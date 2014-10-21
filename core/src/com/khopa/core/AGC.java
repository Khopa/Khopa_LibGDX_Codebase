package com.khopa.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.khopa.core.services.graphic.controllers.DefinitionService;

/**
 * 
 * Application Graphics Context (AGC is less noisy)
 * 
 * @author Clément Perreau
 *
 */
public abstract class AGC {
	
	private static int VW = 1100;
	private static int VH = 2000;

	private static int W = 0;
	private static int H = 0;

    private static int TW = 64;
    private static int TH = 32;

    private static Input.Orientation orientation;

	public static void init(Input.Orientation orientation){
        AGC.orientation = orientation;
		Vector2 chosenProjection = DefinitionService.getInstance().getScreenType().bestProjection(orientation);
		VW = (int) chosenProjection.x;
		VH = (int) chosenProjection.y;
		setW(Gdx.graphics.getWidth());
		setH(Gdx.graphics.getHeight());
        Gdx.app.log(CLT.AGC, "AGC initialized with virtual screen : " + chosenProjection.toString());
	}

    /**
     * Change virtual screen size data to portrait mode
     */
    public static void toPortrait(){
        orientation = Input.Orientation.Portrait;
        recompute();
    }

    /**
     * Change virtual screen size data to portrait mode
     */
    public static void toLandscape(){
        orientation = Input.Orientation.Landscape;
        recompute();
    }

    /**
     * Recompute screen size
     */
    private static void recompute(){
        Vector2 chosenProjection = DefinitionService.getInstance().getScreenType().bestProjection(orientation);
        VW = (int) chosenProjection.x;
        VH = (int) chosenProjection.y;
    }


    public static Input.Orientation getOrientation() {
        return orientation;
    }

    public static int getW() {
		return AGC.W;
	}
	public static void setW(int w) {
        AGC.W = w;
	}
	public static int getH() {
		return AGC.H;
	}
	public static void setH(int h) {
        AGC.H = h;
	}

	public static int getVW() {
		return VW;
	}

	public static void setVW(int VW) {
		AGC.VW = VW;
	}

	public static int getVH() {
		return VH;
	}

	public static void setVH(int VH) {
        AGC.VH = VH;
	}

    public static int getTW() {
        return TW;
    }

    public static void setTW(int TW) {
        AGC.TW = TW;
    }

    public static int getTH() {
        return TH;
    }

    public static void setTH(int TH) {
        AGC.TH = TH;
    }
}
