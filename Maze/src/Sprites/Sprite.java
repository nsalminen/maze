package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Yasen and Nels
 */
public abstract class Sprite {

    public GamePanel panel;
    public Sprite[] neighbors = new Sprite[4];
    public Point position = new Point(999, 999);
    public Node parent;
    private Image image;

    /**
     * @return the position of the Sprite
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @return the x position of the Sprite
     */
    public int getX() {
        int xPos = position.x * panel.getBlockSize();
        return xPos;
    }

    /**
     * @return the y position of the Sprite
     */
    public int getY() {
        int yPos = position.y * panel.getBlockSize();
        return yPos;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
