package com.khopa.core.actions.transitions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.khopa.core.controllers.animation.transition.Transitions;
import com.khopa.core.views.scene.Scene;

public class FadeOutFadeInSceneTransition extends SceneTransitionAction {

	public FadeOutFadeInSceneTransition(Scene newScene, float duration) {
		super(newScene, duration);
		sceneTransition.getStartScene().addAction(Actions.fadeOut(duration/2));
		sceneTransition.getStopScene().addAction(Actions.sequence(Actions.color(Color.BLACK),
																  Actions.delay(duration/2),
																  Transitions.fadeToWhite(duration / 2)));
	}	

}
