package com.khopa.core;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.khopa.core.services.ScoreManager;
import com.khopa.core.services.configuration.impl.ConfigurationServiceImpl;
import com.khopa.core.services.graphic.impl.DefinitionDeterminer;
import com.khopa.core.services.resources.ResourceLoader;
import com.khopa.core.services.resources.string.StringManager;
import com.khopa.core.services.skin.SkinService;
import com.khopa.core.services.sound.MusicService;
import com.khopa.core.services.sound.SoundService;
import com.khopa.core.views.Primitives;
import com.khopa.core.views.scene.LoadingScene;
import com.khopa.core.views.scene.Scene;

/**
 *
 * Main Base Class for Khopa LibGDX based Engine
 *
 * @author Clément Perreau
 *
 */
public abstract class MBC extends ApplicationAdapter {

    /**
     * Engine Instance
     */
    private static MBC instance;

    /**
     * Asset Manager
     */
    private AssetManager assetManager;

    /**
     * Loading screen
     */
    private LoadingScene loadingScreen;

    /**
     * Current Scene
     */
    private Scene currentScene;

    /**
     * Elapsed Time
     */
    private static float elapsedTime = 0;

    /**
     * Action Resolver instance
     */
    private static ActionResolver actionResolver;

    public MBC(ActionResolver resolver){
        actionResolver = resolver;
    }

    @Override
    public void create () {

        instance = this;

        // Initialize
        initializeService();

        // Initialize the asset manager
        this.assetManager = new AssetManager();
        ResourceLoader.resolve(this.assetManager);

        // Initialize first scene
        loadingScreen = new LoadingScene();

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);

    }

    public void initializeService(){
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        new ConfigurationServiceImpl(); // Configuration service (legacy)
        new DefinitionDeterminer();     // Definition
        AGC.init(Input.Orientation.Portrait); // Virtual Screen Init
        Primitives.init();              // Init low level drawing
        SkinService.init();             // Load suitable skin
        StringManager.init();           // Init translations
        MusicService.init();            // Music services
        SoundService.init();            // Sounds services
        ScoreManager.init();
        //MessageDispatcher.getInstance().setTimeGranularity(1.0f); (For libgdx-ai extension)
    }

    @Override
    public void render () {

        // Clear screen
        Gdx.gl.glClearColor(0.0666f, 0.2470f, 0.3490f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        elapsedTime += Gdx.graphics.getDeltaTime();

        if(assetManager.update()){
            if(currentScene != null){
                currentScene.act();
                //MessageDispatcher.getInstance().dispatchDelayedMessages();
                currentScene.draw();
            }else{
                setScene(getStartScene());
            }
        }else {
            float progress = assetManager.getProgress();
            if (loadingScreen != null) {
                loadingScreen.setProgressValue(progress);
                loadingScreen.act();
                loadingScreen.draw();
            }
        }
    }


    public abstract Scene getStartScene();


    public static MBC getInstance(){
        return instance;
    }

    /**
     * Scenes
     */
    public void setScene(Scene scene){
        this.currentScene = scene;

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(scene));
        multiplexer.addProcessor(scene);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public static float getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public void resize(int width, int height) {

        if(width > height){
            AGC.toLandscape();
            if(currentScene != null) currentScene.onOrientationChangedToLandscape();
        }else {
            AGC.toPortrait();
            if(currentScene != null) currentScene.onOrientationChangedToPortrait();
        }

        if(currentScene != null){
            currentScene.onResize(width, height);
        }
        if(loadingScreen != null) {
            loadingScreen.onResize(width, height);
        }

    }




    public static ActionResolver getActionResolver() {
        if(actionResolver == null){
            actionResolver = new DefaultActionResolver();
        }
        return actionResolver;
    }
}

