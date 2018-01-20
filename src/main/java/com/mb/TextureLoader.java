/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class TextureLoader {

    private BufferedImage floor;
    private BufferedImage wall;
    private BufferedImage socket;
    private BufferedImage box;
    private BufferedImage player;

    public void loadTextures(int currentLevel) throws IOException {
        if (currentLevel == 1) {
            wall = ImageIO.read(getClass().getResource("/wall_stone.png"));
        } else {
            wall = ImageIO.read(getClass().getResource("/wall_steel.png"));
        }
        box = ImageIO.read(getClass().getResource("/box1.png"));
        player = ImageIO.read(getClass().getResource("/player6.png"));
        floor = ImageIO.read(getClass().getResource("/floor1.png"));
        socket = ImageIO.read(getClass().getResource("/floor1_socket4.png"));
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
