package com.mb;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * * * * * @MichalBoduch user
 */
//  class SokobanMb  inherits  JFrame methods/functions
public class SokobanMb extends JFrame {

    //   declaring game class
    private Game game;
    public static final int screenWidth = 1550;
    public static final int screenHeight = 850;

    //  overrides JFrame paint method
    @Override
    //  redraws level - when needed
    public void paint(Graphics graphics) {
        // when game is initalized and object game is not null then draw level and menu
        if (game != null && game.isInitalized()) {
            game.drawLevel(graphics);
        }
    }

    //   method initializeGame sta
    public void initializeGame() {
        // setting JFrame bounds
        setBounds(0, 0, screenWidth, screenHeight);
        // setts JFrame visible
        setVisible(true);
        // creates object from Game() class
        game = new Game();
        // calls loadLevel from game object to load first level with given screen size
        game.loadLevel(1);
        
        // calls startKeyboardHandl method to start keyboard manager
        game.startKeyboardHandl();
        // redraws (displays) level and top menu before any key is pressed
        repaint();
    }

    /**
     * * * @param args the command line arguments
     */
    //  main class -starts program ( creates sokoban object from SokobanMb class)
    public static void main(String[] args) {
        // creates sokoban object from SokobanMb class (type of JFrame) 
        SokobanMb sokoban = new SokobanMb();    // run  will take displayMode as parameter
        // calls method run from sokoban object
        sokoban.initializeGame();
    }
}
