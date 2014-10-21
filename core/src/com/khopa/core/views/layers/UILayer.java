package com.khopa.core.views.layers;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khopa.core.AGC;

/**
 *
 */
public class UILayer extends Layer {

    /**
     * Reference to the scene camera
     */
    private OrthographicCamera sceneCamera;

    /**
     * Reference to the own camera
     */
    private Camera camera;

    private SpriteBatch ownbatch;

    public UILayer(OrthographicCamera camera){
        super();
        this.camera =  new OrthographicCamera(AGC.getVW(), AGC.getVH());
        this.sceneCamera = camera;
        this.ownbatch = new SpriteBatch();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ownbatch.begin();
        super.draw(ownbatch, parentAlpha);
        ownbatch.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
