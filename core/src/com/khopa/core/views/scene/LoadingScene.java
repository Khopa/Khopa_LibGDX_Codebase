package com.khopa.core.views.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.khopa.core.AGC;
import com.khopa.core.MBC;
import com.khopa.core.services.PR;
import com.khopa.core.views.PaletteManager;
import com.khopa.core.views.Primitives;


/**
 * Loading screen scene
 * @author Clément Perreau
 */
public class LoadingScene extends Scene{

    /**
     * Background texture
     */
    private TextureRegion bgTexture;

    /**
     * Progress
     */
    private float progressValue = 0;

    public LoadingScene(){
        Texture texture = null;
        MBC.getInstance().resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        switch (AGC.getOrientation()){
            case Landscape:
                texture = new Texture(Gdx.files.internal(PR.treat("sheets/loading_landscape.png")));
                break;
            case Portrait:
                texture = new Texture(Gdx.files.internal(PR.treat("sheets/loading.png")));
                break;
        }
        Gdx.app.log("SCR", String.valueOf(AGC.getVW()));
        Gdx.app.log("SCR", String.valueOf(AGC.getVH()));
        bgTexture = new TextureRegion(texture, AGC.getVW(), AGC.getVH());
    }

    @Override
    public void draw() {
        super.draw();
        getBatch().begin();
        getBatch().draw(bgTexture, 0, 0);
        getBatch().end();

        Primitives.prepareRenderer(getBatch());
        Primitives.gauge(AGC.getVW() / 2 - AGC.getVW() / 4,
                AGC.getVH() / 2 - AGC.getVH() / 50,
                AGC.getVW() / 2,
                AGC.getVH() / 25,
                AGC.getVH() / 100,
                progressValue, PaletteManager.REIMU_RED, PaletteManager.SPIKE_PURPLE);

        // Primitives.endRendering(getSpriteBatch());
    }

    // ---------- Getters ----------- \\

    public float getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(float progressValue) {
        this.progressValue = progressValue;
    }
}
