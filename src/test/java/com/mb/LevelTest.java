package com.mb;

import com.mb.tiles.Floor;
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
    
    
    // testing map loader if method createLevelFromFile() can load the test level file  
    // (/src/test/test/resources/level1.txt)  and properly read width and height
    @Test
    public void mapSizeTest() {
        System.out.println("getTileMap");
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        Floor[][] tileMap = testLevelObject.getTileMap();
        // assert that map width is 6
        assertEquals(6, tileMap.length);
        // assert that map height is 4
        assertEquals(4, tileMap[0].length);
    }

    //  Test of isLevelFinished method - if player can move down twice 
    //  then isLevelFinished() method will return false value - level is not accomplished
    @Test
    public void moveDownWithoutFinishingLevelTest() {
        System.out.println("getTileMap");
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        //  assert if player can move - moveDown() method should return true
        assertTrue(testLevelObject.movePlayerBy(0, 1));
        //  assert if player will move down once and level should not be accomplished
        assertFalse(testLevelObject.isLevelFinished());
        //  assert if player can move - moveDown() method should return true
        assertFalse(testLevelObject.movePlayerBy(0, 1));
        //  assert if player will move down once and level should not be accomplished
        assertFalse(testLevelObject.isLevelFinished());
    }

    //   test of isLevelFinished method if player can move right twice then isLevelFinished 
    //  should  return true value - level finished
    @Test
    public void moveRightShoulFinishLevelTest() {
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        //  asserts that player can move 1 step right - moveRight() method should return true
        assertTrue(testLevelObject.movePlayerBy(1, 0));
        //  assert if player will move right once should level should not be accomplished
        assertFalse(testLevelObject.isLevelFinished());
        //  assert that player can move 1 step right - moveRight() method should return true
        assertTrue(testLevelObject.movePlayerBy(1, 0));
        //  assert if player will move right once  then level should be accomplished – testLevelObject.isLevelFinished() will return true
        assertTrue(testLevelObject.isLevelFinished());
    }
}