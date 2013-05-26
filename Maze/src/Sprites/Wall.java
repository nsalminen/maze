package Sprites;

import java.awt.Color;
import java.awt.Graphics;
import Window.GamePanel;


/**
 *
 * @author Yasen
 */
public class Wall extends Sprite{
    public Wall(int x, int y, GamePanel p){

       xInd = x;
       yInd = y;       
       panel = p;       
       xPos = xInd * panel.blockSize;
       yPos = yInd * panel.blockSize;
       
    }
     
      public void paintSelf(int x, int y,Graphics g){   
       g.setColor(Color.red);
       g.fillRect(x*panel.blockSize, y*panel.blockSize, panel.blockSize, panel.blockSize);
    }
}

      
