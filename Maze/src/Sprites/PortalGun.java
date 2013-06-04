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
public class PortalGun extends Sprite{
    
    
     public boolean taken;
       
     public PortalGun(int x, int y, GamePanel p){
       
       xIndex = x;
       yIndex = y;       
       
       panel = p;       
       
       xPos = xIndex * panel.blockSize;
       yPos = yIndex * panel.blockSize;
       
       
        panel.maze.getNodes()[yIndex][xIndex].setOccupant(this);
 }
     public void paintSelf(Graphics g){   
       g.setColor(Color.GREEN);
       g.fillRect(xIndex*panel.blockSize, yIndex*panel.blockSize, panel.blockSize, panel.blockSize);
       getNeighbors();
      }    
}