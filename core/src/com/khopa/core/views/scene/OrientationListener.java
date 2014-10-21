package com.khopa.core.views.scene;

/**
 * @author Clement
 * @date 16/10/2014.
 * @description Listen to orientation changes
 */
public interface OrientationListener {

    /**
     * Should be called when screen orientation is changed to portrait
     */
    public void onOrientationChangedToPortrait();

    /**
     * Should be called when screen orientation is changed to landscape
     */
    public void onOrientationChangedToLandscape();

}
