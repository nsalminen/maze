package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen and Nels
 */
public class Helper extends Sprite {

    public boolean taken;

    public Helper(Node n, GamePanel pan) {
        parent = n;
        panel = pan;
        parent.addOccupant(this);
        this.setImage(panel.helperImage);
    }

    /**
     * Paints the Helper onto a canvas.
     *
     * @param g Is required for drawing onto a canvas
     */
    public void paintSelf(Graphics g) {
        g.setColor(Color.orange);
        g.drawImage(this.getImage(), parent.xInd * panel.blockSize, parent.yInd * panel.blockSize, panel.blockSize, panel.blockSize, null);
    }
}
