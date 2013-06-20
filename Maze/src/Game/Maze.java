package Game;

import Sprites.*;
import Utilities.Level;
import Window.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class builds an array of Nodes based on a given two dimensional integer
 * Array. It also sets the occupants of these nodes to create the generateMaze.
 *
 * @author Yasen and Nels
 */
public class Maze {

    /**
     * The parent panel of this generateMaze
     */
    private GamePanel panel;
    public ArrayList<Node> floors = new ArrayList<>();
    /**
     * The array of Node objects that make up the generateMaze's structure
     */
    public Node[][] nodes;
    public int[][] maze;
    private boolean showPath;
    private Dimension dimension;
    private ArrayList<String[]> level = new ArrayList<>();
    private Point playerPoint;
    private Point portalGunPoint;
    private Point timeMachinePoint;
    private Point helperPoint;
    private Point goalPoint;

    /**
     * @param level The level object that will be turned into a playable level
     * @param p The parent panel of the generateMaze object
     */
    public Maze(GamePanel panel, Level level) {
        dimension = new Dimension(level.layout.length, level.layout[0].length);
        this.panel = panel;
        nodes = new Node[level.layout.length][level.layout[0].length];
        buildMaze(level);
    }

    public Maze(GamePanel panel) {
        dimension = new Dimension(31, 19);
        this.panel = panel;
        generateMaze();
        nodes = new Node[maze.length][maze[0].length];
        buildMaze(this.maze);
    }

    /**
     * Initializes all elements needed to generate the maze. This method will
     * start the series of recursive calls to generate()
     *
     * @return Returns finished integer array.
     */
    private int[][] generateMaze() {
        maze = new int[dimension.height][dimension.width];
        for (int i = 0; i < dimension.height; i++) {
            for (int j = 0; j < dimension.width; j++) {
                maze[i][j] = 0;
            }
        }
        Random rand = new Random();

        //Random row
        int r = rand.nextInt(dimension.height);
        while (r % 2 == 0) {
            r = rand.nextInt(dimension.height);
        }

        //Random column
        int c = rand.nextInt(dimension.width);
        while (c % 2 == 0) {
            c = rand.nextInt(dimension.width);
        }

        // Starting cell
        maze[r][c] = 0;

        //　Allocate the mazeGrid with recursive method
        generate(r, c);
        level.ensureCapacity(maze[0].length);

        return maze;
    }

    /**
     * This method is the recursive part of the maze generation. It keeps
     * repeating itself until it exhausted all cells.
     *
     * @param r This parameter specifies the current y position in the two
     * dimensional array
     * @param c This parameter specifies the current x position in the two
     * dimensional array
     */
    public void generate(int r, int c) {
        //Generates four random directions and assigns them to an Integer array
        Integer[] directions = generateRandomDirections();

        for (int i = 0; i < directions.length; i++) {
            switch (directions[i]) {
                case 1: // North
                    if (r - 1 <= 1) {
                        continue;
                    }
                    if (maze[r - 2][c] != 1) {
                        maze[r - 2][c] = 1;
                        maze[r - 1][c] = 1;
                        generate(r - 2, c);
                    }
                    break;
                case 2: // East
                    if (c + 1 >= dimension.width - 1) {
                        continue;
                    }
                    if (maze[r][c + 2] != 1) {
                        maze[r][c + 2] = 1;
                        maze[r][c + 1] = 1;
                        generate(r, c + 2);
                    }
                    break;
                case 3: // South
                    if (r + 1 >= dimension.height - 1) {
                        continue;
                    }
                    if (maze[r + 2][c] != 1) {
                        maze[r + 2][c] = 1;
                        maze[r + 1][c] = 1;
                        generate(r + 2, c);
                    }
                    break;
                case 4: // West
                    if (c - 1 <= 1) {
                        continue;
                    }
                    if (maze[r][c - 2] != 1) {
                        maze[r][c - 2] = 1;
                        maze[r][c - 1] = 1;
                        generate(r, c - 2);
                    }
                    break;
            }
        }

    }

    /**
     * Generates an Integer array of integers from one to four and randomizes
     * the order of the array.
     *
     * @return An Integer array containing numbers from one to four, in a random
     * order
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
     * This recursive backtracker finds paths through mazes. Given the current
     * location in the generateMaze, it tries all possible values for the next
     * location. If any of these values are valid, it moves and recursively
     * calls this method with a new location. This recursive algorithm finishes
     * when the current location in the generateMaze is the exit or if the
     * algorithm exhausted all possible paths in the maze.
     *
     * @param current The current Node.
     * @return Returns whether the cell in a specific direction is a valid move
     * or not. Or returns true when the exit is found.
     * @author Nels Salminen
     */
    public boolean findPath(Node current) {
        if (isExit(current)) {
            return true;
        }
        ArrayList<Node> adjacentNodes = getAdjacentNodes(current);
        for (Node potentialMove : adjacentNodes) {
            if (nodeClear(potentialMove)) {
                enterNode(potentialMove);
                potentialMove.setVisited(true);
                if (findPath(potentialMove)) {
                    return true;
                }
                exitNode(potentialMove);
            }
        }
        return false;
    }

    /**
     * Determines whether a Node has the position of an exit or not.
     *
     * @param node The location from this Node will be used
     * @return
     */
    private boolean isExit(Node node) {
        if (node.getyInd() == nodes.length - 2 && node.getxInd() == nodes[0].length - 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets a Node to path status true
     *
     * @param node
     */
    private void enterNode(Node node) {
        nodes[node.getyInd()][node.getxInd()].setPath(true);
    }

    /**
     * Sets a Node to path status false
     *
     * @param node
     */
    private void exitNode(Node node) {
        nodes[node.getyInd()][node.getxInd()].setPath(false);
    }

    /**
     * Checks whether a specific Node is free to enter or not.
     *
     * @param node This node will be examined by the method
     * @return Returns whether a specific Node is free to enter or not
     */
    private boolean nodeClear(Node node) {
        if (node.getyInd() < 0 || node.getyInd() >= nodes.length || node.getyInd() < 0 || node.getxInd() >= nodes[node.getyInd()].length || node.isVisited()) {
            return false;
        }
        return (!(nodes[node.getyInd()][node.getxInd()]).isWall() || isExit(nodes[node.getyInd()][node.getxInd()]));
    }

    /**
     *
     * @param node The method checks for any adjacent nodes around this
     * parameter
     * @return Returns an ArrayList of nodes that are adjacent to the parameter
     */
    public ArrayList<Node> getAdjacentNodes(Node node) {
        ArrayList<Node> adjacencies = new ArrayList<>();
        //West
        if (node.getxInd() != 0) {
            adjacencies.add(nodes[node.getyInd()][node.getxInd() - 1]);
        }
        //East
        if (node.getxInd() < nodes[node.getyInd()].length - 1) {
            adjacencies.add(nodes[node.getyInd()][node.getxInd() + 1]);
        }
        //North
        if (node.getyInd() != 0) {
            adjacencies.add(nodes[node.getyInd() - 1][node.getxInd()]);
        }
        //South
        if (node.getyInd() < nodes.length - 1) {
            adjacencies.add(nodes[node.getyInd() + 1][node.getxInd()]);
        }
        return adjacencies;
    }

    /**
     * Takes a given two dimensional integer array and sets the occupants for
     * the two dimensional Node array accordingly.
     *
     * @param level A Level object that has all values needed to build a maze
     */
    private void buildMaze(Level level) {
        Point pointer = new Point();
        for (int y = 0; y < level.layout.length; y++) {
            for (int x = 0; x < level.layout[0].length; x++) {
                pointer.setLocation(x, y);
                nodes[y][x] = new Node(pointer);
                nodes[y][x].addOccupant(new Floor(nodes[y][x], panel));
                if (level.layout[y][x] == 0) {
                    getNode(pointer).addOccupant(new Wall(nodes[y][x], panel));
                }
                if (level.layout[y][x] == 2) {
                    playerPoint = new Point(x, y);
                }
                if (level.layout[y][x] == 3) {
                    portalGunPoint = new Point(x, y);
                }
                if (level.layout[y][x] == 4) {
                    timeMachinePoint = new Point(x, y);
                }
                if (level.layout[y][x] == 5) {
                    helperPoint = new Point(x, y);
                }
                if (level.layout[y][x] == 6) {
                    goalPoint = new Point(x, y);
                }
            }
        }
    }

    /**
     * Takes a given two dimensional integer array and sets the occupants for
     * the two dimensional Node array accordingly.
     *
     * @param maze A two dimensional integer Array that is used as a blueprint
     * for the generateMaze
     */
    protected void buildMaze(int[][] maze) {
        Point pointer = new Point();

        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                pointer.setLocation(x, y);
                nodes[y][x] = new Node(pointer);
                nodes[y][x].addOccupant(new Floor(nodes[y][x], panel));
                if (maze[y][x] == 1) {
                    floors.add(nodes[y][x]);
                }
                if (maze[y][x] == 0) {
                    getNode(pointer).addOccupant(new Wall(getNode(pointer), panel));
                }
            }
        }
    }

    /**
     * Loops through the Node array and paints a wall for every Node occupied by
     * a Wall object.
     *
     * @param g A Graphics object
     */
    public void paintMaze(Graphics g) {
        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {
                ((Floor) nodes[y][x].getOccupant(0)).paintSelf(g, nodes[y][x].isPath(), showPath);
                if ((nodes[y][x].peekOccupant()) instanceof Wall) {
                    ((Wall) getNode(new Point(x, y)).peekOccupant()).paintSelf(x, y, g);
                }
            }
        }

        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {
                if ((nodes[y][x].peekOccupant()) instanceof Player) {
                    ((Player) nodes[y][x].peekOccupant()).paintSelf(g);
                }

                if ((nodes[y][x].peekOccupant()) instanceof PortalGun) {
                    ((PortalGun) nodes[y][x].peekOccupant()).paintSelf(g);
                }

                if ((nodes[y][x].peekOccupant()) instanceof Goal) {
                    ((Goal) nodes[y][x].peekOccupant()).paintSelf(g);
                }

                if (nodes[y][x].peekOccupant().getClass().getCanonicalName().equals("Sprites.TimeMachine")) {
                    ((TimeMachine) nodes[y][x].peekOccupant()).paintSelf(g);
                }

                if (nodes[y][x].peekOccupant().getClass().getCanonicalName().equals("Sprites.Helper")) {
                    ((Helper) nodes[y][x].peekOccupant()).paintSelf(g);
                }
            }
        }
    }

    /**
     * @return the nodes array
     */
    public Node[][] getNodes() {
        return nodes;
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
     * @return A Node at a specified point
     */
    public Node getNode(Point point) {
        Node node = nodes[point.y][point.x];
        return node;
    }

    /**
     * @return the dimension of the maze
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * @param dimension sets the dimension of the maze
     */
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Level buildLevel() {
        int[][] layout = new int[nodes.length][nodes[0].length];

        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {

                if (nodes[y][x].peekOccupant() instanceof Wall) {
                    layout[y][x] = 0;
                }
                if (nodes[y][x].peekOccupant() instanceof Floor) {
                    layout[y][x] = 1;
                }
                if (nodes[y][x].peekOccupant() instanceof Player) {
                    layout[y][x] = 2;
                }
                if (nodes[y][x].peekOccupant() instanceof PortalGun) {
                    layout[y][x] = 3;
                }
                if (nodes[y][x].peekOccupant() instanceof TimeMachine) {
                    layout[y][x] = 4;
                }
                if (nodes[y][x].peekOccupant() instanceof Helper) {
                    layout[y][x] = 5;
                }
                if (nodes[y][x].peekOccupant() instanceof Goal) {
                    layout[y][x] = 6;
                }
            }
        }

        Level leveler = new Level(layout, panel.player.stepsTaken, panel.player.hasPortalGun, panel.player.steps, showPath);
        return leveler;
    }

    /**
     * @return the playerPoint
     */
    public Point getPlayerPoint() {
        return playerPoint;
    }

    /**
     * @param playerPoint the playerPoint to set
     */
    public void setPlayerPoint(Point playerPoint) {
        this.playerPoint = playerPoint;
    }

    /**
     * @return the portalGunPoint
     */
    public Point getPortalGunPoint() {
        return portalGunPoint;
    }

    /**
     * @param portalGunPoint the portalGunPoint to set
     */
    public void setPortalGunPoint(Point portalGunPoint) {
        this.portalGunPoint = portalGunPoint;
    }

    /**
     * @return the timeMachinePoint
     */
    public Point getTimeMachinePoint() {
        return timeMachinePoint;
    }

    /**
     * @param timeMachinePoint the timeMachinePoint to set
     */
    public void setTimeMachinePoint(Point timeMachinePoint) {
        this.timeMachinePoint = timeMachinePoint;
    }

    /**
     * @return the helperPoint
     */
    public Point getHelperPoint() {
        return helperPoint;
    }

    /**
     * @param helperPoint the helperPoint to set
     */
    public void setHelperPoint(Point helperPoint) {
        this.helperPoint = helperPoint;
    }

    /**
     * @return the goalPoint
     */
    public Point getGoalPoint() {
        return goalPoint;
    }

    /**
     * @param goalPoint the goalPoint to set
     */
    public void setGoalPoint(Point goalPoint) {
        this.goalPoint = goalPoint;
    }

    /**
     * @return the showPath
     */
    public boolean isShowPath() {
        return showPath;
    }

    /**
     * @param showPath the showPath to set
     */
    public void setShowPath(boolean showPath) {
        this.showPath = showPath;
    }
}
