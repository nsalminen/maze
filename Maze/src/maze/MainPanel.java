/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MainPanel extends javax.swing.JPanel {

    private Block[][] mAze;
    private int blockSize = 30;

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        generateMaze(10, 15);
    }

    public Dimension getPreferredSize() {
        return new Dimension(640, 640);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Text
        g.drawString("This is my custom Panel!", 10, 20);
        drawMaze(g);
    }

    public void drawMaze(Graphics g) {
        int xpos = 0;
        int ypos = 0;
        for (int i = 0; i < mAze.length; i++) {
            for (int j = 0; j < mAze[i].length; j++) {
                if (mAze[i][j].legal) {
                    g.setColor(Color.WHITE);
                    g.fillRect(mAze[i][j].xPosition, mAze[i][j].yPosition, blockSize, blockSize);
                } else if (!mAze[i][j].legal) {
                    g.setColor(Color.BLACK);
                    g.fillRect(mAze[i][j].xPosition, mAze[i][j].yPosition, blockSize, blockSize);
                }
                xpos = xpos + blockSize;
            }
            xpos = 0;
            ypos = ypos + blockSize;
        }
    }

    // Under Construction
    public void generateMaze(int width, int height) {
        Block[][] maze = new Block[height][width];
        Stack lastPoint = new Stack();
        int north = 1;
        int south = 2;
        int east = 3;
        int west = 4;

        int xPosition = 0;
        int yPosition = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = new Block(xPosition, yPosition, j);
                xPosition = xPosition + blockSize;
            }
            yPosition = yPosition + blockSize;
            xPosition = 0;
        }
        Random rand = new Random();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.println(maze[i][j].xPosition + " " + maze[i][j].yPosition);
            }
            System.out.println();
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                int randomDirection = rand.nextInt(4);
                if (randomDirection == 1 && i < maze[j].length) {
                    maze[i + 1][j].legal = true;
                } else if (randomDirection == 2 && i > 0) {
                    maze[i - 1][j].legal = true;
                } else if (randomDirection == 3 && j < maze.length) {
                    maze[i][j + 1].legal = true;
                } else if (randomDirection == 4 && j > 0) {
                    maze[i + 1][j - 1].legal = true;
                }
            }
        }

        mAze = maze;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
