package com.mb;

import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Logger;

// Game class methods does:
//  Load levels (calls nethod createLevelFromFile())
//  paints level: drawLevel method
//  reads keyboard keys pressed: startKeyboardHandl dispatchKeyEvent methods
public class Game {

    private int movesCounter; // stores number of moves in each level
    private int currentLevel; // stores number of current level
    private Level level;  // declaration of level object
    private PaintLevel paintLevel; // declaration of paintLevel object
    private boolean initalized;

    // Game class constructor
    public Game() {
        initalized = false;
    }

    // drawing level including top indicators (calls paintLevel object)
    public void drawLevel(Graphics graphics) {
        paintLevel.draw(graphics, movesCounter, currentLevel);
    }

    //  prevalidation 
    //  overloading methods
    //  loading level methods catching exception when a texture cannot be found
    public boolean loadLevel() {
        return loadLevel(1);
    }

    public boolean loadLevel(int levelNumber) {
        return loadLevel(levelNumber, 1200, 850);
    }

    public boolean loadLevel(int levelNumber, int width, int height) {
        initalized = false;
        movesCounter = 0;
        currentLevel = levelNumber;
        // creating object level
        level = new Level();
        // loading map from txt file number - currentLevel
        level.createLevelFromFile(currentLevel);
        paintLevel = new PaintLevel(level, width, height);
        // loading textures with possible exception when a texture does not exists
        try {
            paintLevel.loadTextures(currentLevel);
        } catch (IllegalArgumentException | IOException ex) {
            System.out.println("Cannot load All textures   " + ex);
            return false;
        }
        initalized = true;
        return true;
    }

    // keyboard manager method
    public void startKeyboardHandl() {
        KeyboardFocusManager.
                getCurrentKeyboardFocusManager().
                addKeyEventDispatcher(
                        // new object listening for keyboard events
                        new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {

                        if (e.getID() == KeyEvent.KEY_RELEASED) {
                            //   System.out.println("Got key = " + e.getKeyCode());
                            boolean needRepaint = handleKeyPress(e.getKeyCode());
                            if (needRepaint) {
                                System.out.println("moves counter: " + movesCounter);
                                if (level.isLevelFinished()) {
                                    loadLevel(currentLevel + 1);
                                }
                                e.getComponent().repaint();

                            }
                            return needRepaint;
                        }
                        return false;
                    }
                }
                );
    }

    // keyboard event handling - different methods calls depending of keys pressed 
    public boolean handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                // calls moveLeft() method of object level
                // when methods returns true (player have moved) then movesCounter increased by 1
                if (level.moveLeft()) {
                    movesCounter++;
                }
                return true;
            case KeyEvent.VK_UP:
                if (level.moveUp()) {
                    movesCounter++;
                }
                return true;
            case KeyEvent.VK_RIGHT:
                if (level.moveRight()) {
                    movesCounter++;
                }
                return true;
            case KeyEvent.VK_DOWN:
                if (level.moveDown()) {
                    movesCounter++;
                }
                return true;
            case KeyEvent.VK_Q:
                // program exit
                System.exit(0);
            case KeyEvent.VK_R:
                // restart - reloading current level
                loadLevel(currentLevel);
                return true;
            default:
        }
        return false;
    }

    // ???????????????????????
    public boolean isInitalized() {
        return initalized;
    }

}
