package com.mb;

import static com.mb.MapElement.WALL;
import static com.mb.MapElement.BOX;
import static com.mb.MapElement.SOCKET;
import static com.mb.MapElement.EMPTY;
import static com.mb.MapElement.PLAYER;
import static com.mb.MapElement.FLOOR;
import java.awt.Graphics;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    int levelWidth = 0;
    int levelHeight = 0;
    //  deklaracja tablicy obiektow MapElement
    MapElement[][] arrayOfMapElements;
    String[] loadedFile;
    ArrayList<String> lines;
    Level tempLevel;

    public MapElement[][] getArrayOfMapElements() {
        return arrayOfMapElements;
    }

    //  metoda tworzenia tablicy z obiektow MapElement
    //  metoda przypisuje wynik do arrayOfMapElements
    public void createLevel(int x, int y) {
        MapElement[][] tempLevel = new MapElement[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                tempLevel[j][i] = new MapElement(WALL);
            }
        }
        arrayOfMapElements = tempLevel;
        //  return tempLevel;
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
                        tempLevel[nCharacter][nLine] = new MapElement(PLAYER);
                        break;
                    case 'B':
                        tempLevel[nCharacter][nLine] = new MapElement(BOX);
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

}

//warehouseKeeper: MapElement
//LoadMap()
//checkForWin()
//restartLevel
//movePlater
//checkWhatsBehindCrate
//checkIfCrateOnDiamond
//moveMapElement():  – returns Boolean – moved or not
