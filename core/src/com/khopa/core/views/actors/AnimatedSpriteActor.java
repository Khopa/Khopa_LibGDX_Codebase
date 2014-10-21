package com.khopa.core.views.actors;

import com.badlogic.gdx.ai.Agent;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;

import java.util.HashMap;

/**
 * @author Clément Perreau (Clement)
 * @date 27/08/2014.
 * @description Animated sprite Actor
 */
public class AnimatedSpriteActor extends SpriteActor implements Agent {

    public final static int MSG_END_ANIMATION   = 0x7b;
    public final static int MSG_START_ANIMATION = 0x7c;

    /**
     * Animations data texture regions
     */
    private HashMap<String, Animation> animations;

    /**
     * Point toward current animation
     */
    private Animation currentAnimation;

    /**
     * Animation Time Out
     */
    private float animationTimeout = 2f;

    /**
     * Animation
     */
    private int animationIndex = 0;

    public AnimatedSpriteActor(){
        super();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.animationTimeout -= delta;
        if(this.animationTimeout <= 0){
            incrementAnimation();
        }
    }

    public void setAnimation(String name){
        this.currentAnimation = animations.get(name);
        this.animationIndex   = 0;
        this.animationTimeout = currentAnimation.getAnimationFrameTime();
    }

    public void incrementAnimation(){
        animationIndex ++;
        if(animationIndex >= currentAnimation.getAnimationLength()){
            if(currentAnimation.isRepeatable()){
                animationIndex = 1;
            }else{
                animationIndex--;
            }
            MessageDispatcher.getInstance().dispatchMessage(0,this,this, MSG_END_ANIMATION);
        }
        setRegion(currentAnimation.getRegions()[animationIndex]);
        animationTimeout = currentAnimation.getAnimationFrameTime();
    }

    public HashMap<String, Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(HashMap<String, Animation> animations) {
        this.animations = animations;
        for(String key:animations.keySet()){
            setAnimation(key);
            break;
        }
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public float getAnimationTimeout() {
        return animationTimeout;
    }

    public void setAnimationTimeout(float animationTimeout) {
        this.animationTimeout = animationTimeout;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }
}
