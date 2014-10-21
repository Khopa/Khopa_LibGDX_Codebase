package com.khopa.core.views.layers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.khopa.core.MBC;
import com.khopa.core.services.PR;
import com.khopa.core.views.actors.SpriteActor;

/**
 * Create a background layer with a fixed image
 * Require a background texture pack in the assets folder
 * (data/gfx/ld/sheets/backgrounds.pack) for low def devices
 * (data/gfx/md/sheets/backgrounds.pack) for medium devices
 * (data/gfx/hd/sheets/backgrounds.pack) for hd devices
 */
public class BackgroundLayer extends Layer {

	/**
	 * Background image actor
	 */
	private Actor background;

    /**
     * Create a background layer
     * @param bgName Name of the texture file in the backgrounds texture pack
     */
	public BackgroundLayer(String bgName) {
		super();
        AssetManager asm = MBC.getInstance().getAssetManager();
        TextureAtlas backgrounds = asm.get(PR.treat("sheets/backgrounds.pack"), TextureAtlas.class);
        background = new SpriteActor(backgrounds.findRegion(bgName));
        this.addActor(background);
	}

    /**
     * Get the background actor
     */
	public Actor getBackground() {
		return background;
	}

    /**
     * Change the background actor
     * @param bgname New background name
     */
    public void setTextureBackground(String bgname){
        this.removeActor(background);
        AssetManager asm = MBC.getInstance().getAssetManager();
        TextureAtlas backgrounds = asm.get("data/gfx/hd/sheets/backgrounds.pack", TextureAtlas.class);
        this.background = new SpriteActor(backgrounds.findRegion(bgname));
        this.addActor(background);
    }

	
}
