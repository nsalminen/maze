/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Game.Node;
import Utilities.ImageTool;
import Window.GamePanel;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 *
 * @author Yasen
 */
public abstract class Sprite extends ImageTool{
    public GamePanel panel;
    public Sprite[] neighbors = new Sprite[4];
    public Point position = new Point(999,999);
    public Node parent;
    private Image image;
    
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
    
        public static Image rotate(Image img, double angle){
    double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
    int w = img.getWidth(null), h = img.getHeight(null);
    int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
            * cos + w * sin);
    BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
    Graphics2D g = bimg.createGraphics();
    g.translate((neww - w) / 2, (newh - h) / 2);
    g.rotate(Math.toRadians(angle), w / 2, h / 2);
    g.drawRenderedImage(toBufferedImage(img), null);
    g.dispose();
    return toImage(bimg);
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
