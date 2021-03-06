package com.mb;

import static com.mb.SokobanMb.screenHeight;
import static com.mb.SokobanMb.screenWidth;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

//  Game class methods:
//  Load levels (calls nethod createLevelFromFile())
//  paints level: drawLevel method
//  reads keyboard keys pressed: startKeyboardHandl dispatchKeyEvent methods
public class Game {

    // stores number of moves in each level
    private int movesCounter;
    // stores number of current level
    private int currentLevel;
    // declaration of level object
    private Level level;
    // declaration of paintLevel object
    private PaintLevel paintLevel;
    private boolean initalized;

    // Game class constructor
    public Game() {
        initalized = false;
    }

    // drawing level including top indicators (calls paintLevel object)
    public void drawLevel(Graphics graphics) {
        paintLevel.draw(graphics, movesCounter, currentLevel);
    }

    //  overloading methods
    //  loading level methods catching exception when a texture cannot be found
    public boolean loadLevel() {
        return loadLevel(1);
    }

    public boolean loadLevel(int levelNumber) {
        initalized = false;
        movesCounter = 0;
        currentLevel = levelNumber;
        // creating object level
        level = new Level();
        // loading map from txt file number - currentLevel
        level.createLevelFromFile(currentLevel);
        paintLevel = new PaintLevel(level, screenWidth, screenHeight);
        initalized = true;
        return true;
    }

    // keyboard manager method
    public void startKeyboardHandl() {
        // register key event 
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(gameKeyDispatcher);
    }

    // new object listening for keyboard events
    private KeyEventDispatcher gameKeyDispatcher = new KeyEventDispatcher() {
        // keyboard manager call this method on every keyevent
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {

            if (e.getID() == KeyEvent.KEY_PRESSED) {
                //   System.out.println("Got key = " + e.getKeyCode());
                boolean gameStateChanged = handleKeyPress(e.getKeyCode());
                if (gameStateChanged) {
                    if (level.isLevelFinished()) {
                        loadLevel(currentLevel + 1);
                    }
                    // returns source component and calls repaint on it
                    e.getComponent().repaint();
                }
                return gameStateChanged;
            }
            return false;
        }
    };

    // keyboard event handling - different methods calls depending of keys pressed 
    public boolean handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                // calls moveLeft() method of object level
                // when methods returns true (player have moved) then movesCounter increased by 1
                if (level.movePlayerBy(-1, 0)) {
                    movesCounter++;
                    return true;
                }
                break;
            case KeyEvent.VK_UP:
                if (level.movePlayerBy(0, -1)) {
                    movesCounter++;
                    return true;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (level.movePlayerBy(1, 0)) {
                    movesCounter++;
                    return true;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (level.movePlayerBy(0, 1)) {
                    movesCounter++;
                    return true;
                }
                break;
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

    //  getter to return initialized state of game
    public boolean isInitalized() {
        return initalized;
    }
}
