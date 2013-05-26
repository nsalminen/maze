/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen
 */
public class Floor extends Sprite {
    

    public Floor(int x, int y, GamePanel p){
       xInd = x;
       yInd = y;       
       panel = p;       
       xPos = xInd * panel.blockSize;
       yPos = yInd * panel.blockSize;
       
    }
     
      public void paintSelf(int x, int y,Graphics g){   
       g.setColor(Color.YELLOW);
       g.fillRect(x*panel.blockSize, y*panel.blockSize, panel.blockSize, panel.blockSize);
    
      }
}
