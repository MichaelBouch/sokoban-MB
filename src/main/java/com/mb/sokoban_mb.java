package com.mb;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 * * * @MichalBoduch user
 */
//  JFrame calls method  paint automatically
public class sokoban_mb extends JFrame {

    Level levelNo1 = new Level();
    Level levelFromFile = new Level();
    PaintLevel rysowanie;
    PaintLevel rysowanie2;

    //  JFrame levcall method  paint automatically
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        rysowanie.paintLevel(graphics);
 //       System.out.println("przerysowuje");

    }

    //   is going to call full screen
    public void run(DisplayMode displayMode) throws FileNotFoundException {
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        //   cokolwiek = newLevel.createLevel(6, 6);
        setFont(new Font("Arial", Font.PLAIN, 14));
        levelFromFile.createLevelFromFile(5);
        levelFromFile.startKeyboardHandl();

        setBounds(30, 0, 1200, 850);
        rysowanie = new PaintLevel(levelFromFile, 1200, 850, 0, 0);
        setVisible(true);

    }

    /**
     * * * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {      //  setting up display mode to 800x600  16bits  default refresh rate //        DisplayMode displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);         // method sets full screen
        sokoban_mb sokoban = new sokoban_mb();    // run  will take displayMode as parameter
        sokoban.run(null);
    }
}
