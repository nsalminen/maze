/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Yasen
 */
public class Helper extends Sprite {

    public boolean taken;

    public Helper(Point p, GamePanel pan) {
        position = p;
        panel = pan;
        panel.maze.nodes[position.y][position.y].addOccupant(this);
    }

    public void paintSelf(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(getX(), getY(), panel.blockSize, panel.blockSize);
    }
}
