package Sprites;

import Utilities.SoundEffect;
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
    private SoundEffect sfw;
    private SoundEffect sfb;

    public Player(int x, int y, GamePanel p, Graphics g) {
        xIndex = x;
        yIndex = y;
        panel = p;
        xPos = xIndex * panel.blockSize;
        yPos = yIndex * panel.blockSize;

        panel.maze.nodes[x][y].addOccupant(this);

       sfw = new SoundEffect("content\\sounds\\walk.wav");
       sfb = new SoundEffect("content\\sounds\\bump.wav");
    }

    public void shoot() {
        if (hasPortalGun) {

            boolean shooting = true;

            int xOrigin = xIndex;
            int yOrigin = yIndex;

                 while (shooting) {
                     if (getDirection() == 0) {
                        yOrigin--;
                     }
                     if (getDirection() == 1) {
                        xOrigin++;
                     }if (getDirection() == 2) {
                        yOrigin++;
                     }if (getDirection() == 3) {
                        xOrigin--;
                     }

                    if ((xOrigin-1 ==  0 || yOrigin-1 == 0) || yOrigin+1 == panel.maze.nodes.length || xOrigin+1 == panel.maze.nodes[0].length) {
                        
                        System.out.println("Fell off the deep end");
                        shooting = false;
                    }
                    
                    if (!panel.maze.nodes[yOrigin][xOrigin].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        System.out.println("No Wall");
                                    
                    }
                    
                    if (panel.maze.nodes[yOrigin][xOrigin].popOccupant() instanceof  Wall) {
                        System.out.println("BOOM");                        
                        panel.maze.nodes[yOrigin][xOrigin].trimOccupants(1);
                        shooting = false;
                    }
            hasPortalGun = false;
          }
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
        checkHelper();
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
    
    private void checkHelper() {
        String hnode = panel.maze.getNodes()[panel.helper.yIndex][panel.helper.xIndex].popOccupant().getClass().getCanonicalName();

        if (hnode.equals("Sprites.Player") && !panel.helper.taken) {
            panel.maze.getNodes()[panel.helper.yIndex][panel.helper.xIndex].addOccupant(new Floor(panel.helper.xIndex, panel.helper.yIndex, panel));
            panel.maze.findPath(panel.maze.nodes[yIndex][xIndex]);
            panel.maze.showPath = true;
            panel.repaint();
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
        
        
        
        //System.out.println(direction);
        panel.maze.nodes[yIndex][xIndex].trimOccupants(1);
        switch (direction) {
            case 'N':
                if (getDirection() == 0) {
                    if (yIndex - 1 >= 0) {
                        //Check for open NORTH neighbour
                        if (!neighbors[0].getClass().getCanonicalName().equals("Sprites.Wall")) {
                            //panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            yIndex = yIndex - 1;
                            stepsTaken++;
                            updatePos();                             
                            sfw.play();
                        }
                        else{
                            sfb.play();
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
                            //panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            xIndex = xIndex + 1;
                            stepsTaken++;
                            updatePos();
                            
                           sfw.play();
                        }
                        else{
                            sfb.play();
                        
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
                            //panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            yIndex = yIndex + 1;
                            stepsTaken++;
                            updatePos();
                            
                           sfw.play();
                        }
                        else{
                            sfb.play();
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
                            //panel.maze.nodes[yIndex][xIndex].addOccupant(this);
                            xIndex = xIndex - 1;
                            stepsTaken++;
                            updatePos();
                            
                            sfw.play();
                        }
                        else{
                            sfb.play();
                        }
                    }
                } else {
                    setDirection(3);
                }
                break;
        }
        
        panel.maze.nodes[yIndex][xIndex].addOccupant(this);
        //System.out.println(toString());
        panel.maze.paintMaze(panel.getGraphics());
    }

    @Override
    public String toString() {
        String string = "X:" + this.xIndex + " Y:" + this.yIndex + " DIR:" + this.direction;
        
        return string;
    }
    
    

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir) {
        direction = dir;
    }
}
