package com.mb;

import static com.mb.Tile.WALL;
import static com.mb.Tile.BOX;
import static com.mb.Tile.SOCKET;
import static com.mb.Tile.EMPTY;
import static com.mb.Tile.PLAYER;
import static com.mb.Tile.FLOOR;
import java.util.ArrayList;
import java.util.Scanner;

// class level functions:  load nth level from txt files and creates 2D array of Tile type elements
public class Level {

    private int levelWidth = 0;
    private int levelHeight = 0;
    // playerPositionX/Y  stores player coordinates as long as object level exists
    private int playerPositionX;
    private int playerPositionY;
    private Tile[][] tileMap;

    // method returns 2D array of Tile type elements
    public Tile[][] getTileMap() {
        return tileMap;
    }

    // method creates 2D array of Tile type elements based on information from loaded txt file
    // file number passed by method parameter
    public void createLevelFromFile(int levelNumberToLoad) {

        // file level?.txt leaded and divided by lines  (lines) stored in ArrayList lines
        ArrayList<String> lines = new ArrayList<String>();
        Scanner sc = new Scanner(Level.class.getResourceAsStream("/level" + levelNumberToLoad + ".txt"));
        // level Width and  level Height is defined by length of string and number of strings
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        (lines).forEach(System.out::println);
        levelWidth = lines.get(0).length();
        levelHeight = lines.size();
        System.out.println(levelWidth + "  " + levelHeight);
        // 2D array of Tile type elements is created
        Tile[][] tempLevel = new Tile[levelWidth][levelHeight];
        // each element of the 2D array is filled with Tile type object according to file characters (X or " " or . or @ or *)
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
                    default:
                        System.out.println("invalid flag\n");
                        tempLevel[nCharacter][nLine] = new Tile(WALL);
                }
            }
        }
        // tileMap object point to tempLevel memory address
        tileMap = tempLevel;
    }

    // methods validating if player can move and if it's possible then shifts player position (add or decrease playerPosition by 1)
    // methods returns true if player could move, if not method returns false
    public boolean moveUp() {
        //  If box is in the way of player and nothing stans behind box 
        if (((tileMap[playerPositionX][playerPositionY - 1].getMovable() != null)
                && (tileMap[playerPositionX][playerPositionY - 2].getMovable() == null)
                && (tileMap[playerPositionX][playerPositionY - 1].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX][playerPositionY - 2].getTileType() == FLOOR)
                || ((tileMap[playerPositionX][playerPositionY - 2].getTileType() == SOCKET)
                && (tileMap[playerPositionX][playerPositionY - 2].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerPositionX][playerPositionY - 2].setMovable(tileMap[playerPositionX][playerPositionY - 1].getMovable());
            tileMap[playerPositionX][playerPositionY - 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionY--;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerPositionX][playerPositionY - 1].getMovable() == null)
                    && ((tileMap[playerPositionX][playerPositionY - 1].getTileType() == FLOOR)
                    || (tileMap[playerPositionX][playerPositionY - 1].getTileType() == SOCKET))) {
                //  player object copied imovement
                tileMap[playerPositionX][playerPositionY - 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                // previous position of player "movable" Tile is erased (null)
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionY--;
                // when player moved method returns true
                return true;
            }
        }
        return false;
    }

    public boolean moveDown() {
        //  If box is in the way of player and nothing stans behind box 
        if (((tileMap[playerPositionX][playerPositionY + 1].getMovable() != null)
                && (tileMap[playerPositionX][playerPositionY + 2].getMovable() == null)
                && (tileMap[playerPositionX][playerPositionY + 1].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX][playerPositionY + 2].getTileType() == FLOOR)
                || ((tileMap[playerPositionX][playerPositionY + 2].getTileType() == SOCKET)
                && (tileMap[playerPositionX][playerPositionY + 2].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerPositionX][playerPositionY + 2].setMovable(tileMap[playerPositionX][playerPositionY + 1].getMovable());
            tileMap[playerPositionX][playerPositionY + 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionY++;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerPositionX][playerPositionY + 1].getMovable() == null)
                    && ((tileMap[playerPositionX][playerPositionY + 1].getTileType() == FLOOR)
                    || (tileMap[playerPositionX][playerPositionY + 1].getTileType() == SOCKET))) {
                // previous position of player "movable" Tile is erased (null)
                tileMap[playerPositionX][playerPositionY + 1].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionY++;
                // when player moved method returns true
                return true;
            }
        }
        return false;
    }

    public boolean moveRight() {
        //  If box is in the way of player and nothing stans behind box 
        if (((tileMap[playerPositionX + 1][playerPositionY].getMovable() != null)
                && (tileMap[playerPositionX + 2][playerPositionY].getMovable() == null)
                && (tileMap[playerPositionX + 1][playerPositionY].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX + 2][playerPositionY].getTileType() == FLOOR)
                || ((tileMap[playerPositionX + 2][playerPositionY].getTileType() == SOCKET)
                && (tileMap[playerPositionX + 2][playerPositionY].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerPositionX + 2][playerPositionY].setMovable(tileMap[playerPositionX + 1][playerPositionY].getMovable());
            tileMap[playerPositionX + 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionX++;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerPositionX + 1][playerPositionY].getMovable() == null)
                    && ((tileMap[playerPositionX + 1][playerPositionY].getTileType() == FLOOR)
                    || (tileMap[playerPositionX + 1][playerPositionY].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerPositionX + 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionX++;
                // when player moved method returns true
                return true;
            }
        }
        return false;
    }

    public boolean moveLeft() {
        //  If box is in the way of player and nothing stans behind box 
        if (((tileMap[playerPositionX - 1][playerPositionY].getMovable() != null)
                && (tileMap[playerPositionX - 2][playerPositionY].getMovable() == null)
                && (tileMap[playerPositionX - 1][playerPositionY].getMovable().getTileType() == BOX))
                && ((tileMap[playerPositionX - 2][playerPositionY].getTileType() == FLOOR)
                || ((tileMap[playerPositionX - 2][playerPositionY].getTileType() == SOCKET)
                && (tileMap[playerPositionX - 2][playerPositionY].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerPositionX - 2][playerPositionY].setMovable(tileMap[playerPositionX - 1][playerPositionY].getMovable());
            tileMap[playerPositionX - 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerPositionX][playerPositionY].setMovable(null);
            playerPositionX--;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerPositionX - 1][playerPositionY].getMovable() == null)
                    && ((tileMap[playerPositionX - 1][playerPositionY].getTileType() == FLOOR)
                    || (tileMap[playerPositionX - 1][playerPositionY].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerPositionX - 1][playerPositionY].setMovable(tileMap[playerPositionX][playerPositionY].getMovable());
                tileMap[playerPositionX][playerPositionY].setMovable(null);
                playerPositionX--;
                // when player moved method returns true
                return true;
            }
        }
        return false;
    }

    // method check every element of 2D Tiles objects and if any SOCKET DOES NOT contain BOX then method returns FALSE 
    // if ALL sockets contains BOX then method returns true
    public boolean isLevelFinished() {
        for (Tile[] arrayElement : tileMap) {
            for (Tile element : arrayElement) {
                if (element.getTileType() == SOCKET
                        && (element.getMovable() == null || element.getMovable().getTileType() != BOX)) {
                    return false;
                }
            }
        }
        return true;
    }
}
