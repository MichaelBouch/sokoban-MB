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
    int elementType;
    public static int EMPTY = 0;
    public static int WALL = 1;
    public static int BOX = 2;
    public static int PLAYER = 3;
    public static int SOCKET = 4;
    public static int WIDTH = 50;
    public static int HEIGHT = 50;

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public MapElement( int elementType) {
        this.elementType = elementType;
    }

    
}
