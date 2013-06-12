package Sprites;

import Game.Node;
import java.awt.Color;
import java.awt.Graphics;
import Window.GamePanel;


/**
 *
 * @author Yasen
 */
public class Wall extends Sprite{
   
    public boolean portal;
       
    public Wall(Node n, GamePanel pan) {
        panel = pan;        
        parent = n;
        position = parent.getPosition();
        
    }
     
    public void paintSelf(int x, int y, Graphics g){ 
       g.setColor(Color.red);
       g.fillRect(x*panel.blockSize,y*panel.blockSize, panel.blockSize, panel.blockSize);
    }
}

      
