package com.mb;

import static com.mb.MapElement.BOX;
import static com.mb.MapElement.EMPTY;
import static com.mb.MapElement.FLOOR;
import static com.mb.MapElement.PLAYER;
import static com.mb.MapElement.SOCKET;
import static com.mb.MapElement.WALL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Double.min;
import javax.imageio.ImageIO;

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
        adaptiveTileSizeY = (int) ((screenHeight - 80) / arrayOfMapElements[0].length);
        adaptiveTileSizeX = (int) min(adaptiveTileSizeX, adaptiveTileSizeY);
        xRatioOffset = (int) (screenWidth - (arrayOfMapElements.length * adaptiveTileSizeX)) / 2;
        yRatioOffset = (int) 30 + (screenHeight - (arrayOfMapElements[0].length * adaptiveTileSizeX)) / 2;
        System.out.println(" xOffset: " + xRatioOffset + " yOffset: " + yRatioOffset + "  adaptiveTileSizeX: "
                + adaptiveTileSizeX + "   adaptiveTileSizeY:" + adaptiveTileSizeY);
    }

    void paintLevel(Graphics graphics) {

        BufferedImage textureWall = null;
        BufferedImage textureBox = null;
        BufferedImage texturePlayer = null;
        BufferedImage textureFloor = null;
        BufferedImage textureSocket = null;

        try {
            textureWall = ImageIO.read(getClass().getResource("/wall_stone.png"));
            textureBox = ImageIO.read(getClass().getResource("/box1.png"));
            texturePlayer = ImageIO.read(getClass().getResource("/player3.png"));
            textureFloor = ImageIO.read(getClass().getResource("/floor1.png"));
            textureSocket = ImageIO.read(getClass().getResource("/floor1_socket.png"));
        } catch (IOException e) {
            System.out.println(e);
        }

        graphics.drawString("Moves: 20", 40, 45);
        MapElement[][] arrayOfMapElements = levelToPaint.getArrayOfMapElements();
        graphics.drawImage(textureFloor, 0, 0, screenWidth, screenHeight, null);
        
        for (int i = 0; i < arrayOfMapElements.length; i++) {
            MapElement[] arrayOfMapElement = arrayOfMapElements[i];
            for (int j = 0; j < arrayOfMapElement.length; j++) {

                switch (arrayOfMapElement[j].getElementType()) {
                    case FLOOR:
                        graphics.drawImage(textureFloor,
                                i * adaptiveTileSizeX + xRatioOffset,
                                j * adaptiveTileSizeX + yRatioOffset,
                                adaptiveTileSizeX, adaptiveTileSizeX, null);
                        break;
                    case WALL:
                        graphics.drawImage(textureWall,
                                i * adaptiveTileSizeX + xRatioOffset,
                                j * adaptiveTileSizeX + yRatioOffset,
                                adaptiveTileSizeX, adaptiveTileSizeX, null);
                        break;
                    case SOCKET:
                        graphics.drawImage(textureSocket,
                                i * adaptiveTileSizeX + xRatioOffset,
                                j * adaptiveTileSizeX + yRatioOffset,
                                adaptiveTileSizeX, adaptiveTileSizeX, null);
                        break;
                    case EMPTY:
                        break;
                    default:
                }

                if (arrayOfMapElement[j].getMovable() != null) {
                    switch (arrayOfMapElement[j].getMovable().getElementType()) {
                        case PLAYER:
                            graphics.drawImage(texturePlayer,
                                    i * adaptiveTileSizeX + xRatioOffset, j * adaptiveTileSizeX + yRatioOffset,
                                    adaptiveTileSizeX, adaptiveTileSizeX, null);
                            break;
                        case BOX:
                            graphics.drawImage(textureBox,
                                    i * adaptiveTileSizeX + xRatioOffset, j * adaptiveTileSizeX + yRatioOffset,
                                    adaptiveTileSizeX, adaptiveTileSizeX, null);
                            break;
                    }
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

}
