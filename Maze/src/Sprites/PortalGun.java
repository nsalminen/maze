/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Yasen
 */
public class PortalGun extends Sprite{
    
    
     public Image tileset;
     public boolean taken;
       
     public PortalGun(Node n, GamePanel pan) {
        parent = n;
        panel = pan;
        parent.addOccupant(this);
          this.setImage(panel.portalImage);
    }
     
     public void paintSelf(Graphics g){   
       g.setColor(Color.GREEN);
       g.drawImage(this.getImage(), parent.xInd * panel.blockSize, parent.yInd * panel.blockSize, panel.blockSize, panel.blockSize, null);
   
      }    
}
