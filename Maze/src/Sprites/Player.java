package Sprites;

import Utilities.Position;
import Utilities.SoundEffect;
import Window.GamePanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Stack;

/**
 *
 * @author Yasen and Nels
 */
public class Player extends Sprite {

    public Point facing = new Point(999, 999);
    public int direction;
    public boolean hasPortalGun = false;
    public int stepsTaken = 0;
    private SoundEffect portalpickup;
    private SoundEffect helperpickup;
    private SoundEffect tmpickup;
    private SoundEffect shoot;
    private SoundEffect sfb;
    public Stack<Position> steps;
    private Image portalOverlay;

    public Player(Point position, GamePanel panel) {
        this.position = position;
        this.panel = panel;
        setDirection(1);
        panel.maze.nodes[position.y][position.x].addOccupant(this);
        steps = new Stack<>();
        steps.push(new Position(new Point(1, 1), getDirection()));
        this.setImage(panel.playerImage1);
        this.portalOverlay = panel.portalOverlay;
        sfb = new SoundEffect(panel.loader.getSoundEffect("bump"));
        portalpickup = new SoundEffect(panel.loader.getSoundEffect("pickup_portal"));
        helperpickup = new SoundEffect(panel.loader.getSoundEffect("helper"));
        tmpickup = new SoundEffect(panel.loader.getSoundEffect("timemachine"));
        shoot = new SoundEffect(panel.loader.getSoundEffect("shoot"));
    }

    /**
     * Shoots the portal gun if the player has one.
     */
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
                if (panel.maze.nodes[yOrigin][xOrigin].peekOccupant() instanceof Wall) {
                    shoot.play();
                    panel.maze.nodes[yOrigin][xOrigin].removeOccupant(1);
                    shooting = false;
                }
                hasPortalGun = false;
            }
        }
    }

    /**
     * Paints the Player onto a canvas based on the direction of the Player
     *
     * @param g Is required for drawing to a canvas
     */
    public void paintSelf(Graphics g) {
        g.drawImage(this.getImage(), getX(), getY(), panel.blockSize, panel.blockSize, null);

        if (getDirection() == 0) {
            this.setImage(panel.playerImage0);

        } else if (getDirection() == 1) {
            this.setImage(panel.playerImage1);

        } else if (getDirection() == 2) {
            this.setImage(panel.playerImage2);

        } else if (getDirection() == 3) {
            this.setImage(panel.playerImage3);
        }
        if (hasPortalGun) {
            g.drawImage(this.portalOverlay, getX(), getY(), panel.blockSize, panel.blockSize, null);
        }

        panel.repaint();
        checkGoal();
        checkPortalGun();
        checkTimeMachine();
        checkHelper();
    }

    /**
     * Checks whether the Player's current position is equal to the position of
     * the Goal.
     */
    public void checkGoal() {
        if ((panel.maze.getNode(position).occupants.contains(panel.goal))) {
            panel.maze.getNode(position).removeOccupant(1);
            panel.repaint();
            panel.gameOver();
        }
    }

    /**
     * Checks whether the Player's current position is equal to the position of
     * the PortalGun.
     */
    public void checkPortalGun() {
        if ((panel.maze.getNode(position).occupants.contains(panel.portalGun)) && !panel.portalGun.taken) {
            portalpickup.play();
            panel.maze.getNode(position).removeOccupant(1);
            this.hasPortalGun = true;
            panel.portalGun.taken = true;
            panel.repaint();
        }
    }

    /**
     * Checks whether the Player's current position is equal to the position of
     * the TimeMachine.
     */
    public void checkTimeMachine() {
        if ((panel.maze.getNode(position).occupants.contains(panel.timeMachine)) && !panel.timeMachine.taken) {
            tmpickup.play();
            panel.maze.getNode(position).removeOccupant(1);
            for (int n = 0; n < panel.timeMachine.stepsReduced; n++) {
                if (stepsTaken > 0) {
                    stepsTaken--;
                }
            }
            panel.timeMachine.taken = true;
            panel.repaint();
        }
    }

    /**
     * Checks whether the Player's current position is equal to the position of
     * the Helper.
     */
    private void checkHelper() {
        if ((panel.maze.getNode(position).occupants.contains(panel.helper)) && !panel.helper.taken) {
            helperpickup.play();
            panel.maze.getNode(position).removeOccupant(1);
            panel.maze.findPath(parent);
            panel.maze.showPath = true;
            panel.repaint();
            for (int i = 0; i < panel.maze.nodes.length; i++) {
                for (int j = 0; j < panel.maze.nodes[i].length; j++) {
                    System.out.print(panel.maze.nodes[i][j].isPath() + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * This method moves the Player north, when the Player's direction is facing
     * east.
     */
    public void moveNorth() {
        if (getDirection() == 0) {
            move();
        } else {
            setDirection(0);
        }
    }

    /**
     * This method moves the Player east, when the Player's direction is facing
     * east.
     */
    public void moveEast() {
        if (getDirection() == 1) {
            move();
        } else {
            setDirection(1);
        }
    }

    /**
     * This method moves the Player south, when the Player's direction is facing
     * south.
     */
    public void moveSouth() {
        if (getDirection() == 2) {
            move();
        } else {
            setDirection(2);
        }
    }

    /**
     * This method moves the Player west, when the Player's direction is facing
     * west.
     */
    public void moveWest() {
        if (getDirection() == 3) {
            move();
        } else {
            setDirection(3);
        }
    }

    /**
     * This method determines whether the Player is allowed to move or not.
     *
     * @return Returns whether the Player is allowed to move or not.
     */
    public boolean canMove() {
        boolean canMove = false;
        if (!(facing.x < 0) && !(facing.y < 0)) {
            if (!(facing.x + 1 > panel.maze.nodes[0].length) && !(facing.y + 1 > panel.maze.nodes.length)) {
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
            steps.push(new Position(new Point(position), getDirection()));
            panel.maze.getNode(position).removeOccupant(1);
            panel.maze.getNode(facing).addOccupant(this);
            parent = panel.maze.nodes[facing.y][facing.x];
            position.setLocation(parent.xInd, parent.yInd);
            stepsTaken++;
            updateFacing();
        } else {
            sfb.play();
        }
    }

    /**
     * Undoes the last move of the Player.
     */
    public void undoMove() {
        if (!steps.isEmpty()) {
            panel.maze.getNode(position).removeOccupant(1);
            int dir = steps.peek().getDirection();
            Point lastPosition = steps.pop().getPoint();
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

    /**
     * @return the current direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * When the Player turns this method updates the facing Point
     */
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
    }

    /**
     * Sets the direction to the specified parameter.
     *
     * @param dir
     */
    public void setDirection(int dir) {
        direction = dir;
        updateFacing();
    }

    /**
     * @return the sfb
     */
    public SoundEffect getSfb() {
        return sfb;
    }
}
