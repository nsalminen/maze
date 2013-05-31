package Sprites;

import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

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

        panel.maze.nodes[x][y].setOccupant(this);

    }

    public void shoot() {
        if (portalGun) {

            boolean shooting = true;

            int xOrigin = xIndex;
            int yOrigin = yIndex;

            if (getDirection() == 0) {
                while (shooting) {

                    if ((yOrigin == 0)) {
                        shooting = false;
                    }

                    if (!panel.maze.nodes[yOrigin][xOrigin].getOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        yOrigin--;
                    } else {
                        panel.maze.nodes[yOrigin][xOrigin].setOccupant(new Floor(xOrigin, yOrigin, panel));
                        shooting = false;
                    }
                }
            }
            if (getDirection() == 1) {
                while (shooting) {
                    if ((xOrigin == panel.maze.nodes[0].length - 1)) {
                        shooting = false;
                    }
                    if (!panel.maze.nodes[yOrigin][xOrigin].getOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        xOrigin++;
                    } else {
                        panel.maze.nodes[yOrigin][xOrigin].setOccupant(new Floor(xOrigin, yOrigin, panel));
                        shooting = false;
                    }
                }
            }
            if (getDirection() == 2) {



                while (shooting) {
                    if ((yOrigin == panel.maze.nodes.length - 1)) {
                        shooting = false;
                    }
                    if (!panel.maze.nodes[yOrigin][xOrigin].getOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        yOrigin++;
                    } else {
                        panel.maze.nodes[yOrigin][xOrigin].setOccupant(new Floor(xOrigin, yOrigin, panel));
                        shooting = false;
                    }

                }
            }
            if (getDirection() == 3) {
                while (shooting) {

                    if ((xOrigin == 0)) {
                        shooting = false;
                    }
                    if (!panel.maze.nodes[yOrigin][xOrigin].getOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        xOrigin--;
                    } else {
                        panel.maze.nodes[yOrigin][xOrigin].setOccupant(new Floor(xOrigin, yOrigin, panel));
                        shooting = false;
                    }

                }
            } else {
                shooting = false;
            }

            portalGun = false;
        }
    }

    public void paintSelf(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(xPos, yIndex * panel.blockSize, panel.blockSize, panel.blockSize);

        if (portalGun) {
            int[] xp = {xPos + panel.blockSize, xPos + panel.blockSize, xPos};
            int[] yp = {yPos, yPos + panel.blockSize, yPos + panel.blockSize};

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

        checkPortalGun();
    }

    public void checkPortalGun() {

        String portalnode = panel.maze.nodes[panel.portalGun.yIndex][panel.portalGun.xIndex].getOccupant().getClass().getCanonicalName();

        if (portalnode.equals("Sprites.Player") && !panel.portalGun.taken) {
            panel.maze.nodes[panel.portalGun.yIndex][panel.portalGun.xIndex].setOccupant(new Floor(panel.portalGun.xIndex, panel.portalGun.yIndex, panel));
            this.portalGun = true;
            panel.portalGun.taken = true;

        }

    }

    /**
     * A method that determines what kind of movement is requested of
     * {@link Sprites.Player} and executes the movement.
     *
     * @param direction A variable that is used to determine in which way the
     * user would like to move the player
     */
    public void move(char direction) {
        switch (direction) {
            case 'N':
                if (getDirection() == 0) {
                    if (yIndex - 1 >= 0) {
                        //Check for open NORTH neighbour
                        if (!neighbors[0].getClass().getCanonicalName().equals("Sprites.Wall")) {
                            yIndex = yIndex - 1;
                            updatePos();
                        }
                    }
                } else {
                    setDirection(0);
                }
                break;
            case 'E':
                if (getDirection() == 1) {
                    //Check for right border
                    if (xIndex + 1 < (panel.hardMaze[0].length)) {
                        //Check for open Index
                        if (!neighbors[1].getClass().getCanonicalName().equals("Sprites.Wall")) {
                            xIndex = xIndex + 1;
                            updatePos();
                        }
                    }
                } else {
                    setDirection(1);
                }
                break;
            case 'S':

                if (getDirection() == 2) {
                    //Check for bottom border
                    if (yIndex + 1 < (panel.hardMaze.length)) {
                        //Check for open Index
                        if (!neighbors[2].getClass().getCanonicalName().equals("Sprites.Wall")) {
                            yIndex = yIndex + 1;
                            updatePos();
                        }
                    }
                } else {
                    setDirection(2);
                }
                break;
            case 'W':
                if (getDirection() == 3) {
                    //Check for left border
                    if (xIndex - 1 >= 0) {
                        //Check for open Index
                        if (!neighbors[3].getClass().getCanonicalName().equals("Sprites.Wall")) {
                            xIndex = xIndex - 1;
                            updatePos();
                        }
                    }
                } else {
                    setDirection(3);
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
