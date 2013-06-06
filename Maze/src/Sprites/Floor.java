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

    public void paintSelf(int x, int y, Graphics g, boolean path, boolean showPath) {
        if (path) {
            g.setColor(Color.darkGray);
            g.fillOval(x * panel.blockSize+15, y * panel.blockSize+15, panel.blockSize - 30, panel.blockSize - 30);
        }
    }
}
