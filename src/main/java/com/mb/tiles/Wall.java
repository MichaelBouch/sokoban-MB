package com.mb.tiles;

import java.awt.image.BufferedImage;

public class Wall extends Floor {

    public Wall(BufferedImage texture) {
        super(texture);
    }

    @Override
    public boolean isEmpty() {
        System.out.println("tekst " + getClass().getCanonicalName());
        return false;
    }
}
