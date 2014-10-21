package com.khopa.core.actions.transitions;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.khopa.core.AGC;
import com.khopa.core.views.scene.Scene;

public class SlideOutSlideInTransition extends SceneTransitionAction {

	public static final int LEFT  = 0;
	public static final int RIGHT = 1;
	
	public SlideOutSlideInTransition(Scene newScene, float duration, int direction) {
		super(newScene, duration);
		if(direction == RIGHT){
			sceneTransition.getStartScene().addAction(Actions.moveTo(-AGC.getVW(), 0, duration));
			sceneTransition.getStopScene().addAction(Actions.sequence(Actions.moveTo(AGC.getVW(), 0),
					                                                  Actions.moveTo(0, 0, duration)));
		}
		else{
			sceneTransition.getStartScene().addAction(Actions.moveTo(AGC.getVW(), 0, duration));
			sceneTransition.getStopScene().addAction(Actions.sequence(Actions.moveTo(-AGC.getVW(), 0),
					                                                  Actions.moveTo(0, 0, duration)));
		}
	}

}
