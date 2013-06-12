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
    Node parent;

    public Floor(Node n, GamePanel pan) {
        parent = n;
        position = parent.getPosition();
        panel = pan;
    }

    public void paintSelf(Graphics g, boolean path, boolean showPath) {
            g.setColor(Color.lightGray);
            g.fillRect(getX(), getY(), panel.blockSize, panel.blockSize);
      
         if (path && showPath) {
            g.setColor(Color.darkGray);
            g.fillOval(getX() + panel.blockSize/2-7, getY() + panel.blockSize/2-7, 15, 15);
         }
    }
}
