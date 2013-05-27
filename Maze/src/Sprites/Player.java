package Sprites;

import java.awt.Color;
import java.awt.Graphics;
import Window.GamePanel;

/**
 *
 * @author Yasen
 */
public class Player extends Sprite {

    public int direction;
    public boolean portalGun = false;

    public Player(int x, int y, GamePanel p) {
        xIndex = x;
        yIndex = y;
        panel = p;
        xPos = xIndex * panel.blockSize;
        yPos = yIndex * panel.blockSize;
        
    }
    
    public void togglePortalGun(){
        if(portalGun){
            portalGun = true;
        }
        else if(!portalGun){
            portalGun = false;
        }
    }
    
    public void paintSelf(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(xPos, yIndex * panel.blockSize, panel.blockSize, panel.blockSize);
        
        if(portalGun){
            int[] xp = {xPos+panel.blockSize, xPos+panel.blockSize, xPos};
            int[] yp = {yPos, yPos+panel.blockSize, yPos+panel.blockSize};
            
            g.setColor(Color.green);
            g.fillPolygon(xp, yp, 3);
        }
        
        g.setColor(Color.CYAN);

        if (getDirection() == 0) {
            g.drawLine((xPos) + (panel.blockSize / 2),
                    (yPos) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (xPos) + (panel.blockSize / 2),
                    yPos);
        } else if (getDirection() == 1) {
            g.drawLine((xPos) + (panel.blockSize / 2),
                    (yPos) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (xPos) + (panel.blockSize),
                    yPos + (panel.blockSize / 2));
        } else if (getDirection() == 2) {
            g.drawLine((xPos) + (panel.blockSize / 2),
                    (yPos) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (xPos) + (panel.blockSize / 2),
                    yPos + (panel.blockSize));
        } else if (getDirection() == 3) {
            g.drawLine((xPos) + (panel.blockSize / 2),
                    (yPos) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (xPos),
                    yPos + (panel.blockSize / 2));
        }
        getNeighbors();
    }

    /**
     * A method that determines what kind of movement is requested of
     * {@link Sprites.Player} and executes the movement.
     *
     * @param direction A variable that is used to determine in which way the user
     * would like to move the player
     */
    public void move(char direction) {
        switch (direction) {
            case 'N':
                setDirection(0);
                if (yIndex - 1 >= 0) {
                    //Check for open NORTH neighbour
                    if (!neighbors[0].getClass().getCanonicalName().equals("Sprites.Wall")) {
                        yIndex = yIndex - 1;
                        updatePos();
                    }
                }
                break;
            case 'E':
                setDirection(1);
                //Check for right border
                if (xIndex + 1 < (panel.hardMaze[0].length)) {
                    //Check for open Index
                    if (!neighbors[1].getClass().getCanonicalName().equals("Sprites.Wall")) {
                        xIndex = xIndex + 1;
                        updatePos();
                    }
                }
                break;
            case 'S':
                setDirection(2);
                //Check for bottom border
                if (yIndex + 1 < (panel.hardMaze.length)) {
                    //Check for open Index
                    if (!neighbors[2].getClass().getCanonicalName().equals("Sprites.Wall")) {
                        yIndex = yIndex + 1;
                        updatePos();
                    }
                }
                break;
            case 'W':
                setDirection(3);
                //Check for left border
                if (xIndex - 1 >= 0) {
                    //Check for open Index
                    if (!neighbors[3].getClass().getCanonicalName().equals("Sprites.Wall")) {
                        xIndex = xIndex - 1;
                        updatePos();
                    }
                }
                break;
        }
        panel.maze.nodes[yIndex][xIndex].setOccupant(this);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir) {
        direction = dir;
    }
}
