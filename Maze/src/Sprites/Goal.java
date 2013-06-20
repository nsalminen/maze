package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen and Nels
 */
public class Goal extends Sprite {

    public Goal(Node n, GamePanel pan) {
        parent = n;
        panel = pan;
        parent.addOccupant(this);
        this.setImage(panel.goalImage);
    }

    /**
     * Paints the Goal onto a canvas.
     *
     * @param g Is required for drawing onto a canvas
     */
    public void paintSelf(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawImage(this.getImage(), parent.getxInd() * panel.blockSize, parent.getyInd() * panel.blockSize, panel.blockSize, panel.blockSize, null);

    }
}
