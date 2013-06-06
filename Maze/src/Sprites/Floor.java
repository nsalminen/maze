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

    boolean path;

    public Floor(int x, int y, GamePanel p) {
        xIndex = x;
        yIndex = y;
        panel = p;
        xPos = xIndex * panel.blockSize;
        yPos = yIndex * panel.blockSize;

    }

    public void paintSelf(int x, int y, Graphics g, boolean path) {
            g.setColor(Color.YELLOW);
            g.fillRect(yPos, xPos, panel.blockSize, panel.blockSize);
      
         if (path) {
            g.setColor(Color.darkGray);
            g.fillOval(yPos + panel.blockSize/2-7, xPos + panel.blockSize/2-7, 15, 15);
         }
    }
}
