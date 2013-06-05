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
    public boolean hasPortalGun = false;
    public int stepsTaken = 0;

    public Player(int x, int y, GamePanel p) {
        xIndex = x;
        yIndex = y;
        panel = p;
        xPos = xIndex * panel.blockSize;
        yPos = yIndex * panel.blockSize;

        panel.maze.nodes[x][y].addOccupant(this);

    }

    public void shoot() {
        if (hasPortalGun) {

            boolean shooting = true;

            int xOrigin = xIndex;
            int yOrigin = yIndex;

            if (getDirection() == 0) {
                while (shooting) {

                    if ((yOrigin == 0)) {
                        shooting = false;
                    }
                    if (!panel.maze.nodes[yOrigin][xOrigin].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        yOrigin--;
                    } else if (panel.maze.nodes[yOrigin][xOrigin].getOccupant(1) instanceof  Wall) {
                        panel.maze.nodes[yOrigin][xOrigin].occupants.remove(1);
                        
                        shooting = false;
                    }
                    else{
                         shooting = false;
                    }
                }
            }
            if (getDirection() == 1) {
                while (shooting) {
                    if ((xOrigin == panel.maze.nodes[0].length - 1)) {
                        shooting = false;
                    }
                    if (!panel.maze.nodes[yOrigin][xOrigin].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        xOrigin++;
                    } else {
                        panel.maze.nodes[yOrigin][xOrigin].trimOccupants(1);
                        shooting = false;
                    }
                }
            }
            if (getDirection() == 2) {



                while (shooting) {
                    if ((yOrigin == panel.maze.nodes.length - 1)) {
                        shooting = false;
                    }
                    if (!panel.maze.nodes[yOrigin][xOrigin].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        yOrigin++;
                    } else {
                        panel.maze.nodes[yOrigin][xOrigin].trimOccupants(1);
                        shooting = false;
                    }

                }
            }
            if (getDirection() == 3) {
                while (shooting) {
                    if ((xOrigin == 0)) {
                        shooting = false;
                    }
                    if (!panel.maze.nodes[yOrigin][xOrigin].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        xOrigin--;
                    } else {
                        panel.maze.nodes[yOrigin][xOrigin].trimOccupants(1);
                        shooting = false;
                    }
                }
            } else {
                shooting = false;
            }

            hasPortalGun = false;
        }
    }

    public void paintSelf(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(xPos, yIndex * panel.blockSize, panel.blockSize, panel.blockSize);

        if (hasPortalGun) {
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
        checkTimeMachine();
    }

    public void checkPortalGun() {

        String portalnode = panel.maze.nodes[panel.portalGun.yIndex][panel.portalGun.xIndex].popOccupant().getClass().getCanonicalName();

        if (portalnode.equals("Sprites.Player") && !panel.portalGun.taken) {
            panel.maze.nodes[panel.portalGun.yIndex][panel.portalGun.xIndex].trimOccupants(1);
            this.hasPortalGun = true;
            panel.portalGun.taken = true;
        }

    }
    
    public void checkTimeMachine() {

        String tmnode = panel.maze.nodes[panel.timeMachine.yIndex][panel.timeMachine.xIndex].popOccupant().getClass().getCanonicalName();

        if(tmnode.equals("Sprites.Player") && !panel.timeMachine.taken) {
            panel.maze.nodes[panel.timeMachine.yIndex][panel.timeMachine.xIndex].trimOccupants(1);
            
            for ( int n  = 0; n < panel.timeMachine.stepsReduced; n++)
            {
                if(stepsTaken > 0 )
                {
                    stepsTaken--;
                }
                
            }
            panel.timeMachine.taken = true;

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
                            panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            yIndex = yIndex - 1;
                            stepsTaken++;
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
                            panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            xIndex = xIndex + 1;
                            stepsTaken++;
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
                            panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            yIndex = yIndex + 1;
                            stepsTaken++;
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
                            panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            xIndex = xIndex - 1;
                            stepsTaken++;
                            updatePos();
                        }
                    }
                } else {
                    setDirection(3);
                }
                break;
        }
        panel.maze.nodes[yIndex][xIndex].addOccupant(this);
        
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir) {
        direction = dir;
    }
}
