package com.mb;

import com.mb.tiles.Box;
import com.mb.tiles.Floor;
import com.mb.tiles.Player;
import com.mb.tiles.Socket;
import com.mb.tiles.Wall;
import java.util.ArrayList;
import java.util.Scanner;

// class level functions:  load nth level from txt files and creates 2D array of Floor type elements
public class Level extends Coord {

    private int levelWidth = 0;
    private int levelHeight = 0;
    // playerX/Y  stores player coordinates as long as object level exists
    private int playerX;
    private int playerY;
   
    // declaration 
    private Floor[][] tileMap;
    private TextureLoader textureLoader = new TextureLoader();
//    private Floor floor;
//    private Wall wall;
//    private Box box;
//    private Player player;
//    private Socket socket;
    
    // method returns 2D array of Floor type elements
    public Floor[][] getTileMap() {
        return tileMap;
    }

    // method creates 2D array of Floor type elements based on information from loaded txt file
    // file number passed by method parameter
    public void createLevelFromFile(int levelNumberToLoad) {

        // file level?.txt leaded and divided by lines  (listOfStrings) stored in ArrayList lines
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
        // 2D array of Floor type elements is created
        Floor[][] tempLevel = new Floor[levelWidth][levelHeight];
        // each element of the 2D array is filled with Floor type object according to file characters (X or " " or . or @ or *)
        for (int nLine = 0; nLine < levelHeight; nLine++) {
            String tempString = listOfStrings.get(nLine);

            for (int nCharacter = 0; nCharacter < levelWidth; nCharacter++) {
                switch (tempString.charAt(nCharacter)) {
                    case 'X':
                        tempLevel[nCharacter][nLine] = new Wall(textureLoader.getWallTexture());
                        break;
                    case ' ':
                        tempLevel[nCharacter][nLine] = new Floor(textureLoader.getFloorTexture());
                        break;
                    case '.':
                        tempLevel[nCharacter][nLine] = new Socket(textureLoader.getSocketTexture());
                        break;
                    case '@':
                        tempLevel[nCharacter][nLine] = new Floor(textureLoader.getPlayerTexture());
                        tempLevel[nCharacter][nLine].setMovable(new Player(textureLoader.getPlayerTexture());
//                        Coord coord = new Coord(nCharacter,nLine);
                        playerX = nCharacter;
                        playerY = nLine;
                        break;
                    case '*':
                        tempLevel[nCharacter][nLine] = new Floor(textureLoader.getFloorTexture());
                        tempLevel[nCharacter][nLine].setMovable(new Box(textureLoader.getBoxTexture()));
                        break;
                    default:
                        System.out.println("invalid flag\n");
                        tempLevel[nCharacter][nLine] = new Floor(textureLoader.getFloorTexture());
                }
            }
        }
        // tileMap object point to tempLevel memory address
        tileMap = tempLevel;
    }

    // methods validating if player can move and if it's possible then shifts player position (add or decrease playerPosition by 1)
    // methods returns true if player could move, if not method returns false
    public boolean movePlayerBy(int x, int y) {
        //  If nothing is in the way of player
        if (tileMap[playerX + x][playerY + y].isEmpty()) {
            tileMap[playerX + x][playerY + y].setMovable(tileMap[playerX][playerY].getMovable());
            // previous position of player "movable" Floor is erased (null)
            tileMap[playerX][playerY].setMovable(null);
            playerX = playerX + x;
            playerY = playerY + y;
            return true;
        }
        // if Box is in front of Player and nothing behing Box
        if (tileMap[playerX + x][playerY + y].hasBox()
                && tileMap[playerX + 2 * x][playerY + 2 * y].isEmpty()) {
            tileMap[playerX + 2 * x][playerY + 2 * y].setMovable(tileMap[playerX + x][playerY + y].getMovable());
            tileMap[playerX + x][playerY + y].setMovable(tileMap[playerX][playerY].getMovable());
            // previous position of Player Floor is erased (null), Box object is copied one position forward
            tileMap[playerX][playerY].setMovable(null);
            playerX = playerX + x;
            playerY = playerY + y;
            // when player moved method returns true
            return true;
        }
        return false;
    }

    // method check every element of 2D Tiles objects and if any SOCKET DOES NOT contain BOX then method returns FALSE 
    // if ALL sockets contains BOX then method returns true
    public boolean isLevelFinished() {
        for (Floor[] arrayElement : tileMap) {
            for (Floor element : arrayElement) {
                if ("Socket".equals(element.getClass().getSimpleName())
                        && (element.getMovable() == null || !"Box".equals(element.getMovable().getClass().getSimpleName()))) {
                    return false;
                }
            }
        }
        return true;
    }

}
