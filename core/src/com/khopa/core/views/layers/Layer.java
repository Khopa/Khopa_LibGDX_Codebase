package com.khopa.core.views.layers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.khopa.core.AGC;

/**
 * Handle some custom functionnalities for Group
 * @author Clément Perreau
 */
public abstract class Layer extends Group {

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
     * @param y Y screen coordinate in %
     */
    public void leftAt(Actor actor, float x, float y){
        actor.setX(AGC.getVW()*x);
        System.out.println(actor.getX());
        System.out.println(actor.getWidth());
        actor.setY(AGC.getVH()*y-actor.getHeight()/2);
    }

}
