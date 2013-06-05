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
    private boolean north;     // is there a wall to north of cell i, j
    private boolean east;
    private boolean south;
    private boolean west;
    private boolean visited = false;
    public boolean path;

    public boolean isNorth() {
        return north;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }
    
        public boolean isExit() {
        if (occupant instanceof Goal){
            return true;
        }else{
            return false;
        }
    }

    public boolean isEast() {
        return east;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean isSouth() {
        return south;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public boolean isWest() {
        return west;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public boolean isWall(){
        if (occupant instanceof Wall){
            return true;
        }else{
            return false;
        }
    }
            

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
}
