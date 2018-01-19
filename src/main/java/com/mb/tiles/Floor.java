package com.mb.tiles;

public class Floor {

    // variables available in whole program to define type of tiles
    private Box movable;

    private int tileType;

    public Floor() {

    }

    public Box getMovable() {
        return movable;
    }

    public void setMovable(Box movable) {
        this.movable = movable;
    }

    public int getTileType() {
        return tileType;
    }

    public void setTileType(int tileType) {
        this.tileType = tileType;
    }

    public boolean isEmpty() {
        System.out.println("tekstA " + getClass().getCanonicalName());
        return movable == null;
    }

    public boolean hasBox() {
        return movable != null;
    }

}
