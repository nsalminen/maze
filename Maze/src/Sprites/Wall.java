package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen
 */
public class Wall extends Sprite {

    public boolean portal;

    public Wall(Node node, GamePanel panel) {
        this.panel = panel;
        parent = node;
        position = parent.getPosition();
        this.setImage(panel.wallImage);
    }

    public void paintSelf(int x, int y, Graphics g) {
        g.setColor(Color.red);
        g.drawImage(this.getImage(), x * panel.blockSize, y * panel.blockSize, panel.blockSize, panel.blockSize, null);
    }
}
