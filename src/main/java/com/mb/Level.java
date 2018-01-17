package com.mb;

import com.mb.Coord;
import com.mb.Tile;
import static com.mb.Tile.WALL;
import static com.mb.Tile.BOX;
import static com.mb.Tile.SOCKET;
import static com.mb.Tile.PLAYER;
import static com.mb.Tile.FLOOR;
import java.util.ArrayList;
import java.util.Scanner;

// class level functions:  load nth level from txt files and creates 2D array of Tile type elements
public class Level extends Coord{

    private int levelWidth = 0;
    private int levelHeight = 0;
    // playerX/Y  stores player coordinates as long as object level exists
    private int playerX;
    private int playerY;
    private Coord coord;
    // declaration 
    private Tile[][] tileMap;

    // method returns 2D array of Tile type elements
    public Tile[][] getTileMap() {
        return tileMap;
    }

    // method creates 2D array of Tile type elements based on information from loaded txt file
    // file number passed by method parameter
    public void createLevelFromFile(int levelNumberToLoad) {

        // file level?.txt leaded and divided by lines  (lines) stored in ArrayList lines
        ArrayList<String> listOfStrings = new ArrayList<>();
        Scanner loadedFile = new Scanner(Level.class.getResourceAsStream("/level" + levelNumberToLoad + ".txt"));
        // level Width and  level Height is defined by length of string and number of strings
        while (loadedFile.hasNextLine()) {
            listOfStrings.add(loadedFile.nextLine());
        }
        for (String s : listOfStrings) {
            System.out.println(s);
        }
        levelWidth = listOfStrings.get(0).length();
        levelHeight = listOfStrings.size();
        System.out.println("levelWidth: " + levelWidth + " levelHeight: " + levelHeight);
        // 2D array of Tile type elements is created
        Tile[][] tempLevel = new Tile[levelWidth][levelHeight];
        // each element of the 2D array is filled with Tile type object according to file characters (X or " " or . or @ or *)
        for (int nLine = 0; nLine < levelHeight; nLine++) {
            String tempString = listOfStrings.get(nLine);

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
//                        Coord coord = new Coord(nCharacter,nLine);
                        playerX = nCharacter;
                        playerY = nLine;
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
        if (((tileMap[playerX][playerY - 1].getMovable() != null)
                && (tileMap[playerX][playerY - 2].getMovable() == null)
                && (tileMap[playerX][playerY - 1].getMovable().getTileType() == BOX))
                && ((tileMap[playerX][playerY - 2].getTileType() == FLOOR)
                || ((tileMap[playerX][playerY - 2].getTileType() == SOCKET)
                && (tileMap[playerX][playerY - 2].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerX][playerY - 2].setMovable(tileMap[playerX][playerY - 1].getMovable());
            tileMap[playerX][playerY - 1].setMovable(tileMap[playerX][playerY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerX][playerY].setMovable(null);
            playerY--;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerX][playerY - 1].getMovable() == null)
                    && ((tileMap[playerX][playerY - 1].getTileType() == FLOOR)
                    || (tileMap[playerX][playerY - 1].getTileType() == SOCKET))) {
                //  player object copied imovement
                tileMap[playerX][playerY - 1].setMovable(tileMap[playerX][playerY].getMovable());
                // previous position of player "movable" Tile is erased (null)
                tileMap[playerX][playerY].setMovable(null);
                playerY--;
                // when player moved method returns true
                return true;
            }
        }
        return false;
    }

    public boolean moveDown() {
        //  If box is in the way of player and nothing stans behind box 
        if (((tileMap[playerX][playerY + 1].getMovable() != null)
                && (tileMap[playerX][playerY + 2].getMovable() == null)
                && (tileMap[playerX][playerY + 1].getMovable().getTileType() == BOX))
                && ((tileMap[playerX][playerY + 2].getTileType() == FLOOR)
                || ((tileMap[playerX][playerY + 2].getTileType() == SOCKET)
                && (tileMap[playerX][playerY + 2].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerX][playerY + 2].setMovable(tileMap[playerX][playerY + 1].getMovable());
            tileMap[playerX][playerY + 1].setMovable(tileMap[playerX][playerY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerX][playerY].setMovable(null);
            playerY++;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerX][playerY + 1].getMovable() == null)
                    && ((tileMap[playerX][playerY + 1].getTileType() == FLOOR)
                    || (tileMap[playerX][playerY + 1].getTileType() == SOCKET))) {
                // previous position of player "movable" Tile is erased (null)
                tileMap[playerX][playerY + 1].setMovable(tileMap[playerX][playerY].getMovable());
                tileMap[playerX][playerY].setMovable(null);
                playerY++;
                // when player moved method returns true
                return true;
            }
        }
        return false;
    }

    public boolean moveRight() {
        //  If box is in the way of player and nothing stans behind box 
        if (((tileMap[playerX + 1][playerY].getMovable() != null)
                && (tileMap[playerX + 2][playerY].getMovable() == null)
                && (tileMap[playerX + 1][playerY].getMovable().getTileType() == BOX))
                && ((tileMap[playerX + 2][playerY].getTileType() == FLOOR)
                || ((tileMap[playerX + 2][playerY].getTileType() == SOCKET)
                && (tileMap[playerX + 2][playerY].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerX + 2][playerY].setMovable(tileMap[playerX + 1][playerY].getMovable());
            tileMap[playerX + 1][playerY].setMovable(tileMap[playerX][playerY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerX][playerY].setMovable(null);
            playerX++;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerX + 1][playerY].getMovable() == null)
                    && ((tileMap[playerX + 1][playerY].getTileType() == FLOOR)
                    || (tileMap[playerX + 1][playerY].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerX + 1][playerY].setMovable(tileMap[playerX][playerY].getMovable());
                tileMap[playerX][playerY].setMovable(null);
                playerX++;
                // when player moved method returns true
                return true;
            }
        }
        return false;
    }

    public boolean moveLeft() {
        //  If box is in the way of player and nothing stans behind box 
        if (((tileMap[playerX - 1][playerY].getMovable() != null)
                && (tileMap[playerX - 2][playerY].getMovable() == null)
                && (tileMap[playerX - 1][playerY].getMovable().getTileType() == BOX))
                && ((tileMap[playerX - 2][playerY].getTileType() == FLOOR)
                || ((tileMap[playerX - 2][playerY].getTileType() == SOCKET)
                && (tileMap[playerX - 2][playerY].getTileType() != WALL)))) {
            //  then box is "shifted": Tile object in 2D array are copied
            tileMap[playerX - 2][playerY].setMovable(tileMap[playerX - 1][playerY].getMovable());
            tileMap[playerX - 1][playerY].setMovable(tileMap[playerX][playerY].getMovable());
            // previous position of player "movable" Tile is erased (null)
            tileMap[playerX][playerY].setMovable(null);
            playerX--;
            // when player moved method returns true
            return true;
        } else {
            //  If FLOOR or SOCKET and not WALL is on the way of player then player moves by 1 step
            if ((tileMap[playerX - 1][playerY].getMovable() == null)
                    && ((tileMap[playerX - 1][playerY].getTileType() == FLOOR)
                    || (tileMap[playerX - 1][playerY].getTileType() == SOCKET))) {
                //  player movement
                tileMap[playerX - 1][playerY].setMovable(tileMap[playerX][playerY].getMovable());
                tileMap[playerX][playerY].setMovable(null);
                playerX--;
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
