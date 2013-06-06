package Game;

import Sprites.*;
import Window.GamePanel;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class builds an array of Nodes based on a given 2D integer Array. It
 * also sets the occupants of these nodes to create the maze.
 *
 * @author Yasen Dinkov and Nels Salminen
 */
public class Maze {

    /**
     * The parent panel of this maze
     */
    GamePanel panel;
    
    public ArrayList<Floor> floors = new ArrayList<Floor>();
    
    /**
     * The array of Node objects that makeup the maze's structure
     */
    public Node[][] nodes;
    private int pathFindertotalSteps;
    private int width;
    private int height;

    /**
     * @param maze A 2D integer Array that is used as a blueprint for the maze
     * @param p The parent panel of the maze object
     */
    public Maze(int[][] maze, GamePanel panel) {
        this.panel = panel;
        nodes = new Node[maze.length][maze[0].length];
        buildMaze(maze);
        findPath(nodes[0][0]);
        width = nodes[0].length;
        height = nodes.length;
    }

    /**
     * Takes a given 2D integer array and sets the occupants for the 2D Node
     * array accordingly. 0 = wall 'w' `1 = empty 'e'
     *
     * @param maze A 2D integer Array that is used as a blueprint for the maze
     */
    private void buildMaze(int[][] maze) {
        int id = 0;

        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                nodes[x][y] = new Node(x, y, id);
                
                nodes[x][y].addOccupant(new Floor(x,y,panel));
                floors.add((Floor)nodes[x][y].popOccupant());
                
                
                if (maze[x][y] == 0) {
                nodes[x][y].addOccupant(new Wall(x, y, panel));
                } 
                
                
                id++;
            }
        }
    }

    /**
     * @param current
     * @return
     * @author Nels Salminen
     */
    private boolean findPath(Node current) {
        if (isExit(current)) {
            return true;
        }
        ArrayList<Node> adjacentNodes = getAdjacentNodes(current);
        for (Node potentialMove : adjacentNodes) {
            if (nodeClear(potentialMove)) {
                markNode(potentialMove);
                potentialMove.setVisited(true);
                if (findPath(potentialMove)) {
                    return true;
                }
                exitNode(potentialMove);
            }
        }
        return false;
    }

    private boolean isExit(Node node) {
        if (node.getxInd() == 12 && node.getyInd() == 12) {
            return true;
        } else {
            return false;
        }
    }

    private void markNode(Node node) {
        nodes[node.getxInd()][node.getyInd()].path = true;
        pathFindertotalSteps++;
    }

    private void exitNode(Node node) {
        nodes[node.getxInd()][node.getyInd()].path = false;
        pathFindertotalSteps--;
    }

    private boolean nodeClear(Node node) {
        if (node.getxInd() < 0 || node.getyInd() >= nodes.length || node.getyInd() < 0 || node.getyInd() >= nodes[node.getxInd()].length || node.isVisited()) {
            return false;
        }
        return (!(nodes[node.getxInd()][node.getyInd()]).isWall() || nodes[node.getxInd()][node.getyInd()].isExit());
    }

    public ArrayList<Node> getAdjacentNodes(Node node) {
        ArrayList<Node> adjacencies = new ArrayList<>();
        if (node.getyInd() != 0) {
            adjacencies.add(nodes[node.getxInd()][node.getyInd() - 1]);
        }
        if (node.getyInd() < nodes[node.getxInd()].length - 1) {
            adjacencies.add(nodes[node.getxInd()][node.getyInd() + 1]);
        }
        if (node.getxInd() != 0) {
            adjacencies.add(nodes[node.getxInd() - 1][node.getyInd()]);
        }
        if (node.getxInd() < nodes.length - 1) {
            adjacencies.add(nodes[node.getxInd() + 1][node.getyInd()]);
        }
        return adjacencies;
    }

    /**
     * Goes through the Node array and paints a wall for every Node occupied by
     * a wall
     *
     * @param g A Graphics object
     */
    public void paintMaze(Graphics g) {
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[x].length; y++) {
                if (nodes[x][y].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                    ((Wall) nodes[x][y].popOccupant()).paintSelf(y, x, g);
                }
                if (nodes[x][y].popOccupant().getClass().getCanonicalName().equals("Sprites.Floor")) {
                    ((Floor) nodes[x][y].popOccupant()).paintSelf(y, x, g, nodes[x][y].path);
                }
            }
        }
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[x].length; y++) {                
                if (nodes[x][y].popOccupant().getClass().getCanonicalName().equals("Sprites.Player")) {
                    ((Player) nodes[x][y].popOccupant()).paintSelf(g);
                }
                if (nodes[x][y].popOccupant().getClass().getCanonicalName().equals("Sprites.Goal")) {
                    ((Goal) nodes[x][y].popOccupant()).paintSelf(g);
                }
                if (nodes[x][y].popOccupant().getClass().getCanonicalName().equals("Sprites.PortalGun")) {
                    ((PortalGun) nodes[x][y].popOccupant()).paintSelf(g);
                }
                if (nodes[x][y].popOccupant().getClass().getCanonicalName().equals("Sprites.TimeMachine")) {
                    ((TimeMachine) nodes[x][y].popOccupant()).paintSelf(g);
                }
            }
        }
        panel.cursor.paintSelf(g);
    }

    /**
     * @return the nodes
     */
    public Node[][] getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the panel
     */
    public GamePanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(GamePanel panel) {
        this.panel = panel;
    }

    /**
     * @return the floors
     */
    public ArrayList<Floor> getFloors() {
        return floors;
    }

    /**
     * @param floors the floors to set
     */
    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }
}
