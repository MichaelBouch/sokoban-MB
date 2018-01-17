/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb;

/**
 *
 * @author user
 */
public class Coord {

    int x;
    int y;

    public void Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveLeftBy(int x) {
        this.x = -x;
    }

    public void moveRightBy(int x) {
        this.x = +x;
    }

    public void moveUpBy(int y) {
        this.y = +y;
    }

    public void moveDownBy(int y) {
        this.y = +y;
    }

}
