package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen and Nels
 */
public class Floor extends Sprite {

    public Floor(Node node, GamePanel panel) {
        parent = node;
        position = parent.getPosition();
        this.panel = panel;
        this.setImage(panel.getFloorImage());
    }

    /**
     *
     * @param g Graphics of JPanel, required to draw onto a panel.
     * @param path Whether the Floor is a path or not.
     * @param showPath Whether the path is currently graphically visible for the
     * user
     */
    public void paintSelf(Graphics g, boolean path, boolean showPath) {
        g.setColor(Color.lightGray);
        g.drawImage(this.getImage(), parent.getxInd() * panel.getBlockSize(), parent.getyInd() * panel.getBlockSize(), panel.getBlockSize(), panel.getBlockSize(), null);

        if (path && showPath) {
            g.setColor(Color.darkGray);
            g.fillOval(parent.getxInd() * panel.getBlockSize() + panel.getBlockSize() / 2 - 7, parent.getyInd() * panel.getBlockSize() + panel.getBlockSize() / 2 - 7, panel.getBlockSize() / 2, panel.getBlockSize() / 2);
        }
    }
}
