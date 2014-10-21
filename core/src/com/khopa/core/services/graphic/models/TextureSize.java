package com.khopa.core.services.graphic.models;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public enum TextureSize {
	LOW,
	MEDIUM,
	HIGH;
	
	public String toString(){
		switch(this){
			case LOW:
				return "ld";
			case MEDIUM:
				return "md";
			case HIGH:
				return "hd";
			default:
				break;
		}
		return "ld";
	}
	
	public Vector2 bestProjection(Input.Orientation orientation){

        switch(orientation){
            case Landscape:
                switch(this){
                    case LOW:       // 512
                        return new Vector2(500, 275);
                    case MEDIUM:    // 1024
                        return new Vector2(1000, 550);
                    case HIGH:      // 2048
                        return new Vector2(2000, 1100);
                    default:
                        return new Vector2(500, 275);
                }
            case Portrait:
                switch(this){
                    case LOW:       // 512
                        return new Vector2(275, 500);
                    case MEDIUM:    // 1024
                        return new Vector2(550, 1000);
                    case HIGH:      // 2048
                        return new Vector2(1100, 2000);
                    default:
                        return new Vector2(275, 500);
                }
            default: // Should not happen
                return new Vector2(1100, 2000);
        }

	}

    public int toInvFactor(){
        switch(this){
            case LOW:
                return 4;
            case MEDIUM:
                return 2;
            case HIGH:
                return 1;
            default:
                break;
        }
        return 4;
    }
	
}
