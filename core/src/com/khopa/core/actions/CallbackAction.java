package com.khopa.core.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.khopa.core.interfaces.Callback;

public class CallbackAction extends Action {

	private Callback callback;
	
	public CallbackAction(Callback callback){
		this.callback = callback;
	}
	
	@Override
	public boolean act(float delta) {
		this.callback.call();
		return true;
	}

	public Callback getCallback() {
		return callback;
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	
	
}
