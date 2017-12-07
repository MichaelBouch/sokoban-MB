/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class Screen {

    private GraphicsDevice display;

    public Screen() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        display = environment.getDefaultScreenDevice();
    }

    public void setFullScreen(DisplayMode displayMode, JFrame window) {
        // window stripped from all unnecessary elements
        window.setUndecorated(true);
        // 
        window.setResizable(false);
        // set up full screen mode
        display.setFullScreenWindow(window);

        // check if monitor settings present  + checks if its able to display those settings
        if (displayMode != null && display.isDisplayChangeSupported()) //  try 
        {
            try {
                display.setDisplayMode(displayMode);
            } catch (Exception ex) { }
        }
    }
//
    public Window getFullScreenWindow() {
        //  getFullScreenWindow
        return display.getFullScreenWindow();
    }
    
    //   
    public void restoreScreen(){
        //  gets window 
        Window window = display.getFullScreenWindow();
        if (window !=null)
        { window.dispose();
        }
        // removes window from full screen mode
        display.setFullScreenWindow(null);
    }
    
}
