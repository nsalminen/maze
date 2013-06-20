package Game;

import Sprites.*;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Yasen
 */
public class Node {

    private ArrayList<Sprite> occupants = new ArrayList<>();
    private Point position = new Point(999, 999);
    private int xInd;
    private int yInd;
    private boolean visited = false;
    private boolean path;

    public Node(Point position) {
        this.position = position;
        xInd = position.x;
        yInd = position.y;
    }

    public Node(int xInd, int yInd) {
        this.xInd = xInd;
        this.yInd = yInd;
        position.setLocation(xInd, yInd);
    }

    /**
     * Removes the wall that is placed on this Node.
     *
     * @return Whether a Wall object was removed or not
     */
    public boolean removeWall() {
        for (Sprite p : getOccupants()) {
            if (p instanceof Wall) {
                getOccupants().remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * @return return visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited Sets visited to this value
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return Returns whether the Node has a Wall occupant or not
     */
    public boolean isWall() {
        if (peekOccupant() instanceof Wall) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param index
     * @return Returns Sprite at specified parameter
     */
    public Sprite getOccupant(int index) {
        if (index <= getOccupants().size()) {
            return getOccupants().get(index);
        } else {
            return null;
        }
    }

    /**
     * Sets a Sprite to occupants at the specified parameter
     *
     * @param index
     * @param occupant
     */
    public void setOccupant(int index, Sprite occupant) {
        getOccupants().set(index, occupant);
    }

    /**
     * Removes occupant at specified parameter
     *
     * @param index
     */
    public void removeOccupant(int index) {
        getOccupants().remove(index);
    }

    /**
     * @return Returns occupant on top
     */
    public Sprite peekOccupant() {
        Sprite sprite = getOccupants().get(getOccupants().size() - 1);
        return sprite;
    }

    /**
     * Adds a Sprite to occupants
     *
     * @param sprite
     */
    public void addOccupant(Sprite sprite) {
        getOccupants().add(sprite);
    }

    @Override
    public String toString() {
        String occ = "";
        occ = getOccupants().toString();
        String string = "x = " + getxInd() + "; y = " + getyInd() + "; occupied by: " + occ;
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
     * @return The position of this Node in Point
     */
    public Point getPosition() {
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

    /**
     * @return the occupants
     */
    public ArrayList<Sprite> getOccupants() {
        return occupants;
    }

    /**
     * @param occupants the occupants to set
     */
    public void setOccupants(ArrayList<Sprite> occupants) {
        this.occupants = occupants;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }
}
