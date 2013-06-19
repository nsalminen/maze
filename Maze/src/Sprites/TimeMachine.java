/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen
 */
public class TimeMachine extends Sprite{
    
     public boolean taken;
     public int stepsReduced = 20;
       
     public TimeMachine(Node n, GamePanel pan) {
        parent = n;
        panel = pan;
        parent.addOccupant(this);
    }
     
       public void paintSelf(Graphics g){   
       g.setColor(Color.MAGENTA);
        g.drawImage(this.getImage(), parent.xInd * panel.blockSize, parent.yInd * panel.blockSize, panel.blockSize, panel.blockSize, null);

      }    
    
}
