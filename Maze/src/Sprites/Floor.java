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
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yasen
 */
public class Floor extends Sprite {

    boolean path;

    public Floor(Node node, GamePanel panel) {
        parent = node;
        position = parent.getPosition();
        this.panel = panel;
        this.setImage(panel.floorImage);
    }

    public void paintSelf(Graphics g, boolean path, boolean showPath) {
        g.setColor(Color.lightGray);
        g.drawImage(this.getImage(), parent.xInd * panel.blockSize, parent.yInd * panel.blockSize, panel.blockSize, panel.blockSize, null);

        if (path && showPath) {
            g.setColor(Color.darkGray);
            g.fillOval(parent.xInd * panel.blockSize + panel.blockSize / 2 - 7, parent.yInd * panel.blockSize + panel.blockSize / 2 - 7, panel.blockSize / 2, panel.blockSize / 2);
        }
    }
}
