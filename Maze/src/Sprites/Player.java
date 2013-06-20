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

    private Point facing = new Point(999, 999);
    private int direction;
    private boolean hasPortalGun = false;
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
        panel.getMaze().getNodes()[position.y][position.x].addOccupant(this);
        steps = new Stack<>();
        steps.push(new Position(new Point(1, 1), getDirection()));
        this.setImage(panel.getPlayerImage1());
        this.portalOverlay = panel.getPortalOverlay();
        sfb = new SoundEffect(panel.getLoader().getSoundEffect("bump"));
        portalpickup = new SoundEffect(panel.getLoader().getSoundEffect("pickup_portal"));
        helperpickup = new SoundEffect(panel.getLoader().getSoundEffect("helper"));
        tmpickup = new SoundEffect(panel.getLoader().getSoundEffect("timemachine"));
        shoot = new SoundEffect(panel.getLoader().getSoundEffect("shoot"));
    }

    /**
     * Shoots the portal gun if the player has one.
     */
    public void shoot() {
        if (isHasPortalGun()) {
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
                if (panel.getMaze().getNodes()[yOrigin][xOrigin].peekOccupant() instanceof Wall) {
                    shoot.play();
                    panel.getMaze().getNodes()[yOrigin][xOrigin].removeOccupant(1);
                    shooting = false;
                }
                setHasPortalGun(false);
            }
        }
    }

    /**
     * Paints the Player onto a canvas based on the direction of the Player
     *
     * @param g Is required for drawing to a canvas
     */
    public void paintSelf(Graphics g) {
        g.drawImage(this.getImage(), getX(), getY(), panel.getBlockSize(), panel.getBlockSize(), null);

        if (getDirection() == 0) {
            this.setImage(panel.getPlayerImage0());

        } else if (getDirection() == 1) {
            this.setImage(panel.getPlayerImage1());

        } else if (getDirection() == 2) {
            this.setImage(panel.getPlayerImage2());

        } else if (getDirection() == 3) {
            this.setImage(panel.getPlayerImage3());
        }
        if (isHasPortalGun()) {
            g.drawImage(this.portalOverlay, getX(), getY(), panel.getBlockSize(), panel.getBlockSize(), null);
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
        if ((panel.getMaze().getNode(position).getOccupants().contains(panel.getGoal()))) {
            panel.getMaze().getNode(position).removeOccupant(1);
            panel.repaint();
            panel.gameOver();
        }
    }

    /**
     * Checks whether the Player's current position is equal to the position of
     * the PortalGun.
     */
    public void checkPortalGun() {
        if ((panel.getMaze().getNode(position).getOccupants().contains(panel.getPortalGun())) && ! panel.getPortalGun().taken) {
            portalpickup.play();
            panel.getMaze().getNode(position).removeOccupant(1);
            this.setHasPortalGun(true);
            panel.getPortalGun().taken = true;
            panel.repaint();
        }
    }

    /**
     * Checks whether the Player's current position is equal to the position of
     * the TimeMachine.
     */
    public void checkTimeMachine() {
        if ((panel.getMaze().getNode(position).getOccupants().contains(panel.getTimeMachine())) && !panel.getTimeMachine().taken) {
            tmpickup.play();
            panel.getMaze().getNode(position).removeOccupant(1);
            for (int n = 0; n < panel.getTimeMachine().stepsReduced; n++) {
                if (stepsTaken > 0) {
                    stepsTaken--;
                }
            }
            panel.getTimeMachine().taken = true;
            panel.repaint();
        }
    }

    /**
     * Checks whether the Player's current position is equal to the position of
     * the Helper.
     */
    private void checkHelper() {
        if ((panel.getMaze().getNode(position).getOccupants().contains(panel.getHelper())) && !panel.getHelper().isTaken()) {
            helperpickup.play();
            panel.getMaze().getNode(position).removeOccupant(1);
            panel.getMaze().findPath(parent);
            panel.getMaze().setShowPath(true);
            panel.repaint();
            for (int i = 0; i < panel.getMaze().getNodes().length; i++) {
                for (int j = 0; j < panel.getMaze().getNodes()[i].length; j++) {
                    System.out.print(panel.getMaze().getNodes()[i][j].isPath() + " ");
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
            if (!(facing.x + 1 > panel.getMaze().getNodes()[0].length) && !(facing.y + 1 > panel.getMaze().getNodes().length)) {
                if (!panel.getMaze().getNode(facing).isWall()) {
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
            panel.getMaze().getNode(position).removeOccupant(1);
            panel.getMaze().getNode(getFacing()).addOccupant(this);
            parent = panel.getMaze().getNodes()[getFacing().y][getFacing().x];
            position.setLocation(parent.getxInd(), parent.getyInd());
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
            panel.getMaze().getNode(position).removeOccupant(1);
            int dir = steps.peek().getDirection();
            Point lastPosition = steps.pop().getPoint();
            System.out.println(lastPosition);
            panel.getMaze().getNode(lastPosition).addOccupant(this);
            parent = panel.getMaze().getNodes()[lastPosition.y][lastPosition.x];
            position.setLocation(parent.getxInd(), parent.getyInd());
            setDirection(dir);
            updateFacing();
            if (stepsTaken > 0) {
                stepsTaken--;
            }
        }
    }

    @Override
    public String toString() {
        String string = "X:" + this.position.x + " Y:" + this.position.y + " DIR:" + this.getDirection();
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
            getFacing().setLocation(position.getX(), position.getY() - 1);
        }
        if (getDirection() == 1) {
            getFacing().setLocation(position.getX() + 1, position.getY());
        }
        if (getDirection() == 2) {
            getFacing().setLocation(position.getX(), position.getY() + 1);
        }
        if (getDirection() == 3) {
            getFacing().setLocation(position.getX() - 1, position.getY());
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

    /**
     * @return the facing
     */
    public Point getFacing() {
        return facing;
    }

    /**
     * @param facing the facing to set
     */
    public void setFacing(Point facing) {
        this.facing = facing;
    }

    /**
     * @return the hasPortalGun
     */
    public boolean isHasPortalGun() {
        return hasPortalGun;
    }

    /**
     * @param hasPortalGun the hasPortalGun to set
     */
    public void setHasPortalGun(boolean hasPortalGun) {
        this.hasPortalGun = hasPortalGun;
    }
}
