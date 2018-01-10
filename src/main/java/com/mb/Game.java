package com.mb;

import static com.mb.MapElement.BOX;
import static com.mb.MapElement.FLOOR;
import static com.mb.MapElement.SOCKET;
import static com.mb.MapElement.WALL;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class Game {

    int movesCounter = 0;
    int currentLevel = 1;
    Level level;
    boolean moved;

    public Game(Level level) {
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
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
            //           case KeyEvent.VK_R:
            //             restart = new sokoban_mb().launch();
//            case KeyEvent.VK_R:
//                System.exit(1);
            default:
        }
        return false;
    }

    public int getMovesCounter() {
        return movesCounter;
    }

    public void setMovesCounter(int movesCounter) {
        this.movesCounter = movesCounter;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

}
