package com.mb;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class LevelTest {

    /**
     * Test of getTileMap method, of class Level.
     */
     
    //  test of size of map
    @Test
    public void testMapSize() {
        System.out.println("getTileMap");
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        Tile[][] tileMap = testLevelObject.getTileMap();
        assertEquals(6, tileMap.length);
        assertEquals(4, tileMap[0].length);
    }

    //  test if player can move down once and level will not finish
    @Test
    public void testMoveDown() {
        System.out.println("getTileMap");
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        assertTrue(testLevelObject.moveDown());
        assertFalse(testLevelObject.isLevelFinished());
        assertFalse(testLevelObject.moveDown());
        assertFalse(testLevelObject.isLevelFinished());
    }

    //  test if player can move right twice and isLevelFinished will return true vale - level finished
    @Test
    public void testLevelFinish() {
        Level testLevelObject = new Level();
        testLevelObject.createLevelFromFile(1);
        assertTrue(testLevelObject.moveRight());
        assertFalse(testLevelObject.isLevelFinished());
        assertTrue(testLevelObject.moveRight());
        assertTrue(testLevelObject.isLevelFinished());
    }
}
