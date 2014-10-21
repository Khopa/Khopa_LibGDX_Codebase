package com.khopa.core.views.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.khopa.core.views.PaletteManager;
import com.khopa.core.views.Primitives;

public class SpriteActor extends Actor {

	private TextureRegion region;

    public SpriteActor(){
        this.region = null;
    }

	public SpriteActor(TextureRegion region){
		this.region = region;
		this.setWidth(region.getRegionWidth());
		this.setHeight(region.getRegionHeight());
        this.setOrigin(getWidth()/2, getHeight()/2);
	}

    @Override
	public void draw(Batch batch, float parentAlpha) {

        if(this.region != null) {
            super.draw(batch, parentAlpha);
            Color color = batch.getColor();
            Color current = this.getColor();
            batch.setColor(current.r, current.g, current.b, current.a * parentAlpha);
            batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            batch.setColor(color);
        }

        /*if(true){
            batch.end();
            Primitives.rect((int)this.getX(), (int)(this.getY()), (int) this.getWidth(), (int) this.getHeight(), PaletteManager.ICE_CREAM);
            Primitives.circle((int)this.getCenterX(), (int)this.getCenterY(), (int)this.getWidth()/2, PaletteManager.STRAWBERRY_RED);
            Primitives.circle((int)this.getCenterX(), (int)this.getCenterY(), (int)this.getWidth()/8, Color.RED);
            batch.begin();
        }*/
	}

	public TextureRegion getRegion() {
		return region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
        this.setWidth(region.getRegionWidth());
        this.setHeight(region.getRegionHeight());
        this.setOrigin(getWidth()/2, getHeight()/2);
	}

}
