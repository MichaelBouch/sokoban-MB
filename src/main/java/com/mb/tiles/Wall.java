package com.mb.tiles;

import java.awt.image.BufferedImage;

// Wall class inherits Floor class fields and methods
public class Wall extends Floor {

    public Wall(BufferedImage texture) {
        super(texture);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
