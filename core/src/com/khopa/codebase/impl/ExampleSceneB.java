package com.khopa.codebase.impl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.khopa.core.actions.transitions.FadeOutFadeInSceneTransition;
import com.khopa.core.services.resources.string.StringManager;
import com.khopa.core.services.skin.SkinService;
import com.khopa.core.views.scene.Scene;

/**
 * @author Clément Perreau (Clement)
 * @date 21/10/2014.
 * @description Simple demo scene
 */
public class ExampleSceneB extends Scene {


    private TextButton switchToSceneA;

    public ExampleSceneB(){

        switchToSceneA = new TextButton(StringManager.getString("switchA"), SkinService.getSkin());

        this.addActor(switchToSceneA);
        center(switchToSceneA);

        switchToSceneA.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                new FadeOutFadeInSceneTransition(new ExampleSceneA(), 2f);
            }
        });

    }

}
