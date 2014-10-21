package com.khopa.core.actions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.khopa.core.views.widgets.TextZone;

public class TextZoneWriting extends FinishableAction {

	/**
	 * Targetted text zone
	 */
	private TextZone target;
	
	/**
	 * Time between letters
	 */
	private float deltaLetter;
	
	/**
	 * Delta tmp
	 */
	private float delta = 0;
	
	/**
	 * Current letter index
	 */
	private int index = 0;
	
	/**
	 * Final Position of the text
	 */
	private Vector2 basePosition;
	
	/**
	 * Label index
	 */
	private int labelIndex;
	
	/**
	 * Label start
	 */
	private int labelStart;
	
	/**
	 * Action to make text being written
	 * @param target Targetted textzone
	 * @param deltaLetter Delta time between each letter being written
	 */
	public TextZoneWriting(TextZone target, float deltaLetter, Vector2 topLeft){
		this.target = target;
		this.deltaLetter = deltaLetter;
		
		for(Label label:target.getLabels()){
			label.setText("");
		}
		
		basePosition = topLeft;
		labelIndex = 0;
		labelStart = 0;
		index = 0;
		
	}

	@Override
	public boolean act(float delta) {
		this.delta += delta;
		
		if(this.isFinished()){
			int i = 0;
			for(Label label:target.getLabels()){
				label.setText(target.getWords().get(i++));
			}
			target.pack();
			target.setPosition(basePosition.x, basePosition.y-target.getHeight());
			return true;
		}

		if(index == 0){
			for(Label label:target.getLabels()){
				label.setText("");
			}
		}
		
		if(labelIndex >= target.getLabels().size()){
			labelIndex = 0;
			labelStart = 0;
			index = 0;
			setFinish(true);
			return true;
		}
		
		Label  current     = target.getLabels().get(labelIndex);
		String currentWord = target.getWords().get(labelIndex);
		
		if(this.delta > deltaLetter){
			current.setText(currentWord.substring(0, index-labelStart+1));
			index ++;
			if(index - labelStart >= currentWord.length()){
				labelIndex++;
				labelStart = index;
			}
			target.pack();
			target.setPosition(basePosition.x, basePosition.y-target.getHeight());
			this.delta = 0;
		}
		
		return false;
	}

}
