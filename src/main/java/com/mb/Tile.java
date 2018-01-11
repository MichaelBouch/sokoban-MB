package com.mb;

public class Tile {

    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int BOX = 2;
    public static final int PLAYER = 3;
    public static final int SOCKET = 4;
    public static final int EMPTY = 5;
    private int tileType;
    private Tile movable;

    public Tile(int elementType) {
        this.tileType = elementType;
    }
    
    public Tile getMovable() {
        return movable;
    }

    public void setMovable(Tile movable) {
        this.movable = movable;
    }

    public int getTileType() {
        return tileType;
    }

    public void setTileType(int elementType) {
        this.tileType = elementType;
    }

}
