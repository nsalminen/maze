/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Game.Node;
import Utilities.*;
import Window.GamePanel;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 *
 * @author Yasen
 */
public abstract class Sprite{
    public GamePanel panel;
    public Sprite[] neighbors = new Sprite[4];
    public Point position = new Point(999,999);
    public Node parent;
    private Image image;
    private int face;
    
    public Sprite() {
    }
    public Point getPosition(){
        return position;
    }
    
    public int getX() {
        int xPos = position.x*panel.getBlockSize();
        //System.out.print(xPos);
        return xPos;
    }

    public int getY() {        
        int yPos = position.y*panel.getBlockSize();
       // System.out.print(yPos);
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
