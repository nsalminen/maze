package Game;

import Game.Node;
import Window.GamePanel;
import java.awt.Graphics;
import Sprites.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class builds an array of Nodes based on a given 2D int Array. It also
 * sets the occupants of these nodes to create the maze.
 *
 * @author Yasen
 */
public class Maze {

    /**
     * The parent panel of this maze
     */
    public GamePanel panel;
    public ArrayList<Node> floors = new ArrayList<>();
    public Node[][] nodes;
    private Queue<Node> queue = new LinkedList<>();
    private int width, height;

    /**
     * @param maze A 2D int Array that is used as a blueprint for the maze
     * @param p The parent panel of the maze object
     * @author Yasen
     */
    public Maze(int[][] maze, GamePanel panel) {
        this.panel = panel;
        //maze = mazeArray;
        nodes = new Node[maze.length][maze[0].length];
        buildMaze(maze);
        width = nodes[0].length;
        height = nodes.length;
        solveMaze();
    }

    /**
     * Takes a given 2D int array and sets the occupants for the 2D Node array
     * accordingly. 0 = wall 'w' `1 = empty 'e'
     *
     * @param maze A 2D int Array that is used as a blueprint for the maze
     */
    private void buildMaze(int[][] maze) {
        int id = 0;
        
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                nodes[x][y] = new Node(x, y, id);
                if (maze[x][y] == 0) {
                    nodes[x][y].setOccupant(new Wall(x, y, panel));
                } else {
                    nodes[x][y].setOccupant(new Floor(x, y, panel));
                    floors.add(nodes[x][y]);
                }
                nodes[x][y].setxInd(x);
                nodes[x][y].setyInd(y);
                id++;
            }
        }
    }
    
    private void solveMaze() {
        solveMaze(0, 0);
    }
    
    public void solveMaze(int x, int y) {
        queue.add(nodes[y][x]);
        while (!queue.isEmpty()) {
            Node visitedNode = queue.remove();
            if (isExit(visitedNode)) {
                System.out.println("Exit found");
            } else {
                ArrayList<Node> adjacentNodes = getAdjacentNodes(visitedNode.xInd, visitedNode.yInd);
                for (Node node : adjacentNodes) {
                    if (!node.isVisited() && !node.isWall()) {
                        if (adjacentNodes.size() == 3 | adjacentNodes.size() == 4) {
                            queue.add(node);
                        }
                    }
                }
                visitedNode.setVisited(true);
            }
        }
    }
    
    public ArrayList<Node> getAdjacentNodes(int x, int y) {
        ArrayList<Node> adjacencies = new ArrayList<>();
        if (y != 0) {
            adjacencies.add(nodes[x][y - 1]);
        }
        if (y < nodes[x].length - 1) {
            adjacencies.add(nodes[x][y + 1]);
        }
        if (x != 0) {
            adjacencies.add(nodes[x - 1][y]);
        }
        if (x < nodes.length - 1) {
            adjacencies.add(nodes[x + 1][y]);
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
                if (nodes[x][y].getOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                    ((Wall) nodes[x][y].getOccupant()).paintSelf(y, x, g);
                }
                
            }
        }
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[x].length; y++) {
                if (nodes[x][y].getOccupant().getClass().getCanonicalName().equals("Sprites.Player")) {
                    ((Player) nodes[x][y].getOccupant()).paintSelf(g);
                }
                if (nodes[x][y].getOccupant().getClass().getCanonicalName().equals("Sprites.Goal")) {
                    ((Goal) nodes[x][y].getOccupant()).paintSelf(g);
                }
                if (nodes[x][y].getOccupant().getClass().getCanonicalName().equals("Sprites.PortalGun")) {
                    ((PortalGun) nodes[x][y].getOccupant()).paintSelf(g);
                }
                if (nodes[x][y].getOccupant().getClass().getCanonicalName().equals("Sprites.TimeMachine")) {
                    ((TimeMachine) nodes[x][y].getOccupant()).paintSelf(g);
                }
            }
        }
        panel.cursor.paintSelf(g);
    }
    
    private boolean isExit(Node node) {
        if (node.getxInd() == 0 && node.getyInd() == nodes.length) {
            return true;
        } else {
            return false;
        }
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
    public ArrayList<Node> getFloors() {
        return floors;
    }

    /**
     * @param floors the floors to set
     */
    public void setFloors(ArrayList<Node> floors) {
        this.floors = floors;
    }
}
