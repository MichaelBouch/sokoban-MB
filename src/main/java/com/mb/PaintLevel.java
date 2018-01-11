package com.mb;

import static com.mb.Tile.BOX;
import static com.mb.Tile.EMPTY;
import static com.mb.Tile.FLOOR;
import static com.mb.Tile.PLAYER;
import static com.mb.Tile.SOCKET;
import static com.mb.Tile.WALL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Double.min;
import javax.imageio.ImageIO;

public class PaintLevel {

    private Level levelToPaint;
    private int screenWidth;
    private int screenHeight;
    private int xRatioOffset = 0;
    private int yRatioOffset = 0;
    private int adaptiveTileSizeX, adaptiveTileSizeY;

    private BufferedImage textureWall;
    private BufferedImage textureBox;
    private BufferedImage texturePlayer;
    private BufferedImage textureFloor;
    private BufferedImage textureSocket;
    private BufferedImage screenBuffer = new BufferedImage(1200, 850, BufferedImage.TYPE_INT_RGB);

    public PaintLevel(Level levelToPaint, int screenWidth, int screenHeight) {
        this.levelToPaint = levelToPaint;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        //  class attribute arrayOfMapElements get levelToPaint object
        Tile[][] arrayOfMapElements = levelToPaint.getTileMap();
        adaptiveTileSizeX = (int) (screenWidth / arrayOfMapElements.length);
        adaptiveTileSizeY = (int) ((screenHeight - 80) / arrayOfMapElements[0].length);
        adaptiveTileSizeX = (int) min(adaptiveTileSizeX, adaptiveTileSizeY);
        xRatioOffset = (int) (screenWidth - (arrayOfMapElements.length * adaptiveTileSizeX)) / 2;
        yRatioOffset = (int) 30 + (screenHeight - (arrayOfMapElements[0].length * adaptiveTileSizeX)) / 2;
        System.out.println(" xOffset: " + xRatioOffset + " yOffset: " + yRatioOffset + "  adaptiveTileSizeX: "
                + adaptiveTileSizeX + "   adaptiveTileSizeY:" + adaptiveTileSizeY);

    }

    void draw(Graphics graphicsComp, int counter, int currentLevel) {
        //  drawing on graphics object 
        //  drawing on screenBuffer 
        Graphics graphics = screenBuffer.getGraphics();

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, screenWidth, screenHeight);
        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        graphics.setColor(Color.RED);
        graphics.drawString("Level: " + currentLevel, (screenWidth / 5), 50);
        graphics.setColor(Color.ORANGE);
        graphics.drawString("Moves: " + counter, 2 * (screenWidth / 5), 50);
        graphics.setColor(Color.lightGray);
        graphics.drawString("R - Restart", 3 * (screenWidth / 5), 50);
        graphics.drawString("Q - Quit", 4 * (screenWidth / 5), 50);

        Tile[][] tileMap = levelToPaint.getTileMap();

        for (int i = 0; i < tileMap.length; i++) {
            Tile[] tileRow = tileMap[i];
            for (int j = 0; j < tileRow.length; j++) {

                switch (tileRow[j].getTileType()) {
                    case FLOOR:
                        tileDraw(graphics, textureFloor, i, j);
                        break;
                    case WALL:
                        tileDraw(graphics, textureWall, i, j);
                        break;
                    case SOCKET:
                        tileDraw(graphics, textureSocket, i, j);
                        break;
                    case EMPTY:
                        break;
                    default:
                }

                if (tileRow[j].getMovable() != null) {
                    switch (tileRow[j].getMovable().getTileType()) {
                        case PLAYER:
                            graphics.drawImage(texturePlayer,
                                    i * adaptiveTileSizeX + xRatioOffset + 1, j * adaptiveTileSizeX + yRatioOffset + 1,
                                    adaptiveTileSizeX - 2, adaptiveTileSizeX - 2, null);
                            break;
                        case BOX:
                            graphics.drawImage(textureBox,
                                    i * adaptiveTileSizeX + xRatioOffset, j * adaptiveTileSizeX + yRatioOffset,
                                    adaptiveTileSizeX - 1, adaptiveTileSizeX - 1, null);
                            break;
                    }
                }
            }
        }

        //  rysowanie.draw(bf.getGraphics());
        //  rewrite to window video memory (component)
        graphicsComp.drawImage(screenBuffer, 0, 0, null);
    }

    private void tileDraw(Graphics graphics, BufferedImage texture, int i, int j) {
        graphics.drawImage(texture,
                i * adaptiveTileSizeX + xRatioOffset,
                j * adaptiveTileSizeX + yRatioOffset,
                adaptiveTileSizeX, adaptiveTileSizeX, null);
    }

    //  prevali
    public void loadTextures(int currentLevel) throws IOException {
        if (currentLevel == 1) {
            textureWall = ImageIO.read(getClass().getResource("/wall_steel.png"));
        } else {
            textureWall = ImageIO.read(getClass().getResource("/wall_stone.png"));
        }
        textureBox = ImageIO.read(getClass().getResource("/box1.png"));
        texturePlayer = ImageIO.read(getClass().getResource("/player6.png"));
        textureFloor = ImageIO.read(getClass().getResource("/floor1.png"));
        textureSocket = ImageIO.read(getClass().getResource("/floor1_socket1.png"));
    }
}
