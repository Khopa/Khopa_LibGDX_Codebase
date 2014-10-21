package com.khopa.core.services.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.khopa.core.services.graphic.controllers.DefinitionService;

/**
 * This load the application ui skin and makes it available to the whole application
 * through : SkinService.getSkin()
 */
public class SkinService {

	protected static Skin skin;
	
	public static void init(){
		skin = new Skin(Gdx.files.internal("data/gfx/"
				                         + DefinitionService.getInstance().getScreenType().toString()
				                         + "/skins/uiskin.json"));
	}
	
	public static Skin getSkin(){
		return skin;
	}; 
	
	public static BitmapFont getFont(){
		return skin.getFont("default-font");
	}
	
	public static float getFontHeight(){
		return getFont().getLineHeight();
	}
	
}
