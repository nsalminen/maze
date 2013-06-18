package Sprites;

import Game.*;
import Utilities.Position;
import Utilities.SoundEffect;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;

/**
 *
 * @author Yasen
 */
public class Player extends Sprite {

    public Point facing = new Point(999, 999);
    public int direction;
    public boolean hasPortalGun = false;
    public int stepsTaken = 0;
    private SoundEffect sfw;
    private SoundEffect sfb;
    public Stack<Point> steps;
    public Stack<Position> steps2;
    
        
        

    public Player(Point p, GamePanel pan) {
        
        position = p;
        panel = pan;
        setDirection(1);
        panel.maze.nodes[position.y][position.x].addOccupant(this);
        
        steps2 = new Stack<>();
        steps2.push(new Position (new Point(1, 1), getDirection()));

        sfw = new SoundEffect(panel.loader.getSoundEffect("walk"));
        sfb = new SoundEffect(panel.loader.getSoundEffect("bump"));
    }

    public void shoot() {
        if (hasPortalGun) {

            boolean shooting = true;

            int xOrigin = position.x;
            int yOrigin = position.y;

            while (shooting) {
                if (getDirection() == 0) {
                    yOrigin--;
                }
                if (getDirection() == 1) {
                    xOrigin++;
                }
                if (getDirection() == 2) {
                    yOrigin++;
                }
                if (getDirection() == 3) {
                    xOrigin--;
                }

                if ((xOrigin - 1 == 0 || yOrigin - 1 == 0) || yOrigin + 1 == panel.maze.nodes.length || xOrigin + 1 == panel.maze.nodes[0].length) {

                    //System.out.println("Fell off the deep end");
                    shooting = false;
                }

                if (!panel.maze.nodes[yOrigin][xOrigin].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                    //System.out.println("No Wall");

                }

                if (panel.maze.nodes[yOrigin][xOrigin].popOccupant() instanceof Wall) {
                    //System.out.println("BOOM");
                    panel.maze.nodes[yOrigin][xOrigin].trimOccupants(1);
                    shooting = false;
                }
                hasPortalGun = false;
            }
        }
    }

    public void paintSelf(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(getX(), getY(), panel.blockSize, panel.blockSize);



        g.setColor(Color.blue);
        g.drawRect(facing.x * panel.blockSize, facing.y * panel.blockSize, panel.blockSize, panel.blockSize);

        if (hasPortalGun) {
            int[] xp = {getX() + panel.blockSize, getX() + panel.blockSize, getX()};
            int[] yp = {getY(), getY() + panel.blockSize, getY() + panel.blockSize};

            g.setColor(Color.green);
            g.fillPolygon(xp, yp, 3);
        }

        g.setColor(Color.CYAN);

        if (getDirection() == 0) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()) + (panel.blockSize / 2),
                    getY());
        } else if (getDirection() == 1) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()) + (panel.blockSize),
                    getY() + (panel.blockSize / 2));
        } else if (getDirection() == 2) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()) + (panel.blockSize / 2),
                    getY() + (panel.blockSize));
        } else if (getDirection() == 3) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()),
                    getY() + (panel.blockSize / 2));
        }
        checkGoal();
        checkPortalGun();
        checkTimeMachine();
        checkHelper();
    }

    public void checkGoal() {
        if ((panel.maze.getNode(position).occupants.contains(panel.goal))) {
            System.out.println("GAME!");
            panel.maze.getNode(position).trimOccupants(1);
            panel.repaint();
            panel.gameOver();
        }
    }

    public void checkPortalGun() {
        if ((panel.maze.getNode(position).occupants.contains(panel.portalGun)) && !panel.portalGun.taken) {
            System.out.println("Found PortalGun!");
            panel.maze.getNode(position).trimOccupants(1);
            this.hasPortalGun = true;
            panel.portalGun.taken = true;
            panel.repaint();
        }
    }

    public void checkTimeMachine() {
        if ((panel.maze.getNode(position).occupants.contains(panel.timeMachine)) && !panel.timeMachine.taken) {
            System.out.println("Found TimeMachine!");
            panel.maze.getNode(position).trimOccupants(1);
            for (int n = 0; n < panel.timeMachine.stepsReduced; n++) {
                if (stepsTaken > 0) {
                    stepsTaken--;
                }
            }
            panel.timeMachine.taken = true;
            panel.repaint();
        }
    }

    private void checkHelper() {
        if ((panel.maze.getNode(position).occupants.contains(panel.helper)) && !panel.helper.taken) {
            System.out.println("Found Helper!");
            panel.maze.getNode(position).trimOccupants(1);
            panel.maze.findPath(new Node(parent.yInd, parent.xInd));
            panel.maze.showPath = true;
            panel.repaint();
        }
    }

    public void moveNorth() {
        if (getDirection() == 0) {
            move();

        } else {
            setDirection(0);
        }
    }

    public void moveEast() {
        if (getDirection() == 1) {
            move();

        } else {
            setDirection(1);
        }
    }

    public void moveSouth() {
        if (getDirection() == 2) {
            move();

        } else {
            setDirection(2);
        }
    }

    public void moveWest() {
        if (getDirection() == 3) {
            move();

        } else {
            setDirection(3);
        }

    }

    public boolean canMove() {
        boolean canMove = false;

        if (!(facing.x < 0) && !(facing.y < 0)) {
            if (!(facing.x + 1 > panel.maze.nodes.length) && !(facing.y + 1 > panel.maze.nodes[0].length)) {
                if (!panel.maze.getNode(facing).isWall()) {
                    canMove = true;
                }
            }
        }
        return canMove;
    }

    /**
     * A method that determines what kind of movement is requested of
     * {@link Sprites.Player} and executes the movement.
     *
     * @param direction A variable that is used to determine in which way the
     * user would like to move the player
     */
    public void move() {
        if (canMove()) {
            sfw.play();
           
            steps2.push(new Position (new Point(position), getDirection()));
            panel.maze.getNode(position).trimOccupants(1);
            //System.out.println("MOVING");
            panel.maze.getNode(facing).addOccupant(this);
            parent = panel.maze.nodes[facing.y][facing.x];
            position.setLocation(parent.xInd, parent.yInd);
            //System.out.println("Player" + position);
            //System.out.println("STOPPED");
            stepsTaken++;
            updateFacing();
        } else {
            sfb.play();
        }
    }

    public void undoMove() {
        if (!steps2.isEmpty()) {
            panel.maze.getNode(position).trimOccupants(1);
            System.out.println("UNDO");
            int dir = steps2.peek().direction;
            Point lastPosition = steps2.pop().point;
            System.out.println(lastPosition);
            panel.maze.getNode(lastPosition).addOccupant(this);
            parent = panel.maze.nodes[lastPosition.y][lastPosition.x];
            position.setLocation(parent.xInd, parent.yInd);
            setDirection(dir);
            updateFacing();
            if (stepsTaken > 0) {
                stepsTaken--;
            }
        }
    }

    @Override
    public String toString() {
        String string = "X:" + this.position.x + " Y:" + this.position.y + " DIR:" + this.direction;
        return string;
    }

    public int getDirection() {
        return direction;
    }

    private void updateFacing() {
        if (getDirection() == 0) {
            facing.setLocation(position.getX(), position.getY() - 1);
        }
        if (getDirection() == 1) {
            facing.setLocation(position.getX() + 1, position.getY());

        }
        if (getDirection() == 2) {
            facing.setLocation(position.getX(), position.getY() + 1);

        }
        if (getDirection() == 3) {
            facing.setLocation(position.getX() - 1, position.getY());
        }

        //System.out.println("--------------");
    }

    public void setDirection(int dir) {
        direction = dir;
        updateFacing();
    }

    /**
     * @return the sfw
     */
    public SoundEffect getSfw() {
        return sfw;
    }

    /**
     * @return the sfb
     */
    public SoundEffect getSfb() {
        return sfb;
    }
}
