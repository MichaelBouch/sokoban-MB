package com.mb;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

//  loading 5 textures with possible exception when a texture does not exists
public class TextureLoader {

    private BufferedImage floor;
    private BufferedImage wall;
    private BufferedImage socket;
    private BufferedImage box;
    private BufferedImage player;

    TextureLoader(int currentLevel) {
        try {
            switch (currentLevel) {
                case 1:
                    wall = ImageIO.read(getClass().getResource("/wall_stone.png"));
                    break;
                case 2:
                    wall = ImageIO.read(getClass().getResource("/wall_steel.png"));
                    break;
                case 3:
                    wall = ImageIO.read(getClass().getResource("/wall_brick.png"));
                    break;
                default:
                    wall = ImageIO.read(getClass().getResource("/wall_steel.png"));
                    break;
            }
            box = ImageIO.read(getClass().getResource("/box1.png"));
            player = ImageIO.read(getClass().getResource("/player6.png"));
            floor = ImageIO.read(getClass().getResource("/floor1.png"));
            socket = ImageIO.read(getClass().getResource("/floor1_socket4.png"));
        } catch (IllegalArgumentException | IOException ex) {
            System.out.println("Cannot load All textures   " + ex);
            //  prevalidation 
        }
    }

    public BufferedImage getFloor() {
        return floor;
    }

    public void setFloor(BufferedImage floor) {
        this.floor = floor;
    }

    public BufferedImage getWall() {
        return wall;
    }

    public void setWall(BufferedImage wall) {
        this.wall = wall;
    }

    public BufferedImage getSocket() {
        return socket;
    }

    public void setSocket(BufferedImage socket) {
        this.socket = socket;
    }

    public BufferedImage getBox() {
        return box;
    }

    public void setBox(BufferedImage box) {
        this.box = box;
    }

    public BufferedImage getPlayer() {
        return player;
    }

    public void setPlayer(BufferedImage player) {
        this.player = player;
    }
}
