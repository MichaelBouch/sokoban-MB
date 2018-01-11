package com.mb;

import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Logger;

public class Game {

    private int movesCounter;
    private int currentLevel;
    private Level level;
    private PaintLevel rysowanie;
    private boolean initalized;

    public Game() {
        initalized = false;
    }

    public void drawLevel(Graphics graphics) {
        rysowanie.draw(graphics, movesCounter, currentLevel);
    }

    //  prevalidation 
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
        level = new Level();
        level.createLevelFromFile(currentLevel);
        rysowanie = new PaintLevel(level, width, height);
        try {
            rysowanie.loadTextures(currentLevel);
        } catch (IllegalArgumentException | IOException ex) {
            System.out.println("Cannot load All textures   " + ex);
            return false;
        } 
        initalized = true;
        return true;
    } 

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

    public boolean handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
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
                System.exit(0);
            case KeyEvent.VK_R:
                loadLevel(currentLevel);

                return true;
            default:
        }

        return false;
    }

    public boolean isInitalized() {
        return initalized;
    }

}
