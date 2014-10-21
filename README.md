Khopa LibGDX Codebase
=====================

This is my personnal codebase for libgdx based projects.
Feel free to use if you like it, but it may not be fully documented, nor tested.

**Importing in Intellij IDEA / Android Studio :**

- File/Import Projet and select the build.gradle file. (Nothing more to do, just wait for the IDE to resolve dependencies)

**Importing in Eclipse :**

- No idea, you will probably need the ADT plugin, and a Gradle plugin

**Use without IDE :**

- Use the gradle files to build the project


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


Quick features overview :
-------------------------

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
For example, if the screen width becomes higher than the screen height, a "onOrientationChangedToLandscape" event will be triggered.
This is a bit hacky, but very convenient since you will be able to trigger the event on desktop by resizing the lwgl frame

Managing different screen resolutions and device graphic capabilities :
----------------------------------------------------------------------

The latest libgdx versions introduced the very convenient viewport classes. So working in a very high resolution virtual screen size is possible.,

However, some devices may not be able to load high resolution graphics, and it will result in 'white' texture if you load too high resolution textures (which are needed for high resolution screens).

This codebase provide a way to resolve this problem, allowing you to use very high resolution graphics on high end devices while still being able to support low-cost/old devices by using resized smaller textures.

You will have to organize graphics ressources in your folders like that

assets/data/gfx/hd
assets/data/gfx/md
assets/data/gfx/ld

- HD -> Texture UP to 2048*2048, Virtual Screen Size of (2000*1100)
- MD -> Texture UP to 1024*1024, Virtual Screen Size of (1000*550)
- LD -> Texture UP to 512*512,   Virtual Screen Size of (500*275)

To access the best resource to use for this device, you may then use the PathResolver (PR) class :

```java

PR.treat("my_nice_asset.png")

// Will return 'assets/data/gfx/hd/my_nice_asset.png' on Galaxy S4 
// or 'assets/data/gfx/md/my_nice_asset.png' on an old LG Optimus Black for instance

```

A convenient python script to resize assets (.png Images and .pack TextureAtlas files) is available in the assets folder
Python 2.7 and PIL are needed (build_gfx.py) You may include it to your gradle build.

Assets like .fnt may still have to be resized manually

**This means my apk will contains 3 times the same assets in different resolutions :** **YES**

Translations :
--------------

System settings will be used to determine the user language. English is the default language.
Create translation files in assets/data/translations to handle more translations !

**Example :**

**lang-en.xml**
```xml
<english>

    <!-- General -->
    <entry key = "yes"     content = "Yes"/>
    <entry key = "no"      content = "No"/>

    <!-- Scene -->
    <entry key = "switchA" content = "Switch to scene A (Slide Transition)"/>
    <entry key = "switchB" content = "Switch to scene B (Fade Transition)"/>

</english>
```

**lang-fr.xml**
```xml
<french>

    <!-- General -->
    <entry key = "yes"     content = "Oui"/>
    <entry key = "no"      content = "Non"/>

    <!-- Scene -->
    <entry key = "switchA" content = "Changer pour la scene A (Slide Transition)"/>
    <entry key = "switchB" content = "Changer pour la scene B (Fade Transition)"/>

</french>
```

Then to get a string, use the key :

```java
    StringManager.getString("yes");
```

This will return 'oui' if systems settings are in French for example.
Of course, it will work on Android.

You may use format expression for more advanced strings :

**Example :**

```xml
<english>
    <entry key = "red"  content = "red"/>
    <entry key = "car"  content = "I want a %s car"/>
<english>
```

```xml
<french>
    <entry key = "red"  content = "rouge"/>
    <entry key = "car"  content = "Je veux une voiture %s"/>
<french>
```

Then :

```java
    String adjective = StringManager.getString("red");
    String.format(StringManager.getString("car"), adjective);
```









