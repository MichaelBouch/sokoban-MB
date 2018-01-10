package com.mb;

import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Game {

    private int movesCounter = 0;
    private int currentLevel = 1;
    private Level level;
    private PaintLevel rysowanie;

    public Game() {
    }

    public void drawLevel(Graphics graphics) {
        rysowanie.paintLevel(graphics);
//        rysowanie.paintLevel(bf.getGraphics());
//        graphics.drawImage(bf, 0, 0, null);
    }

    public void loadLevel(int levelNumber) {
        currentLevel = levelNumber;
        level = new Level();
        level.createLevelFromFile(currentLevel);
        rysowanie = new PaintLevel(level, 1200, 850);
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
            //           case KeyEvent.VK_R:
            //             restart = new sokoban_mb().launch();
//            case KeyEvent.VK_R:
//                System.exit(1);
            default:
        }
        System.out.println(movesCounter);
        return false;
    }

}
