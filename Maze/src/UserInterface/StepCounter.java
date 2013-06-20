/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Sprites.Player;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen
 */
public class StepCounter {

    private int x;
    private int y;
    private GamePanel panel;

    public StepCounter(int xPos, int yPos, GamePanel panel) {
        this.panel = panel;
        x = 0;
        y = 0;
    }

    public void drawSteps(Graphics g) {
        String steps = Integer.toString(panel.player.stepsTaken);
        g.setColor(Color.gray);
        g.drawRect(x, y, panel.blockSize, panel.blockSize);
        g.setColor(Color.white);
        g.drawString(steps, x + (panel.blockSize / 2) - (3 * steps.length()), y + 25);
    }
}
