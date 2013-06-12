package Game;

import Sprites.*;
import Window.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    public int[][] maze;
    private int pathFindertotalSteps;
    public boolean showPath;
    private Random random;
    private Dimension dimension;

    /**
     * @param maze A 2D integer Array that is used as a blueprint for the maze
     * @param p The parent panel of the maze object
     */
    public Maze(GamePanel panel) {
        dimension = new Dimension(15, 15);
        random = new Random();
        this.panel = panel;
        maze();
        nodes = new Node[dimension.width][dimension.height];
        buildMaze(this.maze);
    }

    /**
     * Takes a given 2D integer array and sets the occupants for the 2D Node
     * array accordingly. 0 = wall 'w' `1 = empty 'e'
     *
     * @param maze A 2D integer Array that is used as a blueprint for the maze
     */
    private void buildMaze(int[][] maze) {
        int id = 0;
        for (int y = 0; y <= dimension.height - 1; y++) {
            for (int x = 0; x <= dimension.width - 1; x++) {
                nodes[y][x] = new Node(y, x, id);
                nodes[y][x].addOccupant(new Floor(y, x, panel));
                floors.add((Floor) nodes[y][x].popOccupant());
                if (maze[y][x] == 0) {
                    nodes[y][x].addOccupant(new Wall(y, x, panel));
                }
                id++;
            }
        }
        System.out.println(nodes.length + " " + nodes[0].length);
    }

    private int[][] maze() {
        maze = new int[dimension.height][dimension.width];
        // Initialize
        for (int i = 0; i < dimension.height; i++) {
            for (int j = 0; j < dimension.width; j++) {
                maze[i][j] = 0;
            }
        }

        Random rand = new Random();
        // r for row、c for column
        // Generate random r
        int r = rand.nextInt(dimension.height);
        while (r % 2 == 0) {
            r = rand.nextInt(dimension.height);
        }
        // Generate random c
        int c = rand.nextInt(dimension.width);
        while (c % 2 == 0) {
            c = rand.nextInt(dimension.width);
        }
        // Starting cell
        maze[r][c] = 0;

        //　Allocate the mazeGrid with recursive method
        recursion(r, c);

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(maze.length + " " + maze[0].length);
        return maze;
    }

    public void recursion(int r, int c) {
        // 4 random directions
        Integer[] randDirs = generateRandomDirections();
        // Examine each direction
        for (int i = 0; i < randDirs.length; i++) {

            switch (randDirs[i]) {
                case 1: // Up
                    //　Whether 2 cells up is out or not
                    if (r - 1 <= 1) {
                        continue;
                    }
                    if (maze[r - 2][c] != 1) {
                        maze[r - 2][c] = 1;
                        maze[r - 1][c] = 1;
                        recursion(r - 2, c);
                    }
                    break;
                case 2: // Right
                    // Whether 2 cells to the right is out or not
                    if (c + 1 >= dimension.width - 1) {
                        continue;
                    }
                    if (maze[r][c + 2] != 1) {
                        maze[r][c + 2] = 1;
                        maze[r][c + 1] = 1;
                        recursion(r, c + 2);
                    }
                    break;
                case 3: // Down
                    // Whether 2 cells down is out or not
                    if (r + 1 >= dimension.height - 1) {
                        continue;
                    }
                    if (maze[r + 2][c] != 1) {
                        maze[r + 2][c] = 1;
                        maze[r + 1][c] = 1;
                        recursion(r + 2, c);
                    }
                    break;
                case 4: // Left
                    // Whether 2 cells to the left is out or not
                    if (c - 1 <= 1) {
                        continue;
                    }
                    if (maze[r][c - 2] != 1) {
                        maze[r][c - 2] = 1;
                        maze[r][c - 1] = 1;
                        recursion(r, c - 2);
                    }
                    break;
            }
        }

    }

    /**
     * Generate an array with random directions 1-4
     *
     * @return Array containing 4 directions in random order
     */
    public Integer[] generateRandomDirections() {
        ArrayList<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            randoms.add(i + 1);
        }
        Collections.shuffle(randoms);

        return randoms.toArray(new Integer[4]);
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
                markNodePath(potentialMove);
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
        if (node.getxInd() == nodes.length - 2 && node.getyInd() == nodes[0].length - 2) {
            return true;
        } else {
            return false;
        }
    }

    private void markNodePath(Node node) {
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
                    ((Floor) nodes[x][y].popOccupant()).paintSelf(y, x, g, nodes[x][y].isPath(), showPath);
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
                if (nodes[x][y].popOccupant().getClass().getCanonicalName().equals("Sprites.Helper")) {
                    ((Helper) nodes[x][y].popOccupant()).paintSelf(g);
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

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
