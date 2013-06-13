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
public class Floor extends Sprite {

    boolean path;

    public Floor(Node n, GamePanel pan) {
        parent = n;
        position = parent.getPosition();
        panel = pan;
    }

    public void paintSelf(Graphics g, boolean path, boolean showPath) {
            g.setColor(Color.lightGray);
            g.fillRect(parent.xInd*panel.blockSize, parent.yInd*panel.blockSize, panel.blockSize, panel.blockSize);
      
         if (path && showPath) {
            g.setColor(Color.darkGray);
            g.fillOval(parent.xInd*panel.blockSize + panel.blockSize/2-7, parent.yInd*panel.blockSize + panel.blockSize/2-7, 15, 15);
         }
    }
}
