/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
//  JFrame call method  paint automatically
public class sokoban_mb extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //  setting up display mode to 800x600  16bits  default refresh rate
        DisplayMode displayMode = new DisplayMode(600, 42340, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        // method sets full screen
        sokoban_mb sokoban = new sokoban_mb();
        // run  will take displayMode as parameter
        sokoban.run(displayMode);
    }

    // is going to call full screen
    public void run(DisplayMode displayMode) {
        setBackground(Color.ORANGE);
        setForeground(Color.GREEN);
        setFont(new Font("Arial", Font.PLAIN, 14));

        Screen screen = new Screen();
        try {
            // changing to full screen
            screen.setFullScreen(displayMode, this);
            try {
                // pause gives chance to see if 
                Thread.sleep(5000);
            } catch (Exception ex) {
            }
        } finally {
            // restoring to normal size
            screen.restoreScreen();
        }
    }

    //  JFrame call method  paint automatically
    public void paint(Graphics graphics) {
        //  if graphics is not a Graphics2D object is going to create graphics2d object from graphics
        //  to be able to apply Antialiasing
//        if (graphics instanceof Graphics2D){
//            Graphics2D graphics2d = (Graphics2D)graphics;
//            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        }
        graphics.drawRect(100, 30, 30, 40);
        graphics.drawString("This is going to be game", 200, 200);
    }
}
