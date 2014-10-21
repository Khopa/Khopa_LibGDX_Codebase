package com.khopa.core.views.widgets.carrousel;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Carrousel Click Listener
 * @author Clément Perreau
 * @date 30/05/2014
 */
public class CarrouselClickListener extends ClickListener {

    protected Carrousel carrousel;

    /**
     * Click listener for carousel
     * @param carrousel Carousel to control
     */
    public CarrouselClickListener(Carrousel carrousel){
        this.carrousel = carrousel;
    }

}
