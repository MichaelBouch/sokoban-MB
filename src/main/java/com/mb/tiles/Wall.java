package com.mb.tiles;

import java.awt.image.BufferedImage;

public class Wall extends Floor {

    private BufferedImage texture;

    public Wall(BufferedImage texture) {
        this.texture = texture;
      
    }

    @Override
   public boolean isEmpty() {
        System.out.println("tekst " + getClass().getCanonicalName());
        return false;
    }

}
