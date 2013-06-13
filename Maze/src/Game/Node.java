/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Sprites.*;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Yasen
 */
public class Node {
    public ArrayList<Sprite> occupants = new ArrayList<Sprite>();
    
    public Point position = new Point(999,999);
    public int xInd;
    public int yInd;
    private boolean visited = false;
    private boolean generationVisited = false;
    private boolean open;
    private boolean path;

    
    
    public boolean removeWall() {
        for (Sprite p : occupants) {
            if (p instanceof Wall) {
                occupants.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isWall() {
        if (popOccupant() instanceof Wall) {
            return true;
        } else {
            return false;
        }
    }
            
    public Node(Point p) {
        position = p;        
        xInd = position.x;
        yInd = position.y;
    }
    
    public Node(int x, int y) {
        xInd = x;
        yInd = y;        
        position.setLocation(xInd, yInd);
    }

    public Sprite getOccupant(int index) {
        if (index > occupants.size()) {
            return occupants.get(index);
        } else {
            return null;
        }
    }

    public void setOccupant(int index, Sprite occupant) {
        occupants.set(index, occupant);
    }

    public void trimOccupants(int trimSize) {

        occupants.remove(trimSize);

    }

    public void removeOccupantType(char type) {
        for (Sprite occu : occupants) {
            if ((occu instanceof Wall) && type == 'w') {
                occupants.remove(occu);
            }
        }
    }

    public Sprite popOccupant() {
        Sprite sprite = occupants.get(occupants.size() - 1);
        return sprite;
    }

    public void addOccupant(Sprite sprite) {
        occupants.add(sprite);
    }

    @Override
    public String toString() {

        String occ = "";

        occ = occupants.toString();

        String string = "x = " + xInd + "; y = " + yInd + "; occupied by: " + occ;



        return string;
    }

    /**
     * @return the xInd
     */
    public int getxInd() {
        return xInd;
    }

    /**
     * @param xInd the xInd to set
     */
    public void setxInd(int xInd) {
        this.xInd = xInd;
    }
    
    public Point getPosition(){
        return position;
    }

    /**
     * @return the yInd
     */
    public int getyInd() {
        return yInd;
    }

    /**
     * @param yInd the yInd to set
     */
    public void setyInd(int yInd) {
        this.yInd = yInd;
    }

    /**
     * @return the path
     */
    public boolean isPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(boolean path) {
        this.path = path;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
