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

    //    deklaracja tablicy mapy
//nowy = nowy;
    Level levelNo1 = new Level();
    PaintLevel rysowanie;

    //  JFrame levcall method  paint automatically
    @Override
    public void paint(Graphics graphics) {
        graphics.drawRect(100, 30, 30, 40);
//        levelNo1.paintLevel(graphics);
        rysowanie.paintLevel(graphics);
//        levelNo1.paintLevel(levelNo1, graphics);
        graphics.drawString("This is going to be game", 200, 200);
    }

    // is going to call full screen
    public void run(DisplayMode displayMode) {
//        cokolwiek = newLevel.createLevel(6, 6);
        levelNo1.createLevel(6, 6);
//        Level newMap = Level.getLevel();
//        rysowanie = new PaintLevel(levelNo1);
        setBounds(100, 100, 400, 300);
        setVisible(true);
        setBackground(Color.ORANGE);
        setForeground(Color.GREEN);
        setFont(new Font("Arial", Font.PLAIN, 14));

        try {
            try {
                // pause gives chance to see if 
//                Thread.sleep(5000);
            } catch (Exception ex) {
            }
        } finally {
        }
    }

    /**
     * * @param args the command line arguments
     */
    public static void main(String[] args) {      //  setting up display mode to 800x600  16bits  default refresh rate //        DisplayMode displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);         // method sets full screen
        sokoban_mb sokoban = new sokoban_mb();    // run  will take displayMode as parameter
        sokoban.run(null);
    }
}
