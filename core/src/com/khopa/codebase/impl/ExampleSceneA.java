package com.khopa.codebase.impl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.khopa.core.AGC;
import com.khopa.core.actions.transitions.SlideOutSlideInTransition;
import com.khopa.core.services.resources.string.StringManager;
import com.khopa.core.services.skin.SkinService;
import com.khopa.core.views.layers.BackgroundLayer;
import com.khopa.core.views.scene.Scene;

/**
 * @author Clément Perreau
 * @date 21/10/2014.
 * @description Example scene, this demonstrate some of the main features of this code base
 */
public class ExampleSceneA extends Scene {

    /**
     * Background
     */
    private BackgroundLayer background;

    /**
     * Button to switch to scene B
     */
    private TextButton switchToSceneB;

    public ExampleSceneA(){

        // String manager handle translated string dictionaries stored in 'data/translation/lang-XX.xml
        // It use the system default language

        // The skin service make the default skin available globally
        // (skin must be located in data/gfx/'resolution'/skins/uiskin

        switchToSceneB = new TextButton(StringManager.getString("switchB"), SkinService.getSkin());

        // Background : To use background, you must have loaded a 'backgrounds.pack' texture atlas
        switch(AGC.getOrientation()){
            // AGC give you access to the screen orientation and the current virtual screen size
            case Landscape:
                background = new BackgroundLayer("background_landscape");
                break;
            case Portrait:
                background = new BackgroundLayer("background");
                break;
        }
        this.addActor(background);
        this.addActor(switchToSceneB);

        // Convenient scene method to center actors
        center(switchToSceneB);

        switchToSceneB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                new SlideOutSlideInTransition(new ExampleSceneB(), .5f, SlideOutSlideInTransition.RIGHT);
            }
        });

    }

    // Your scene can listen to orientation changes !
    // It will also work on desktop, perfect for testing, isn't it ?

    @Override
    public void onOrientationChangedToPortrait() {
        background.setTextureBackground("background");
    }

    @Override
    public void onOrientationChangedToLandscape() {
        background.setTextureBackground("background_landscape");
    }
}
