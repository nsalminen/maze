package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen and Nels
 */
public class Wall extends Sprite {

    public Wall(Node node, GamePanel panel) {
        this.panel = panel;
        parent = node;
        position = parent.getPosition();
        this.setImage(panel.getWallImage());
    }

    public void paintSelf(int x, int y, Graphics g) {
        g.setColor(Color.red);
        g.drawImage(this.getImage(), x * panel.getBlockSize(), y * panel.getBlockSize(), panel.getBlockSize(), panel.getBlockSize(), null);
    }
}
