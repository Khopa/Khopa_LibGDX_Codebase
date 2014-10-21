package com.khopa.core.views.widgets.carrousel;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * Listener for the carrousel next button
 * @author Clément Perreau
 * @date 30/05/2014
 */
public class CarrouselPreviousButtonListener extends CarrouselClickListener {

    /**
     * Click listener for carousel
     *
     * @param carrousel Carousel to control
     */
    public CarrouselPreviousButtonListener(Carrousel carrousel) {
        super(carrousel);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        this.carrousel.onPrevious();
    }
}
