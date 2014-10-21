package com.khopa.core.views.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Clément Perreau (Clement)
 * @date 27/08/2014.
 * @description Describe an animation
 */
public class Animation {

    /**
     * List of texture to display
     */
    private TextureRegion[] regions;

    /**
     * Repeatable
     */
    private boolean repeatable;

    /**
     * Animation frame time
     */
    public float animationFrameTime = .5f;

    /**
     * Animation length
     */
    public int animationLength;

    public Animation(TextureRegion[] regions, boolean repeat, float frameTime, int length){
        setRegions(regions);
        setRepeatable(repeat);
        setAnimationFrameTime(frameTime);
        setAnimationLength(length);
    }

    // ------------------------------- \\


    public TextureRegion[] getRegions() {
        return regions;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public float getAnimationFrameTime() {
        return animationFrameTime;
    }

    public void setAnimationFrameTime(float animationFrameTime) {
        this.animationFrameTime = animationFrameTime;
    }

    public int getAnimationLength() {
        return animationLength;
    }

    public void setAnimationLength(int animationLength) {
        this.animationLength = animationLength;
    }
}
