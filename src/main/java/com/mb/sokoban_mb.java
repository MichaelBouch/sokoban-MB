package com.mb;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 * * * * * @MichalBoduch user
 */
//  JFrame calls method  paint automatically
public class sokoban_mb extends JFrame {

    private Game game;

    @Override
    public void paint(Graphics graphics) {
        if (game != null && game.isInitalized()) {
            game.drawLevel(graphics);
        }
    }

    //   is going to call full screen
    public void run(DisplayMode displayMode) {
        setBounds(0, 0, 1200, 850);
        setVisible(true);
        game = new Game();
        game.loadLevel(1, 1200, 850);
        game.startKeyboardHandl();
        repaint();
    }

    /**
     * * * @param args the command line arguments
     */
    public static void main(String[] args) {     //  setting up display mode to 800x600  16bits  default refresh rate //        DisplayMode displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);         // method sets full screen
        sokoban_mb sokoban = new sokoban_mb();    // run  will take displayMode as parameter
        sokoban.run(null);

    }
}
