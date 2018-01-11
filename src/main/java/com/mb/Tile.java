package com.mb;

//  class Tile defines type of tile and if tile is movable then 
//  object from Tile class will be created inside Tile object
public class Tile {

    // variables available in whole program to define type of tiles
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int BOX = 2;
    public static final int PLAYER = 3;
    public static final int SOCKET = 4;
    public static final int EMPTY = 5;
    private int tileType;
    private Tile movable;

    // Tile constructor
    public Tile(int elementType) {
        this.tileType = elementType;
    }

    // getter - returns movable (Tile type object) if exists
    public Tile getMovable() {
        return movable;
    }

    // setter sets movable object (Tile) in object class Type
    public void setMovable(Tile movable) {
        this.movable = movable;
    }

    // getter - returns tile type
    public int getTileType() {
        return tileType;
    }

    // sets type of object from FLOOR = 0;  WALL = 1; BOX = 2; PLAYER = 3; SOCKET = 4; 
    //   EMPTY = 5; available
    public void setTileType(int elementType) {
        this.tileType = elementType;
    }

}
