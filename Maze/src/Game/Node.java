/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Sprites.*;

/**
 *
 * @author Yasen
 */
public class Node {

    public int id;
    private Sprite occupant;
    public int xInd;
    public int yInd;

    public Node(int x, int y, int nodeId) {
        xInd = x;
        yInd = y;
        id = nodeId;
    }

    public Sprite getOccupant() {
        return occupant;
    }

    public void setOccupant(Sprite occ) {
        occupant = occ;
    }

    @Override
    public String toString() {
        String string = "x = " + xInd + "; y = " + yInd + "; occupied by: " + occupant.getClass().getCanonicalName();
        return string;
    }
}
