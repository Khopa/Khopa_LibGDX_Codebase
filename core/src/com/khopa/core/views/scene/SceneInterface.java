package com.khopa.core.views.scene;

import com.badlogic.gdx.Input.Orientation;

public interface SceneInterface extends OrientationListener {

	/**
	 * Should be called when the window/view is resized
	 * @param width New width
	 * @param height New height
	 */
	public void onResize(int width, int height);


	
}
