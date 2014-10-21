package com.khopa.codebase.impl;

import com.khopa.core.ActionResolver;
import com.khopa.core.MBC;
import com.khopa.core.views.scene.Scene;


/**
 *
 * As you constantly have to write the same code here, i extracted it
 * to 'MBC' class (Main Base Class)
 *
 * To access the game single instance, use MBC.getInstance()
 *
 */
public class ExampleGame extends MBC {

    public ExampleGame() {
        super(null);
    }

    /**
     * Pass an action resolver if you need to call platform specific stuff
     */
    public ExampleGame(ActionResolver resolver) {
        super(resolver);
    }

    @Override
    public Scene getStartScene() {
        return new ExampleSceneA();
    }

}
