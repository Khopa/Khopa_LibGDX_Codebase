Khopa LibGDX Codebase
=====================

This is my personnal codebase for libgdx based projects
Feel free to use if you like it, but it may not be fully documented, nor tested.

Features :
----------

- Transitions between scenes
- Handle screen orientation changes
- Translations management
- Resource loading scene
- A more convenient way lo load assets with asset manager
- A lot of convenient classes (at least for me)

PreRequisite :
--------------

- You have to know how to use scene2d in libgdx

MBC means **Main Base Class**

Philosophy :
------------

- Consider that the Scene class is the libgdx Stage class
- You only have to create Scenes for your game, you dont have to manage the main class manually

See for yourself here : "https://github.com/Khopa/Khopa_LibGDX_Codebase/tree/master/core/src/com/khopa/codebase/impl"


Quick example :
---------------

```java
package com.khopa.codebase.impl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.khopa.core.AGC;
import com.khopa.core.actions.transitions.SlideOutSlideInTransition;
import com.khopa.core.services.resources.string.StringManager;
import com.khopa.core.services.skin.SkinService;
import com.khopa.core.views.layers.BackgroundLayer;
import com.khopa.core.views.scene.Scene;

/**
 * @author Clément Perreau
 * @date 21/10/2014.
 * @description Example scene, this demonstrate some of the main features of this code base
 */
public class ExampleSceneA extends Scene {

    /**
     * Background
     */
    private BackgroundLayer background;

    /**
     * Button to switch to scene B
     */
    private TextButton switchToSceneB;

    public ExampleSceneA(){

        // String manager handle translated string dictionaries stored in 'data/translation/lang-XX.xml
        // It use the system default language

        // The skin service make the default skin available globally
        // (skin must be located in data/gfx/'resolution'/skins/uiskin

        switchToSceneB = new TextButton(StringManager.getString("switchB"), SkinService.getSkin());

        // Background : To use background, you must have loaded a 'backgrounds.pack' texture atlas
        switch(AGC.getOrientation()){
            // AGC give you access to the screen orientation and the current virtual screen size
            case Landscape:
                background = new BackgroundLayer("background_landscape");
                break;
            case Portrait:
                background = new BackgroundLayer("background");
                break;
        }
        this.addActor(background);
        this.addActor(switchToSceneB);

        // Convenient scene method to center actors
        center(switchToSceneB);

        switchToSceneB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                new SlideOutSlideInTransition(new ExampleSceneB(), .5f, SlideOutSlideInTransition.RIGHT);
            }
        });

    }

    // Your scene can listen to orientation changes !
    // It will also work on desktop, perfect for testing, isn't it ?

    @Override
    public void onOrientationChangedToPortrait() {
        background.setTextureBackground("background");
    }

    @Override
    public void onOrientationChangedToLandscape() {
        background.setTextureBackground("background_landscape");
    }
}
```

How to use scenes & transitions :
---------------------------------

- Your main class must extends the MBC class and provide the first game scene
  (That means you should keep the default one provided here and change the getStartScene method): 
  https://github.com/Khopa/Khopa_LibGDX_Codebase/blob/master/core/src/com/khopa/codebase/impl/ExampleGame.java
- Inside a scene create a transition to another scene

```java

  // Slide transition
  new SlideOutSlideInTransition(new OtherScene(), .5f, SlideOutSlideInTransition.RIGHT);
  
  // Or FadeOut transition
  new FadeOutFadeInTransition(new OtherScene(), 2f);
  
```

And that's it. Nothing else to do.

*How does it work ? :* 
The **MBC** class manage scenes, creating a transition will trigger a scene change in the MBC, so you have nothing more to do.

How to manage screen orientation changes :
------------------------------------------

In your scene, override these two methods :

```java
    @Override
    public void onOrientationChangedToPortrait() {
        // Example of what you could do here
        background.setTextureBackground("background"); 
    }

    @Override
    public void onOrientationChangedToLandscape() {
        background.setTextureBackground("background_landscape");
    }
```

This is usefull to re-position actors and ui to fit differents screens layouts

*How does it work ? :*
The **MBC** trigger this events after screen resizing.
For example, if width become higher than height, a "onOrientationChangedToLandscape" event will be triggered.
This is a bit hacky, but very convenient since you will be able to trigger the event on desktop by resizing the lwgl frame




