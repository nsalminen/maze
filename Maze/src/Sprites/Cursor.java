/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import java.awt.Color;
import java.awt.Graphics;
import Window.GamePanel;

/**
 *
 * @author Yasen
 */
public class Cursor {

    private int xInd;
    private int yInd;
    private GamePanel panel;

    public Cursor(int x, int y, GamePanel p) {

        xInd = x;
        yInd = y;
        panel = p;

    }

    public void paintSelf(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(xInd * panel.blockSize, yInd * panel.blockSize, panel.blockSize, panel.blockSize);
        g.drawRect(xInd * panel.blockSize + 1, yInd * panel.blockSize, panel.blockSize, panel.blockSize);
        g.drawRect(xInd * panel.blockSize, yInd * panel.blockSize + 1, panel.blockSize, panel.blockSize);
        g.drawRect(xInd * panel.blockSize + 1, yInd * panel.blockSize + 1, panel.blockSize, panel.blockSize);
    }

    /**
     * A method that determines what kind of movement is requested of
     * {@link Sprites.Cursor} and executes the movement.
     *
     * @param direction A variable that is used to determine in which way the user
     * would like to move the cursor
     */
    public void move(char direction) {
        switch (direction) {
            case 'N':
                if (yInd - 1 >= 0) {
                    yInd = yInd - 1;
                }
                break;
            case 'E':
                if (xInd + 1 < (panel.hardMaze[0].length)) {
                    xInd = xInd + 1;
                }
                break;
            case 'S':
                if (yInd + 1 < (panel.hardMaze.length)) {
                    yInd = yInd + 1;
                }
                break;
            case 'W':
                if (xInd - 1 >= 0) {
                    xInd = xInd - 1;
                }
                break;
        }
    }

    public void printCurrentNode() {
        System.out.println(panel.maze.getNodes()[yInd][xInd].toString());
    }
}
