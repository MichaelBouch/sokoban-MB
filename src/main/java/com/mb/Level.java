package com.mb;

import static com.mb.MapElement.HEIGHT;
import static com.mb.MapElement.WALL;
import static com.mb.MapElement.WIDTH;
import java.awt.Graphics;

public class Level {

    //  deklaracja tablicy obiektow MapElement
    MapElement[][] arrayOfMapElements;
    
    //  metoda tworzenia tablicy z obiektow MapElement
    //  metoda przypisuje wynik do arrayOfMapElements
    public void createLevel(int x, int y) {
        MapElement[][] tempLevel = new MapElement[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tempLevel[j][i] = new MapElement(WALL);
            }
        }
        arrayOfMapElements =  tempLevel;
    //  return tempLevel;
    }

    
    public MapElement[][] getArrayOfMapElements() {
        return arrayOfMapElements;
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
