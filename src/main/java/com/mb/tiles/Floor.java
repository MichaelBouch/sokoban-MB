package com.mb.tiles;

public class Floor {

    // variables available in whole program to define type of tiles
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int BOX = 2;
    public static final int PLAYER = 3;
    public static final int SOCKET = 4;
    private Box movable;
    private boolean isEmpty;

    private int tileType;

    public Floor(int tileType) {
        this.tileType = tileType;
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

    boolean isEmpty() {
        if (movable == null) {
            return false;
        } else {
            return true;
        }
    }
}
