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
public class Goal extends Sprite {
    

    public Goal(int x, int y, GamePanel p){
       xIndex = x;
       yIndex = y;       
       panel = p;       
       xPos = xIndex * panel.blockSize;
       yPos = yIndex * panel.blockSize;
       
       panel.maze.nodes[yIndex][xIndex].addOccupant(this);
    }
     
      public void paintSelf(Graphics g){   
       g.setColor(Color.YELLOW);
       g.fillRect(xIndex*panel.blockSize, yIndex*panel.blockSize, panel.blockSize, panel.blockSize);
       getNeighbors();
      }    
}
