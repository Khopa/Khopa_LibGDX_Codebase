package com.khopa.core.views.widgets.carrousel;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.khopa.core.views.actors.SpriteActor;

/**
 * Carrousel Widget for libgdx
 * @author Clément Perreau
 * @date 30/05/2014
 */
public class Carrousel extends Table {

    /**
     * UI skin
     */
    private Skin skin;

    /**
     * Button to show previous entry
     */
    private Button next;

    /**
     * Button to show next entry
     */
    private Button previous;

    /**
     * Whether the carrousel is circular or not
     */
    private boolean circular = true;

    /**
     * List of actors to be displayed
     */
    private TextureRegion[] regions;

    /**
     * Number of entry to display at the same time
     */
    private int visibleEntry;

    /**
     * Current Selection sprite
     */
    private SpriteActor sprite;

    /**
     * Current Selection pointer
     */
    private int selected;

    /**
     * Constructor for Carrousel Widget
     */
    public Carrousel(Skin skin, TextureRegion[] regions){

        selected = 0;
        previous = new TextButton("<", skin);
        next     = new TextButton(">", skin);
        sprite = new SpriteActor(regions[selected]);

        previous.addListener(new CarrouselPreviousButtonListener(this));
        next.addListener(new CarrouselNextButtonListener(this));

        this.regions = regions;
        this.add(previous);
        this.add(sprite);
        this.add(next);

        this.pack();

    }

    // ----- Getters | Setters ------ \\

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Button getNext() {
        return next;
    }

    public void setNext(Button next) {
        this.next = next;
    }

    public Button getPrevious() {
        return previous;
    }

    public void setPrevious(Button previous) {
        this.previous = previous;
    }

    public boolean isCircular() {
        return circular;
    }

    public void setCircular(boolean circular) {
        this.circular = circular;
    }

    public int getVisibleEntry() {
        return visibleEntry;
    }

    public void setVisibleEntry(int visibleEntry) {
        this.visibleEntry = visibleEntry;
    }


    // -------- Listeners ----------- \\

    public void onPrevious() {
        selected = selected - 1;
        if (selected < 0 || regions[selected] == null) selected = regions.length - 1;
        sprite.setRegion(regions[selected]);
    }

    public void onNext() {
        selected = selected + 1;
        if (selected >= regions.length) selected = 0;
        sprite.setRegion(regions[selected]);
    }
}
