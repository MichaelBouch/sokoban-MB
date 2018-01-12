package com.mb;

import org.junit.Test;
import static org.junit.Assert.*;

public class LevelTest {

    //  test for equal size of array created from txt file
    //  Test of getTileMap method, of class Level.
    //  loaded size of txt file. 
    //  the test file is placed in test/resources/level1.txt and contains the following map: 
    //  XXXXXX
    //  X@* .X
    //  X   XX
    //  XXXXXX
    
    @Test
    public void mapSizeTest() {
        System.out.println("getTileMap");
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        Tile[][] tileMap = testLevelObject.getTileMap();
        // test assert that map width is 6
        assertEquals(6, tileMap.length);
        // test assert that map height is 4
        assertEquals(4, tileMap[0].length);
    }

    //  test Level class and methods: createLevelFromFile, isLevelFinished from test.txt file
    @Test
    public void moveDownTest() {
        System.out.println("getTileMap");
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        //  test asserts if player can move - moveDown() method will return true
        assertTrue(testLevelObject.moveDown());
        //  test asserts if player will move down once and level will not be accomplished
        assertFalse(testLevelObject.isLevelFinished());
        //  test asserts if player can move - moveDown() method will return true
        assertFalse(testLevelObject.moveDown());
        //  test asserts if player will move down once and level will not be accomplished
        assertFalse(testLevelObject.isLevelFinished());
    }

    //  test isLevelFinished if player can move right twice and isLevelFinished will return true vale - level finished
    @Test
    public void levelFinishTest() {
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        //  test asserts that player can move 1 step right - moveRight() method will return true
        assertTrue(testLevelObject.moveRight());
        //  test asserts if player will move right once and level will not be accomplished
        assertFalse(testLevelObject.isLevelFinished());
        //  test asserts that player can move 1 step down - moveRight() method will return true
        assertTrue(testLevelObject.moveRight());
        //  test asserts if player will move right once and level will be accomplished
        assertTrue(testLevelObject.isLevelFinished());
    }
    
    
}
