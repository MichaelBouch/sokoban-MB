package com.mb.tiles;

public class Wall extends Floor {

    public Wall(int tileType) {
        super(tileType);
    }

    @Override
   public boolean isEmpty() {
        System.out.println("tekst " + getClass().getCanonicalName());
        return false;
    }

}
