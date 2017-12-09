package com.mb;

import static com.mb.MapElement.EMPTY;
import static com.mb.MapElement.HEIGHT;
import static com.mb.MapElement.PLAYER;
import static com.mb.MapElement.SOCKET;
import static com.mb.MapElement.WALL;
import static com.mb.MapElement.WIDTH;
import java.awt.Graphics;

public class PaintLevel {

    Level levelToPaint;
    int screenWidth;
    int screenHeight;
    int xStart;
    int yStart;

    public PaintLevel(Level levelToPaint, int screenWidth, int screenHeight, int xStart, int yStart) {
        this.levelToPaint = levelToPaint;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.xStart = xStart;
        this.yStart = yStart;
    }

//            for (int i = 0; i < x; i++) {
//            for (int j = 0; j < y; j++) {
//                levelToPaint[j][i] = new MapElement(WALL);
//            }
//    public PaintLevel(Level levelToPaint) {
//        this.levelToPaint = levelToPaint;
//    }
    void paintLevel(Graphics graphics) {
        
        //  class attribute arrayOfMapElements get levelToPaint object
        MapElement[][] arrayOfMapElements = levelToPaint.getArrayOfMapElements();
        //  x.lenght = number of columns   
        for (int i = 0; i < arrayOfMapElements.length; i++) {
            MapElement[] arrayOfMapElement = arrayOfMapElements[i];
            for (int j = 0; j < arrayOfMapElement.length; j++) {
                
                MapElement mapElement = arrayOfMapElement[j];
            }
        }
    }

    void paintLevel_OLD(Graphics graphics) {

        for (MapElement[] mapElements : levelToPaint.getArrayOfMapElements()) {
            for (MapElement mapElement : mapElements) {
                // mapElement.paint(graphics);
                if (mapElement.getElementType() == EMPTY) {
                    drawEmpty();
                }
                if (mapElement.getElementType() == WALL) {
                    drawWall();
                }
                if (mapElement.getElementType() == PLAYER) {
                    drawPlayer();
                }

            }
        }
    }

    private void drawEmpty() {

    }

    private void drawWall() {

    }

    private void drawPlayer() {

    }

//
//    void paint(Graphics graphics) {
//        if (elementType == EMPTY) {
//            graphics.setColor(java.awt.Color.WHITE);
//        }
//        if (elementType == WALL) {
//            graphics.setColor(java.awt.Color.BLACK);
//        }
//        if (elementType == PLAYER) {
//            graphics.setColor(java.awt.Color.BLACK);
//        }
//        if (elementType == SOCKET) {
//            graphics.setColor(java.awt.Color.BLACK);
//        }
//
//        graphics.drawRect(x, y, WIDTH, HEIGHT);
//    }
}
