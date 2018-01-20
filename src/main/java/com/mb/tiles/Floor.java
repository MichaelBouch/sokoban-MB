package com.mb.tiles;

import com.mb.TextureLoader;
import java.awt.image.BufferedImage;

public class Floor {

    // variables available in whole program to define type of tiles
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
        System.out.println("tekstA " + getClass().getCanonicalName());
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
