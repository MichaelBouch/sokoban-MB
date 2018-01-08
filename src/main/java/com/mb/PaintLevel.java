package com.mb;

import static com.mb.MapElement.BOX;
import static com.mb.MapElement.EMPTY;
import static com.mb.MapElement.FLOOR;
import static com.mb.MapElement.PLAYER;
import static com.mb.MapElement.SOCKET;
import static com.mb.MapElement.WALL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static java.lang.Double.min;

public class PaintLevel {

    Level levelToPaint;
    int screenWidth;
    int screenHeight;
    int xStart;
    int yStart;
    int xRatioOffset = 0;
    int yRatioOffset = 0;
    int adaptiveTileSizeX, adaptiveTileSizeY;

    public PaintLevel(Level levelToPaint, int screenWidth, int screenHeight, int xStart, int yStart) {
        this.levelToPaint = levelToPaint;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.xStart = xStart;
        this.yStart = yStart;
        //  class attribute arrayOfMapElements get levelToPaint object
        MapElement[][] arrayOfMapElements = levelToPaint.getArrayOfMapElements();
        adaptiveTileSizeX = (int) (screenWidth / arrayOfMapElements.length);
        adaptiveTileSizeY = (int) ((screenHeight -80 )/ arrayOfMapElements[0].length);
        adaptiveTileSizeX = (int) min(adaptiveTileSizeX, adaptiveTileSizeY);
        xRatioOffset = (int) (screenWidth - (arrayOfMapElements.length * adaptiveTileSizeX)) / 2;
        yRatioOffset = (int) 30 + (screenHeight - (arrayOfMapElements[0].length * adaptiveTileSizeX)) / 2;
        System.out.println(" xOffset: " + xRatioOffset + " yOffset: " + yRatioOffset + "  adaptiveTileSizeX: "
                + adaptiveTileSizeX + "   adaptiveTileSizeY:" + adaptiveTileSizeY);

    }

    void paintLevel(Graphics graphics) {
        //   setFont(new Font("Arial", Font.PLAIN, 14));
        graphics.drawString("This is going to be game", 10, 10);

        MapElement[][] arrayOfMapElements = levelToPaint.getArrayOfMapElements();

        for (int i = 0; i < arrayOfMapElements.length; i++) {
            MapElement[] arrayOfMapElement = arrayOfMapElements[i];
            for (int j = 0; j < arrayOfMapElement.length; j++) {

                switch (arrayOfMapElement[j].getElementType()) {
                    case FLOOR:
                        break;
                    case WALL:
                        graphics.drawRect(i * adaptiveTileSizeX + xRatioOffset,
                                j * adaptiveTileSizeX + yRatioOffset,
                                adaptiveTileSizeX, adaptiveTileSizeX);
                        break;
                    case SOCKET:
                        graphics.drawOval(i * adaptiveTileSizeX + xRatioOffset + adaptiveTileSizeX / 4,
                                j * adaptiveTileSizeX + yRatioOffset + adaptiveTileSizeX / 4,
                                adaptiveTileSizeX / 2, adaptiveTileSizeX / 2);
                        break;
                    case EMPTY:
                        break;
                    default:
                }

                if (arrayOfMapElement[j].getMovable() != null) {
                    switch (arrayOfMapElement[j].getMovable().getElementType()) {
                        case PLAYER:
                            graphics.drawRect(i * adaptiveTileSizeX + xRatioOffset + adaptiveTileSizeX / 4,
                                    j * adaptiveTileSizeX + yRatioOffset + adaptiveTileSizeX / 4,
                                    adaptiveTileSizeX / 2, adaptiveTileSizeX / 2);
                            break;
                        case BOX:
                            graphics.drawOval(i * adaptiveTileSizeX + xRatioOffset,
                                    j * adaptiveTileSizeX + yRatioOffset,
                                    adaptiveTileSizeX, adaptiveTileSizeX);
                            break;

                    }

                }
            }
        }
        graphics.drawString("This is going to be game", 10, 10);
    }

    private void drawEmpty() {

    }

    private void drawWall() {

    }

    private void drawPlayer() {

    }
}
