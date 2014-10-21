package com.khopa.core.services.graphic.impl;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.khopa.core.CLT;
import com.khopa.core.services.graphic.controllers.DefinitionService;
import com.khopa.core.services.graphic.models.TextureSize;

public class DefinitionDeterminer extends DefinitionService {

	private TextureSize screenType;
	
	public DefinitionDeterminer(){
		super();
		
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		int m = 0;
		if(w>h) m = w;
		else m = h;
		
		if(Gdx.app.getType() == ApplicationType.Desktop){
			screenType = TextureSize.HIGH;
		}
		else if(m <= 480){
			screenType = TextureSize.LOW;
		}
		else if(m <= 1024){
			screenType = TextureSize.MEDIUM;
		}
		else {
			screenType = TextureSize.HIGH;
		}
		
		Gdx.app.log(CLT.DEF, "System screen definition : " + screenType.toString());
		
	}
	
	@Override
	public TextureSize getScreenType() {
		return screenType;
	}
	
	public Vector2 getBestResolution(){
		return screenType.bestProjection(Gdx.input.getNativeOrientation());
	}
	
}
