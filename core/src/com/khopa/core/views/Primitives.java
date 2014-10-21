package com.khopa.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * Primitives pour le dessin de formes
 * @author Clément Perreau
 */
public class Primitives {
	
	/**
	 * Shape renderer
	 */
	private static ShapeRenderer renderer;
	
	public static void init(){
		renderer = new ShapeRenderer();
	}
	
	/**
	 * Prepare renderer
	 */
	public static void prepareRenderer(Batch batch){
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
	}
	
	/**
	 * Enable alpha blending
	 */
	public static void enableAlphaBlending(){
		Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	/**
	 * Disable alpha blending
	 */
	public static void disableAlphaBlending(){
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	/**
	 * Choose line width
	 */
	public static void lineWidth(int width){
		Gdx.gl20.glLineWidth(width);
	}

	/**
	 * Draw a rectangle with given color
	 */
	public static void rect(int x, int y, int w, int h,Color color){
		renderer.setColor(color);
		Primitives.rect(x,y,w,h);
	}

    public static void circle(int x, int y, int r, Color color){
        renderer.begin(ShapeType.Line);
        renderer.setColor(color);
        renderer.circle(x,y,r);
        renderer.end();
    }

    /**
     * Draw a rectangle with current gl color
     */
	public static void rect(int x, int y, int w, int h){
		renderer.begin(ShapeType.Line);
		renderer.rect(x, y, w, h);
		renderer.end();
	}
	
	/**
	 * Draw a filled rectangle with given color
	 */
	public static void filledRect(int x, int y, int w, int h, Color color){
		renderer.begin(ShapeType.Filled);
		renderer.setColor(color);
		renderer.rect(x, y, w, h);
		renderer.end();
	}
	
	/**
	 * Draw a line
	 */
	public static void line(int x1, int y1, int x2, int y2, int width, Color color){
		renderer.begin(ShapeType.Line);
		renderer.setColor(color);
		Gdx.gl20.glLineWidth(width);
		renderer.line(x1, y1, x2, y2);
		renderer.end();
	}
	
	/**
	 * End rendering shapes
	 * @param batch
	 */
	public static void endRendering(Batch batch){
		batch.begin();
	}

	/**
	 * Draw a gauge
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param w gauge width
	 * @param h gauge height
	 * @param borderSize gauge's border size
	 * @param percent Fill ratio
	 * @param colorInside inside color
	 * @param borderColor border color
	 */
	public static void gauge(int x, int y, int w, int h, int borderSize, float percent, Color colorInside, Color borderColor){
		
		// Draw borders
		Primitives.filledRect(x,y,w,h,borderColor);

		int wg = (int) (percent*(w-2*borderSize));
		
		// Draw gauge
		Primitives.filledRect(x+borderSize, y+borderSize, wg, h-2*borderSize, colorInside);
		
	}
	
	
	
}
