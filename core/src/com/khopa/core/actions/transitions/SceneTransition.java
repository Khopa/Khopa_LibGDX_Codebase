package com.khopa.core.actions.transitions;

import com.khopa.core.views.scene.Scene;

public class SceneTransition extends Scene {

	/**
	 * Current scene
	 */
	private Scene startScene;
	
	/**
	 * Scene we want to get in
	 */
	private Scene stopScene;
	
	/**
	 * Whether the start scene continue acting
	 */
	private boolean actA;
	
	/**
	 * Whether the stop scene start acting directly
	 */
	private boolean actB;

	// ------------- Constructors -------------- \\
	
	/**
	 * Transition between scene start and scene stop
	 * @param start StartScene
	 * @param stop  StopScene
	 * @param actA  Act for starting scene or not
	 * @param actB  Act for end scene or not
	 */
	public SceneTransition(Scene start, Scene stop, boolean actA, boolean actB){
		setStartScene(start);
		setStopScene(stop);
		setActA(actA);
		setActB(actB);
	}
	
	public SceneTransition(Scene start, Scene stop){
		this(start, stop, false, false);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		startScene.act();
		stopScene.act();
	}
	
	
	@Override
	public void draw() {
		super.draw();
		stopScene.draw();
		startScene.draw();
	}
	
	// ------------- Static --------------- \\
	
	
	
	// ------------- Getters -------------- \\
	
	public Scene getStartScene() {
		return startScene;
	}

	public void setStartScene(Scene startScene) {
		this.startScene = startScene;
	}

	public Scene getStopScene() {
		return stopScene;
	}

	public void setStopScene(Scene stopScene) {
		this.stopScene = stopScene;
	}

	public boolean isActA() {
		return actA;
	}

	public void setActA(boolean actA) {
		this.actA = actA;
	}

	public boolean isActB() {
		return actB;
	}

	public void setActB(boolean actB) {
		this.actB = actB;
	}
	
}
