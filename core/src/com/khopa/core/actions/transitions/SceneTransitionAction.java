package com.khopa.core.actions.transitions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.khopa.core.MBC;
import com.khopa.core.views.scene.Scene;

/**
 * Transition between two scenes
 * @author Clément Perreau
 */
public abstract class SceneTransitionAction extends Action{

	/**
	 * Elapsed time
	 */
	protected float elapsedTime;
	
	/**
	 * Action Duration
	 */
	protected float duration;
	
	/**
	 * Current transition scene
	 */
	protected SceneTransition sceneTransition;
	
	public SceneTransitionAction(Scene newScene, float duration){
		this.sceneTransition = new SceneTransition(MBC.getInstance().getCurrentScene(), newScene);
		this.sceneTransition.addAction(this);
        MBC.getInstance().setScene(this.sceneTransition);
		setDuration(duration);
	}
	
	@Override
	public boolean act(float delta) {
		elapsedTime += delta;
		if(elapsedTime >= duration){
            MBC.getInstance().setScene(sceneTransition.getStopScene());
			return true;
		}
		return false;
	}
	
	// ------ Getters ------- \\
	
	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}


}
