package com.mb;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * * * * @MichalBoduch user
 */
//  JFrame calls method  paint automatically
public class sokoban_mb extends JFrame {

    //sokoban_mb.setBackground(new Color(0, 0, 0, 0));
    Level levelFromFile = new Level();
    PaintLevel rysowanie;
    Game game;
    int currentLevel = 1;
    BufferedImage bf = new BufferedImage(1200, 850, BufferedImage.TYPE_INT_RGB);

    //  JFrame levcall method  paint automatically
    @Override
    public void paint(Graphics graphics) {
        rysowanie.setMovesCounter(game.getMovesCounter());
        rysowanie.paintLevel(bf.getGraphics());
        if (levelFromFile.isLevelFinished()) {
            levelFromFile.createLevelFromFile(++currentLevel);
        }
        graphics.drawImage(bf, 0, 0, null);
    }

    //   is going to call full screen
    public void run(DisplayMode displayMode) throws FileNotFoundException {

        //   cokolwiek = newLevel.createLevel(6, 6);
        setFont(new Font("Arial", Font.PLAIN, 20));
        levelFromFile.createLevelFromFile(currentLevel);
        game = new Game(levelFromFile);
        game.setLevel(levelFromFile);
//        Game.startKeyboardHandl();
        game.startKeyboardHandl();
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
