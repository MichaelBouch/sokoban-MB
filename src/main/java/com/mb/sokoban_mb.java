package com.mb;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * * * @MichalBoduch user
 */
//  JFrame calls method  paint automatically
public class sokoban_mb extends JFrame {

    //sokoban_mb.setBackground(new Color(0, 0, 0, 0));
    //sokoban_mb.frame.setOpacity(0.0f);

    Level levelNo1 = new Level();
    Level levelFromFile = new Level();
    PaintLevel rysowanie;
    PaintLevel rysowanie2;

    //  JFrame levcall method  paint automatically
    //       Redraw przerysowuje;
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawRect(0, 0, getWidth(), getHeight());
        rysowanie.paintLevel(graphics);
    }

    //   is going to call full screen
    public void run(DisplayMode displayMode) throws FileNotFoundException {

        //   cokolwiek = newLevel.createLevel(6, 6);
        setFont(new Font("Arial", Font.PLAIN, 20));

        levelFromFile.createLevelFromFile(1);
        levelFromFile.startKeyboardHandl();
        setBackground(Color.yellow);
        setBounds(0, 0, 1200, 850);
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
