package com.khopa.core.services;

import com.badlogic.gdx.Gdx;
import com.khopa.core.CLT;
import com.khopa.core.MCR;

/**
 * @author Clement Perreau
 * @date 25/08/2014
 *
 * Controller for game score, (with possibly interface to google play services in the future)
 *
 */
public class ScoreManager {

    /**
     * Current score
     */
    private static float ST_SCORE;

    /**
     * Best score ever (Get from app prefs)
     */
    private static int ST_BEST;

    /**
     * Init score manager component
     */
    public static void init(){
        ST_SCORE = 0;
        ST_BEST  = Gdx.app.getPreferences(CLT.ENGINE+ CLT.UUID).getInteger("BEST", 0);
        MCR.getActionResolver().getUserScore();
    }

    /**
     * Increment score
     */
    public static void increment(){
        ST_SCORE += Gdx.graphics.getDeltaTime()*5;
        if(ST_SCORE > ST_BEST){
            ST_BEST = (int)ST_SCORE;
            Gdx.app.getPreferences(CLT.ENGINE+CLT.UUID).putInteger("BEST", ST_BEST);
        }
    }

    public static void setCurrent(float val){
        ST_SCORE = val;
        if(ST_SCORE > ST_BEST){
            ST_BEST = (int)ST_SCORE;
            Gdx.app.getPreferences(CLT.ENGINE+CLT.UUID).putInteger("BEST", ST_BEST);
        }
    }

    public static void reset(){
        ST_SCORE = 0;
    }

    public static int getCurrent(){
        return (int)ST_SCORE;
    }

    public static int getBest(){
        return ST_BEST;
    }

    public static void setBest(int best) {
        ST_BEST = best;
    }
}
