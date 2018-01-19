/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb;

import com.mb.tiles.Box;
import com.mb.tiles.Floor;
import com.mb.tiles.Player;
import com.mb.tiles.Socket;
import com.mb.tiles.Wall;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class TileLoader {

    public Floor floor ;
    public Wall wall;
    public Socket socket;
    public Box box;
    public Player player;
    
    
    public void loadTextures(int currentLevel) throws IOException {
        if (currentLevel == 1) {
            wall.setTexture(ImageIO.read(getClass().getResource("/wall_steel.png")));
        } else {
            wall.setTexture(ImageIO.read(getClass().getResource("/wall_stone.png")));
        }
        box.setTexture(ImageIO.read(getClass().getResource("/box1.png")));
        player = new Player(ImageIO.read(getClass().getResource("/player6.png")));
        floor = new Floor(ImageIO.read(getClass().getResource("/floor1.png")));
        socket = new Socket(ImageIO.read(getClass().getResource("/floor1_socket4.png"))); 
    }
}
