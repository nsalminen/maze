package Game;

import Sprites.*;
import Window.*;
import java.awt.Graphics;
import java.awt.Point;
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
    public boolean showPath;

    /**
     * @param maze A 2D integer Array that is used as a blueprint for the maze
     * @param p The parent panel of the maze object
     */
    public Maze(int[][] maze, GamePanel panel) {
        
        
        this.panel = panel;
        nodes = new Node[maze.length][maze[0].length];
        buildMaze(maze);
    }

   
    /**
     * This recursive backtracker finds paths through mazes. Given a location in
     * the maze, it tries all possible values for the next location. If any of
     * these values is valid, it moves and recursively calls this method with a
     * new location in the maze. This recursive algorithm finishes when the
     * current location in the maze is the exit or if the algorithm exhausted
     * all possible paths in the maze.
     *
     * @param current
     * @return
     * @author Nels Salminen
     */
    public boolean findPath(Node current) {
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
        if (node.getxInd() == nodes.length - 1 && node.getyInd() == nodes[0].length - 1) {
            return true;
        } else {
            return false;
        }
    }

    private void markNode(Node node) {
        nodes[node.getxInd()][node.getyInd()].setPath(true);
        pathFindertotalSteps++;
    }

    private void exitNode(Node node) {
        nodes[node.getxInd()][node.getyInd()].setPath(false);
        pathFindertotalSteps--;
    }

    private boolean nodeClear(Node node) {
        if (node.getxInd() < 0 || node.getyInd() >= nodes.length || node.getyInd() < 0 || node.getyInd() >= nodes[node.getxInd()].length || node.isVisited()) {
            return false;
        }
        return (!(nodes[node.getxInd()][node.getyInd()]).isWall() || isExit(nodes[node.getxInd()][node.getyInd()]));
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
     * Takes a given 2D integer array and sets the occupants for the 2D Node
     * array accordingly. 0 = wall 'w' `1 = empty 'e'
     *
     * @param maze A 2D integer Array that is used as a blueprint for the maze
     */
    private void buildMaze(int[][] maze) {
        Point pointer = new Point(999,999);
        
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                pointer.setLocation(x,y);
                nodes[y][x] = new Node(pointer);
                nodes[y][x].addOccupant(new Floor(nodes[y][x], panel));
                floors.add((Floor) nodes[y][x].popOccupant());
                if(maze[y][x] == 0){
                    getNode(pointer).addOccupant(new Wall(getNode(pointer),panel));
                }
            }
        }
    }


    /**
     * Goes through the Node array and paints a wall for every Node occupied by
     * a wall
     *
     * @param g A Graphics object
     */
    public void paintMaze(Graphics g) {
        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {
                if (nodes[y][x].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {                    
                    ((Wall)getNode(x,y).popOccupant()).paintSelf(x,y,g);
                }
                if (nodes[y][x].popOccupant().getClass().getCanonicalName().equals("Sprites.Floor")) {
                    ((Floor) nodes[y][x].popOccupant()).paintSelf( g, nodes[x][y].isPath(), showPath);
                }
                if (nodes[y][x].popOccupant().getClass().getCanonicalName().equals("Sprites.Player")) {
                    ((Player) nodes[y][x].popOccupant()).paintSelf(g);
                }
            }
        }
        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {
                
                if (nodes[y][x].popOccupant().getClass().getCanonicalName().equals("Sprites.Goal")) {
                    ((Goal) nodes[y][x].popOccupant()).paintSelf(g);
                }
                if (nodes[y][x].popOccupant().getClass().getCanonicalName().equals("Sprites.PortalGun")) {
                    ((PortalGun) nodes[y][x].popOccupant()).paintSelf(g);
                }
                if (nodes[y][x].popOccupant().getClass().getCanonicalName().equals("Sprites.TimeMachine")) {
                    ((TimeMachine) nodes[y][x].popOccupant()).paintSelf(g);
                }
                if (nodes[y][x].popOccupant().getClass().getCanonicalName().equals("Sprites.Helper")) {
                    ((Helper) nodes[y][x].popOccupant()).paintSelf(g);
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

    public Node getNode(Point p){
        Node node = nodes[p.y][p.x];        
        return node;
    }
    
    public Node getNode(int x, int y){
        Node node = nodes[y][x];
        return node;
    }
    /**
     * @param floors the floors to set
     */
    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }
}
