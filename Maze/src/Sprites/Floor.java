/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yasen
 */
public class Floor extends Sprite {

    boolean path;
    BufferedImage image;

    public Floor(Node n, GamePanel pan) {
        try {
            parent = n;
            position = parent.getPosition();
            panel = pan;
            image = ImageIO.read(panel.loader.getImageFile("Floor"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Floor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Floor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paintSelf(Graphics g, boolean path, boolean showPath) {
        g.setColor(Color.lightGray);
        g.drawImage(image, parent.xInd * panel.blockSize, parent.yInd * panel.blockSize, panel.blockSize, panel.blockSize, null);

        if (path && showPath) {
            g.setColor(Color.darkGray);
            g.fillOval(parent.xInd * panel.blockSize + panel.blockSize / 2 - 7, parent.yInd * panel.blockSize + panel.blockSize / 2 - 7, 15, 15);
        }
    }
}
