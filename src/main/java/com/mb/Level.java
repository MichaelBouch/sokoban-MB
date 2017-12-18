package com.mb;

import static com.mb.MapElement.WALL;
import static com.mb.MapElement.BOX;
import static com.mb.MapElement.SOCKET;
import static com.mb.MapElement.EMPTY;
import static com.mb.MapElement.PLAYER;
import static com.mb.MapElement.FLOOR;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    /**
     * // deklaracja obektu lvl klasy Level Level lvl; // deklaracja i
     * jednoczesne utworzeni obektu lvl klasy Level Level lvl = new Level(); //
     * konstruktor bezpareametroey Level lvl = new Level(123); // konstruktor z
     * prametrem typu int // utworzenie obiektu klasy Level i przypisanie do
     * obieku (bez deklaracji - mus byc zadeklarowabe wczesnije) lvl = new
     * Level(); // tylko utworzenie nowgo obiektu klasy Level (bez sensu
     * wyglada, ale w sciadze pisze): new Level(); // przekaznie nowo
     * utworzonego obiektu do metody zrobCos(new Level()); // wywolanie metody
     * // wywolanie metoddy z obiektem zadklarowany wczesniej zrobCos(lvl);
     */
    int levelWidth = 0;
    int levelHeight = 0;
    int playerPositionX;
    int playerPositionY;
    //  deklaracja tablicy obiektow MapElement
    MapElement[][] arrayOfMapElements;
    MapElement[][] arrayOfMovableElements;
    //  deklaracja tablicy stringow
    String[] loadedFile;
    ArrayList<String> lines;
    Level tempLevel;

    public MapElement[][] getArrayOfMapElements() {
        return arrayOfMapElements;
    }

    public void createLevelFromFile(int levelNumberToLoad) {

        if (Level.class.getResourceAsStream("/level" + levelNumberToLoad + ".txt") == null) {
            System.out.println(" Level No: " + levelNumberToLoad + " cannot be read from file");
            return;
        }

        ArrayList<String> lines = new ArrayList<String>();
        Scanner sc = new Scanner(Level.class.getResourceAsStream("/level" + levelNumberToLoad + ".txt"));
//            Scanner sc = new Scanner(new File("C://Svn//trunk//soko//src//main//resources//level" + levelNumberToLoad + ".txt"));
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        (lines).forEach(System.out::println);
        levelWidth = lines.get(0).length();
        levelHeight = lines.size();
        System.out.println(levelWidth + "  " + levelHeight);
        MapElement[][] tempLevel = new MapElement[levelWidth][levelHeight];
        for (int nLine = 0; nLine < levelHeight; nLine++) {
            String tempString = "";
            tempString = lines.get(nLine);

            for (int nCharacter = 0; nCharacter < levelWidth; nCharacter++) {
                switch (tempString.charAt(nCharacter)) {
                    case '#':
                        tempLevel[nCharacter][nLine] = new MapElement(WALL);
                        break;
                    case '-':
                        tempLevel[nCharacter][nLine] = new MapElement(FLOOR);
                        break;
                    case '+':
                        tempLevel[nCharacter][nLine] = new MapElement(SOCKET);
                        break;
                    case 'P':
                        tempLevel[nCharacter][nLine] = new MapElement(FLOOR);
                        tempLevel[nCharacter][nLine].setMovable(new MapElement(PLAYER));
                        playerPositionX = nCharacter;
                        playerPositionY = nLine;
                        break;
                    case 'B':
                        tempLevel[nCharacter][nLine] = new MapElement(FLOOR);
                        tempLevel[nCharacter][nLine].setMovable(new MapElement(BOX));
                        break;
                    case '_':
                        tempLevel[nCharacter][nLine] = new MapElement(EMPTY);
                        break;
                    default:
                        System.out.println("invalid flag\n");
                        tempLevel[nCharacter][nLine] = new MapElement(WALL);
                }
            }
        }
        arrayOfMapElements = tempLevel;
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
                            System.out.println("Got key = " + e.getKeyCode());
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
                moveLeft();
                System.out.println("LEFT");
                return true;
            case KeyEvent.VK_UP:
                moveUp();
                System.out.println("UP");
                return true;
            case KeyEvent.VK_RIGHT:
                moveRight();
                System.out.println("RIGHT");
                return true;
            case KeyEvent.VK_DOWN:
                moveDown();
                System.out.println("DOWN");
                return true;
            case KeyEvent.VK_Q:
                System.exit(0);
//            case KeyEvent.VK_R:
//                System.exit(1);
            default:
        }
        return false;

    }

    public void moveUp() {
//        boolean moved = false;
        if (((arrayOfMapElements[playerPositionX][playerPositionY - 1].getMovable() != null)
                && (arrayOfMapElements[playerPositionX][playerPositionY - 2].getMovable() == null)
                && (arrayOfMapElements[playerPositionX][playerPositionY - 1].getMovable().getElementType() == BOX))
                && ((arrayOfMapElements[playerPositionX][playerPositionY - 2].getElementType() == FLOOR)
                || ((arrayOfMapElements[playerPositionX][playerPositionY - 2].getElementType() == SOCKET)
                && (arrayOfMapElements[playerPositionX][playerPositionY - 2].getElementType() != WALL)))) {
            //  box movement
            arrayOfMapElements[playerPositionX][playerPositionY - 2].setMovable(
                    arrayOfMapElements[playerPositionX][playerPositionY - 1].getMovable());
            arrayOfMapElements[playerPositionX][playerPositionY - 1].setMovable(
                    arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
            //  player movement
            arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
            playerPositionY--;
//            moved = true;
            System.out.println("If 1  moved");
        } else {
            if ((arrayOfMapElements[playerPositionX][playerPositionY - 1].getMovable() == null)
                    && ((arrayOfMapElements[playerPositionX][playerPositionY - 1].getElementType() == FLOOR)
                    || (arrayOfMapElements[playerPositionX][playerPositionY - 1].getElementType() == SOCKET))) {
                //  player movement
                arrayOfMapElements[playerPositionX][playerPositionY - 1].setMovable(
                        arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
                arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
                System.out.println("If 2  moved");
                playerPositionY--;
            }
        }
    }

    public void moveDown() {
//        boolean moved = false;
        if (((arrayOfMapElements[playerPositionX][playerPositionY + 1].getMovable() != null)
                && (arrayOfMapElements[playerPositionX][playerPositionY + 2].getMovable() == null)
                && (arrayOfMapElements[playerPositionX][playerPositionY + 1].getMovable().getElementType() == BOX))
                && ((arrayOfMapElements[playerPositionX][playerPositionY + 2].getElementType() == FLOOR)
                || ((arrayOfMapElements[playerPositionX][playerPositionY + 2].getElementType() == SOCKET)
                && (arrayOfMapElements[playerPositionX][playerPositionY + 2].getElementType() != WALL)))) {
            //  box movement
            arrayOfMapElements[playerPositionX][playerPositionY + 2].setMovable(
                    arrayOfMapElements[playerPositionX][playerPositionY + 1].getMovable());
            arrayOfMapElements[playerPositionX][playerPositionY + 1].setMovable(
                    arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
            //  player movement
            arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
            playerPositionY++;
//            moved = true;
            System.out.println("If 1  moved");
        } else {
            if ((arrayOfMapElements[playerPositionX][playerPositionY + 1].getMovable() == null)
                    && ((arrayOfMapElements[playerPositionX][playerPositionY + 1].getElementType() == FLOOR)
                    || (arrayOfMapElements[playerPositionX][playerPositionY + 1].getElementType() == SOCKET))) {
                //  player movement
                arrayOfMapElements[playerPositionX][playerPositionY + 1].setMovable(
                        arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
                arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
                System.out.println("If 2  moved");
                playerPositionY++;
            }
        }
    }

    public void moveRight() {
//        boolean moved = false;
        if (((arrayOfMapElements[playerPositionX + 1][playerPositionY].getMovable() != null)
                && (arrayOfMapElements[playerPositionX + 2][playerPositionY].getMovable() == null)
                && (arrayOfMapElements[playerPositionX + 1][playerPositionY].getMovable().getElementType() == BOX))
                && ((arrayOfMapElements[playerPositionX + 2][playerPositionY].getElementType() == FLOOR)
                || ((arrayOfMapElements[playerPositionX + 2][playerPositionY].getElementType() == SOCKET)
                && (arrayOfMapElements[playerPositionX + 2][playerPositionY].getElementType() != WALL)))) {
            //  box movement
            arrayOfMapElements[playerPositionX + 2][playerPositionY].setMovable(
                    arrayOfMapElements[playerPositionX + 1][playerPositionY].getMovable());
            arrayOfMapElements[playerPositionX + 1][playerPositionY].setMovable(
                    arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
            //  player movement
            arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
            playerPositionX++;
//            moved = true;
            System.out.println("If 1  moved");
        } else {
            if ((arrayOfMapElements[playerPositionX + 1][playerPositionY].getMovable() == null)
                    && ((arrayOfMapElements[playerPositionX + 1][playerPositionY].getElementType() == FLOOR)
                    || (arrayOfMapElements[playerPositionX + 1][playerPositionY].getElementType() == SOCKET))) {
                //  player movement
                arrayOfMapElements[playerPositionX + 1][playerPositionY].setMovable(
                        arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
                arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
                System.out.println("If 2  moved");
                playerPositionX++;
            }
        }
    }

    public void moveLeft() {
//        boolean moved = false;
        if (((arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable() != null)
                && (arrayOfMapElements[playerPositionX - 2][playerPositionY].getMovable() == null)
                && (arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable().getElementType() == BOX))
                && ((arrayOfMapElements[playerPositionX - 2][playerPositionY].getElementType() == FLOOR)
                || ((arrayOfMapElements[playerPositionX - 2][playerPositionY].getElementType() == SOCKET)
                && (arrayOfMapElements[playerPositionX - 2][playerPositionY].getElementType() != WALL)))) {
            //  box movement
            arrayOfMapElements[playerPositionX - 2][playerPositionY].setMovable(
                    arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable());
            arrayOfMapElements[playerPositionX - 1][playerPositionY].setMovable(
                    arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
            //  player movement
            arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
            playerPositionX--;
//            moved = true;
            System.out.println("If 1  moved");
        } else {
            if ((arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable() == null)
                    && ((arrayOfMapElements[playerPositionX - 1][playerPositionY].getElementType() == FLOOR)
                    || (arrayOfMapElements[playerPositionX - 1][playerPositionY].getElementType() == SOCKET))) {
                //  player movement
                arrayOfMapElements[playerPositionX - 1][playerPositionY].setMovable(
                        arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
                arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
                System.out.println("If 2  moved");
                playerPositionX--;
            }
        }
    }
}

//    public void moveLeft() {
//        boolean moved = false;
//        if (((arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable() != null)
//                && (arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable().getElementType() == BOX))
//                && ((arrayOfMapElements[playerPositionX - 2][playerPositionY].getElementType() == FLOOR)
//                || ((arrayOfMapElements[playerPositionX - 2][playerPositionY].getElementType() == SOCKET)
//                && (arrayOfMapElements[playerPositionX - 2][playerPositionY].getElementType() != WALL)))) {
//            //  box movement
//            arrayOfMapElements[playerPositionX - 2][playerPositionY].setMovable(
//                    arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable());
//            arrayOfMapElements[playerPositionX - 1][playerPositionY].setMovable(
//                    arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
//            //  player movement
//            arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
//            playerPositionX--;
//            moved = true;
//            System.out.println("If 1  moved");
//        }
//
//        if (!moved
//                && ((arrayOfMapElements[playerPositionX - 1][playerPositionY].getMovable() != null)
//                && ((arrayOfMapElements[playerPositionX - 1][playerPositionY].getElementType() == FLOOR)
//                || (arrayOfMapElements[playerPositionX - 1][playerPositionY].getElementType() == SOCKET)))) {
//            //  player movement
//            arrayOfMapElements[playerPositionX - 1][playerPositionY].setMovable(
//                    arrayOfMapElements[playerPositionX][playerPositionY].getMovable());
//            arrayOfMapElements[playerPositionX][playerPositionY].setMovable(null);
//            System.out.println("If 2  moved");
//            playerPositionX--;
//        }
//    }
// }
//warehouseKeeper: MapElement
//LoadMap()
//checkForWin()
//restartLevel
//movePlater
//checkWhatsBehindCrate
//checkIfCrateOnDiamond
//moveMapElement():  – returns Boolean – moved or not
