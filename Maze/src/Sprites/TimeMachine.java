package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen and Nels
 */
public class TimeMachine extends Sprite {

    public boolean taken;
    public int stepsReduced = 20;

    public TimeMachine(Node n, GamePanel pan) {
        parent = n;
        panel = pan;
        parent.addOccupant(this);
        this.setImage(panel.getTimeMachineImage());
    }

    /**
     * Paints the TimeMachine onto a canvas.
     *
     * @param g Is required for drawing onto a canvas
     */
    public void paintSelf(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.drawImage(this.getImage(), parent.getxInd() * panel.getBlockSize(), parent.getyInd() * panel.getBlockSize(), panel.getBlockSize(), panel.getBlockSize(), null);

    }
}
