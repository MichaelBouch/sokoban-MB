package com.mb;

import java.awt.DisplayMode;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * * * * * @MichalBoduch user
 */
//  class sokoban_mb  inherits  JFrame methods/functions
public class sokoban_mb extends JFrame {
    
    //    ??????????     declaring game class
    private Game game;
    //    ?????????????
    @Override
    //  redraws level - when needed
    public void paint(Graphics graphics) {
        if (game != null && game.isInitalized()) {
            game.drawLevel(graphics);
        }
    }

    //   method run   ??????????????????????
    public void run(DisplayMode displayMode) {
        // setting JFrame bounds
        setBounds(0, 0, 1200, 850);
        // setts JFrame visible
        setVisible(true);
        // creates object from Game() class
        game = new Game();
        // calls loadLevel from game object to load first level
        game.loadLevel(1, 1200, 850);
        // calls startKeyboardHandl method to start keyboard manager
        game.startKeyboardHandl();
        // redraws (displays) level and top menu before any key is pressed
        repaint();
    }

    /**
     * * * @param args the command line arguments
     */
    //  main class   ?????????????????????
    public static void main(String[] args) {     
        // creates sokoban object from sokoban_mb class (type of JFrame) 
        sokoban_mb sokoban = new sokoban_mb();    // run  will take displayMode as parameter
        // calls method run from sokoban object
        sokoban.run(null);
    }
}
