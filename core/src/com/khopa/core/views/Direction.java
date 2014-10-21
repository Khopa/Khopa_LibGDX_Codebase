package com.khopa.core.views;

/**
 * @author Clément Perreau
 * @date 15/07/2014
 */
public enum Direction {
    NORTH((char)1),
    EAST((char)2),
    SOUTH((char)4),
    WEST((char)8);

    private char value;

    Direction(char value) {
        this.value = value;
    }

    public char toBitValue() {
        return value;
    }
}
