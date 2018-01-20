package com.mb;

import com.mb.tiles.Floor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Double.min;
import javax.imageio.ImageIO;

// PaintLevel class main functions: calculate size of tiles,  
// load textures, displays tiles and top menu
public class PaintLevel {

    private Level levelToPaint;
    private int screenWidth;
    private int screenHeight;
    private int xRatioOffset = 0;
    private int yRatioOffset = 0;
    private int tileSizeX, tileSizeY;

    private BufferedImage screenBuffer = new BufferedImage(1550, 850, BufferedImage.TYPE_INT_RGB);

    // constructor receives 2D array levelToPaint object and screen sizes as parameters
    public PaintLevel(Level levelToPaint, int screenWidth, int screenHeight) {
        this.levelToPaint = levelToPaint;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        //  class attribute arrayOfMapElements get levelToPaint object
        Floor[][] tileMap = levelToPaint.getTileMap();
        // size of each Floor calculated from screen size divided by number of rows / columns
        tileSizeX = (int) (screenWidth / tileMap.length);
        tileSizeY = (int) ((screenHeight - 80) / tileMap[0].length);
        tileSizeX = (int) min(tileSizeX, tileSizeY);
        // calculates offset to draw level in the center of the frame
        xRatioOffset = (int) (screenWidth - (tileMap.length * tileSizeX)) / 2;
        yRatioOffset = (int) 30 + (screenHeight - (tileMap[0].length * tileSizeX)) / 2;
    }

    // draw method draws on graphics object: top menu, and all tiles based on information 
    //  from 2D array levelToPaint 
    void draw(Graphics graphicsComp, int counter, int currentLevel) {
        //  drawing on graphics object 
        //  drawing on screenBuffer 
        Graphics graphics = screenBuffer.getGraphics();

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, screenWidth, screenHeight);
        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        graphics.setColor(Color.RED);
        graphics.drawString("Level: " + currentLevel + " /5", (screenWidth / 5), 50);
        graphics.setColor(Color.ORANGE);
        graphics.drawString("Moves: " + counter, 2 * (screenWidth / 5), 50);
        graphics.setColor(Color.lightGray);
        graphics.drawString("R - Restart", 3 * (screenWidth / 5), 50);
        graphics.drawString("Q - Quit", 4 * (screenWidth / 5), 50);
        // declaring 2D array tileMap to get from levelToPaint object by getter getTileMap()
        Floor[][] tileMap = levelToPaint.getTileMap();
        // iterate through each element of 2D array and adding texture to graphics object depending on Floor type
        for (int i = 0; i < tileMap.length; i++) {
            Floor[] tileRow = tileMap[i];
            for (int j = 0; j < tileRow.length; j++) {

//                switch (tileRow[j].getTileType()) {
                switch (tileRow[j].getClass().getSimpleName()) {
                    case "Floor":
                        drawTile(graphics, tileRow[j].getFloorTexture(), i, j);
                        break;
                    case "Wall":
                        drawTile(graphics, tileRow[j].getWallTexture(), i, j);
                        break;
                    case "Socket":
                        drawTile(graphics, tileRow[j].getSocketTexture(), i, j);
                        System.out.println(tileRow[j].getClass().getSimpleName());
                        break;
                    default:
                }
//                System.out.println(tileRow[j].getClass().getCanonicalName());

                //   when object of tileMap is Movable (Player or Box) 
                //   then type of movable object is retrieved by getter getMovable() from tileRow object
                //   depending of moveble Player or Box adequate texture is applied
                if (tileRow[j].getMovable() != null) {
                    switch (tileRow[j].getMovable().getClass().getSimpleName()) {
                        case "Player":
                            graphics.drawImage(tileRow[j].getPlayerTexture(),
                                    i * tileSizeX + xRatioOffset + 1, j * tileSizeX + yRatioOffset + 1,
                                    tileSizeX - 2, tileSizeX - 2, null);
                            break;
                        case "Box":
                            graphics.drawImage(tileRow[j].getBoxTexture(),
                                    i * tileSizeX + xRatioOffset, j * tileSizeX + yRatioOffset,
                                    tileSizeX - 1, tileSizeX - 1, null);
                            break;
                    }
                }
            }
        }

        //  rewrite graphics (memory area) to window (video memory) (component)
        graphicsComp.drawImage(screenBuffer, 0, 0, null);
    }

    // method draws tiles - places a texture in certain position of graphics object
    private void drawTile(Graphics graphics, BufferedImage texture, int i, int j) {
        graphics.drawImage(texture,
                i * tileSizeX + xRatioOffset,
                j * tileSizeX + yRatioOffset,
                tileSizeX, tileSizeX, null);
    }

    //  method load textures 
    //  if texture cannot be found - throws exception 
    //  Textures from Wolfenstain3D 1992, Copyrights: Developer(s) id Software; Publisher(s) Apogee Software; FormGen (Spear of Destiny); Director(s) Tom Hall; Designer(s) John Romero Tom Hall; Programmer(s) John Carmack  John Romero; Artist(s) Adrian Carmack; Composer(s) Robert Prince Series Wolfenstein; Platform(s) MS-DOS[show]; Release May 5, 1992
//    public void loadTextures(int currentLevel) throws IOException {
//        if (currentLevel == 1) {
//            textureWall = ImageIO.read(getClass().getResource("/wall_steel.png"));
//        } else {
//            textureWall = ImageIO.read(getClass().getResource("/wall_stone.png"));
//        }
//        textureBox = ImageIO.read(getClass().getResource("/box1.png"));
//        texturePlayer = ImageIO.read(getClass().getResource("/player6.png"));
//        textureFloor = ImageIO.read(getClass().getResource("/floor1.png"));
//        textureSocket = ImageIO.read(getClass().getResource("/floor1_socket4.png"));
//    }
}
