//interface MapElement
package com.mb;

import java.awt.Graphics;
import javafx.scene.paint.Color;
import javax.swing.text.StyleConstants;

/**
 *
 * @author user
 */
public class MapElement {

    //  0 EMPTY, 1 WALL, 2 BOX 
    private int elementType;
    // masz zadeklarowany obiekt movable klasy MapElement w kazdym MapElement 
    // mozesz wstawic kolejny
    private MapElement movable;

    public MapElement getMovable() {
        return movable;
    }

    public void setMovable(MapElement movable) {
        this.movable = movable;
    }
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int BOX = 2;
    public static final int PLAYER = 3;
    public static final int SOCKET = 4;
    public static final int EMPTY = 5;
//    public static int WIDTH = 50;
//    public static int HEIGHT = 50;

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public MapElement(int elementType) {
        this.elementType = elementType;
    }

}
