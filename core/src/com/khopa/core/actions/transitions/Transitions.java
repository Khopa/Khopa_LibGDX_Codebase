package com.khopa.core.actions.transitions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Transitions {

	public static Action fadeOut(float duration){
		return Actions.alpha(0, duration);
	}
	
	public static Action fadeIn(float duration){
		return Actions.sequence(Actions.alpha(0), Actions.alpha(1, duration));
	}
	
	public static Action fadeToBlack(float duration){
		return Actions.color(new Color(0.1f, 0.1f, 0.1f, 1f), duration);
	}

	public static Action fadeToWhite(float duration) {
		return Actions.color(new Color(1f, 1f, 1f, 1f), duration);
	}
	
}
