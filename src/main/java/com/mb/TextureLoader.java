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

    private BufferedImage floorTexture;
    private BufferedImage wallTexture;
    private BufferedImage socketTexture;
    private BufferedImage boxTexture;
    private BufferedImage playerTexture;

    public void loadTextures(int currentLevel) throws IOException {
        if (currentLevel == 1) {
            wallTexture = (ImageIO.read(getClass().getResource("/wall_steel.png")));
        } else {
            wallTexture = (ImageIO.read(getClass().getResource("/wall_stone.png")));
        }
        boxTexture = (ImageIO.read(getClass().getResource("/box1.png")));
        playerTexture = (ImageIO.read(getClass().getResource("/player6.png")));
        floorTexture = (ImageIO.read(getClass().getResource("/floor1.png")));
        socketTexture = (ImageIO.read(getClass().getResource("/floor1_socket4.png")));
    }

    public BufferedImage getFloorTexture() {
        return floorTexture;
    }

    public void setFloorTexture(BufferedImage floorTexture) {
        this.floorTexture = floorTexture;
    }

    public BufferedImage getWallTexture() {
        return wallTexture;
    }

    public void setWallTexture(BufferedImage wallTexture) {
        this.wallTexture = wallTexture;
    }

    public BufferedImage getSocketTexture() {
        return socketTexture;
    }

    public void setSocketTexture(BufferedImage socketTexture) {
        this.socketTexture = socketTexture;
    }

    public BufferedImage getBoxTexture() {
        return boxTexture;
    }

    public void setBoxTexture(BufferedImage boxTexture) {
        this.boxTexture = boxTexture;
    }

    public BufferedImage getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(BufferedImage playerTexture) {
        this.playerTexture = playerTexture;
    }
}
