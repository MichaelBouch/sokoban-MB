package com.mb;

public class Logic {

    
    int movesCounter = 0;
    int currentLevel = 1;

    public Logic() {
    }

    public void addMove() {
        movesCounter++;
    }

    public int getMovesCounter() {
        return movesCounter;
    }

    public void setMovesCounter(int movesCounter) {
        this.movesCounter = movesCounter;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

}
