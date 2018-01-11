package com.mb;

import static com.mb.Tile.WALL;
import static com.mb.Tile.BOX;
import static com.mb.Tile.SOCKET;
import static com.mb.Tile.EMPTY;
import static com.mb.Tile.PLAYER;
import static com.mb.Tile.FLOOR;
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
    private int levelWidth = 0;
    private int levelHeight = 0;
    private int playerPositionX;
    private int playerPositionY;
    private Tile[][] tileMap;

    //  deklaracja tablicy obiektow Tile
    //   Tile[][] arrayOfMovableElements;
    //  deklaracja tablicy stringow
    public Tile[][] getTileMap() {
        return tileMap;
    }

    public void createLevelFromFile(int levelNumberToLoad) {

//        if (Level.class.getResourceAsStream("/level" + levelNumberToLoad + ".txt") == null) {
//            System.out.println(" Level No: " + levelNumberToLoad + " cannot be read from file");
//            return;
//        }
        ArrayList<String> lines = new ArrayList<String>();
        Scanner sc = new Scanner(Level.class.getResourceAsStream("/level" + levelNumberToLoad + ".txt"));
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        (lines).forEach(System.out::println);
        levelWidth = lines.get(0).length();
        levelHeight = lines.size();
        System.out.println(levelWidth + "  " + levelHeight);
        Tile[][] tempLevel = new Tile[levelWidth][levelHeight];
        for (int nLine = 0; nLine < levelHeight; nLine++) {
            String tempString = "";
            tempString = lines.get(nLine);

            for (int nCharacter = 0; nCharacter < levelWidth; nCharacter++) {
                switch (tempString.charAt(nCharacter)) {
                    case 'X':
                        tempLevel[nCharacter][nLine] = new Tile(WALL);
                        break;
                    case ' ':
                        tempLevel[nCharacter][nLine] = new Tile(FLOOR);
                        break;
                    case '.':
                        tempLevel[nCharacter][nLine] = new Tile(SOCKET);
                        break;
                    case '@':
                        tempLevel[nCharacter][nLine] = new Tile(FLOOR);
                        tempLevel[nCharacter][nLine].setMovable(new Tile(PLAYER));
                        playerPositionX = nCharacter;
                        playerPositionY = nLine;
                        break;
                    case '*':
                        tempLevel[nCharacter][nLine] = new Tile(FLOOR);
                        tempLevel[nCharacter][nLine].setMovable(new Tile(BOX));
                        break;
                    case '_':
                        tempLevel[nCharacter][nLine] = new Tile(EMPTY);
                        break;
                    default:
                        System.out.println("invalid flag\n");
                        tempLevel[nCharacter][nLine] = new Tile(WALL);
                }
            }
        }
        tileMap = tempLevel;
    }

    public boolean moveUp() {
        //  If 1  moved
        if (((tileMap[playerPositionX][playerPositionY - 1].getMovable() != null)
                && (tileMap[playerPositionX][playerPositionY - 2].getMovable() == null)
                && (tileMap[playerPositionX][playerPositionY - 1].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX][playerPositionY - 2].getTileType() == FLOOR)
                || ((tileMap[playerPositionX][playerPositionY - 2].getTileType() == SOCKET)
                && (tileMap[playerPositionX][playerPositionY - 2].getTileType() != WALL)))) {
            //  box movement
            tileMap[playerPositionX][playerPositionY - 2].setMovable(tileMap[playerPositionX][playerPositionY - 1].getMovable());
            tileMap[playerPositionX][playerPositionY - 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            //  player movement
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionY--;
            return true;
        } else {
            //  If 2  moved
            if ((tileMap[playerPositionX][playerPositionY - 1].getMovable() == null)
                    && ((tileMap[playerPositionX][playerPositionY - 1].getTileType() == FLOOR)
                    || (tileMap[playerPositionX][playerPositionY - 1].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerPositionX][playerPositionY - 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionY--;
                return true;
            }
        }
        return false;
    }

    public boolean moveDown() {
        if (((tileMap[playerPositionX][playerPositionY + 1].getMovable() != null)
                && (tileMap[playerPositionX][playerPositionY + 2].getMovable() == null)
                && (tileMap[playerPositionX][playerPositionY + 1].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX][playerPositionY + 2].getTileType() == FLOOR)
                || ((tileMap[playerPositionX][playerPositionY + 2].getTileType() == SOCKET)
                && (tileMap[playerPositionX][playerPositionY + 2].getTileType() != WALL)))) {
            //  box movement
            tileMap[playerPositionX][playerPositionY + 2].setMovable(tileMap[playerPositionX][playerPositionY + 1].getMovable());
            tileMap[playerPositionX][playerPositionY + 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            //  player movement
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionY++;
            return true;
        } else {
            if ((tileMap[playerPositionX][playerPositionY + 1].getMovable() == null)
                    && ((tileMap[playerPositionX][playerPositionY + 1].getTileType() == FLOOR)
                    || (tileMap[playerPositionX][playerPositionY + 1].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerPositionX][playerPositionY + 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionY++;
                return true;
            }
        }
        return false;
    }

    public boolean moveRight() {
        if (((tileMap[playerPositionX + 1][playerPositionY].getMovable() != null)
                && (tileMap[playerPositionX + 2][playerPositionY].getMovable() == null)
                && (tileMap[playerPositionX + 1][playerPositionY].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX + 2][playerPositionY].getTileType() == FLOOR)
                || ((tileMap[playerPositionX + 2][playerPositionY].getTileType() == SOCKET)
                && (tileMap[playerPositionX + 2][playerPositionY].getTileType() != WALL)))) {
            //  box movement
            tileMap[playerPositionX + 2][playerPositionY].setMovable(tileMap[playerPositionX + 1][playerPositionY].getMovable());
            tileMap[playerPositionX + 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            //  player movement
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionX++;
            return true;
        } else {
            if ((tileMap[playerPositionX + 1][playerPositionY].getMovable() == null)
                    && ((tileMap[playerPositionX + 1][playerPositionY].getTileType() == FLOOR)
                    || (tileMap[playerPositionX + 1][playerPositionY].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerPositionX + 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionX++;
                return true;
            }
        }
        return false;
    }

    public boolean moveLeft() {
        //  If 1  moved
        if (((tileMap[playerPositionX - 1][playerPositionY].getMovable() != null)
                && (tileMap[playerPositionX - 2][playerPositionY].getMovable() == null)
                && (tileMap[playerPositionX - 1][playerPositionY].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX - 2][playerPositionY].getTileType() == FLOOR)
                || ((tileMap[playerPositionX - 2][playerPositionY].getTileType() == SOCKET)
                && (tileMap[playerPositionX - 2][playerPositionY].getTileType() != WALL)))) {
            //  box movement
            tileMap[playerPositionX - 2][playerPositionY].setMovable(tileMap[playerPositionX - 1][playerPositionY].getMovable());
            tileMap[playerPositionX - 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            //  player movement
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionX--;
            return true;
        } else {
            //  If 2  moved"
            if ((tileMap[playerPositionX - 1][playerPositionY].getMovable() == null)
                    && ((tileMap[playerPositionX - 1][playerPositionY].getTileType() == FLOOR)
                    || (tileMap[playerPositionX - 1][playerPositionY].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerPositionX - 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionX--;
                return true;
            }
        }
        return false;
    }

    public boolean isLevelFinished() {
        for (Tile[] arrayElement : tileMap) {
            for (Tile element : arrayElement) {
                if (element.getTileType() == SOCKET && (element.getMovable() == null
                        || element.getMovable().getTileType() != BOX)) {
                    return false;
                }
            }
        }
        return true;
    }
}
