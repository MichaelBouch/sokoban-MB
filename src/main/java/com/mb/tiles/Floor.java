package com.mb.tiles;

import java.awt.image.BufferedImage;

public class Floor {

    // parent class of all tiles - have fields and necessary getters and setters for each tile
    // each tile contains it's texture to simplify painting method
    private Box movable;
    private BufferedImage texture;

    public Floor(BufferedImage texture) {
        this.texture = texture;
    }

    public Box getMovable() {
        return movable;
    }

    public void setMovable(Box movable) {
        this.movable = movable;
    }

    public boolean isEmpty() {
        return movable == null;
    }

    public boolean hasBox() {
        return movable != null;

    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

}
