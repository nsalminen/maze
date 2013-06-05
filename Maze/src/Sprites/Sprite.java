/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Window.GamePanel;

/**
 *
 * @author Yasen
 */
public abstract class Sprite {

    public int xPos;
    public int yPos;
    public int xIndex;
    public int yIndex;
    public GamePanel panel;
    public Sprite[] neighbors = new Sprite[4];

    public Sprite() {
    }

    public void getNeighbors() {

        if (yIndex - 1 >= 0) {
            neighbors[0] = panel.maze.nodes[yIndex - 1][xIndex].popOccupant(); // NORTH NEIGHBOUR at Index:0;
        }
        if (xIndex + 1 < (panel.hardMaze[0].length)) {
            neighbors[1] = panel.maze.nodes[yIndex][xIndex + 1].popOccupant(); // EAST NEIGHBOUR at Index:1;
        }
        if (yIndex + 1 < (panel.hardMaze.length)) {
            neighbors[2] = panel.maze.nodes[yIndex + 1][xIndex].popOccupant(); // SOUTH NEIGHBOUR at Index:2;
        }
        if (xIndex - 1 >= 0) {
            neighbors[3] = panel.maze.nodes[yIndex][xIndex - 1].popOccupant(); // WEST NEIGHBOUR at Index:3;
        }
    }

    public int getX() {
        System.out.print(xPos);
        return xPos;
    }

    public int getY() {
        System.out.print(yPos);
        return yPos;
    }

    public void updatePos() {
        xPos = xIndex * panel.blockSize;
        yPos = yIndex * panel.blockSize;
    }
}
