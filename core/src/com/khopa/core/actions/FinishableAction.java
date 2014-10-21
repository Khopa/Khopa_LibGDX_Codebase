package com.khopa.core.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

public abstract class FinishableAction extends Action {

	private boolean finish = false;
	
	public synchronized boolean isFinished(){
		return finish;
	}
	
	public synchronized void setFinish(boolean newValue){
		finish = newValue;
	}
	
}
