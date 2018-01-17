package com.mb.tiles;

public class Floor {

    private Box movable;

    private int tileType;

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

    

    Box isEmpty() {
        if (movable == null) {
            return null;
        } else {
            return movable;
        }
    }
}
