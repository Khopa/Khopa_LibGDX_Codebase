package com.khopa.core.views.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.khopa.core.AGC;

public class Scene extends Stage implements SceneInterface, GestureDetector.GestureListener{

	public Scene(){
        super(new StretchViewport(AGC.getVW(),AGC.getVH()));
    }

    @Override
    public void onResize(int width, int height) {
        Gdx.app.debug("SCREEN", "RESIZING");
        setViewport(new StretchViewport(AGC.getVW(), AGC.getVH()));
        getViewport().update(width, height, true);
    }

    @Override
    public void onOrientationChangedToPortrait() {
        // Do nothing
    }

    @Override
    public void onOrientationChangedToLandscape() {
        // Do nothing
    }

    // Gesture Adaptater //

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    /**
     * Center the actor to the center of the screen
     * @param actor Actor to center
     */
    public void center(Actor actor){
        centerAt(actor, .5f,.5f);
    }

    /**
     * Center the actor to the given screen coordinate (in %)
     * @param actor Actor to center
     * @param x X screen coordinate in %
     * @param y Y screen coordinate in %
     */
    public void centerAt(Actor actor, float x, float y){
        actor.setX(AGC.getVW()*x-actor.getWidth()/2);
        actor.setY(AGC.getVH()*y-actor.getHeight()/2);
    }

    /**
     * Set the actor left coordinate to the given screen coordinate (in %)
     * @param actor Actor to center
     * @param x Left X screen coordinate in %
     * @param y Bottom Y screen coordinate in %
     */
    public void leftAt(Actor actor, float x, float y){
        actor.setX(AGC.getVW()*x);
        System.out.println(actor.getX());
        System.out.println(actor.getWidth());
        actor.setY(AGC.getVH()*y-actor.getHeight()/2);
    }


    /**
     * Get the center coordinate needed for the given actor to make him appear at given point
     * @param actor Actor
     * @param x Point X in %
     * @param y Point Y in %
     * @return Pixel Centered coordinate at the given percent point
     */
    public Vector2 getCenterCoordinateAtForActor(Actor actor, float x, float y){
        return new Vector2(AGC.getVW()*x-actor.getWidth()/2,AGC.getVH()*y-actor.getHeight()/2);
    }


    /**
     * Get the left coordinate in pixel for the point given in screen percentage
     * @param x Point X in %
     * @param y Point Y in %
     * @return Pixel Left coordinate at the given percent point
     */
    public Vector2 getPixelLeftCoordinateForPercentPoint(float x, float y){
        return new Vector2(AGC.getVW()*x,AGC.getVH()*y);
    }


}
