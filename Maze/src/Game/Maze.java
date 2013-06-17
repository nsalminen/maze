package Game;

import Sprites.*;
import Window.*;
import Utilities.Level;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class builds an array of Nodes based on a given 2D integer Array. It
 * also sets the occupants of these nodes to create the generateMaze.
 *
 * @author Yasen Dinkov and Nels Salminen
 */
public class Maze {

    /**
     * The parent panel of this generateMaze
     */
    GamePanel panel;
    public ArrayList<Node> floors = new ArrayList<Node>();
    /**
     * The array of Node objects that makeup the generateMaze's structure
     */
    public Node[][] nodes;
    public int[][] maze;
    private int pathFindertotalSteps;
    public boolean showPath;
    private Random random;
    private Dimension dimension;
    public ArrayList<String[]> level = new ArrayList<String[]>();
    
    public Point playerPoint;
    public Point portalGunPoint;
    public Point timeMachinePoint;
    public Point helperPoint;
    public Point goalPoint;
    
    /**
     * @param generateMaze A 2D integer Array that is used as a blueprint for the generateMaze
     * @param p The parent panel of the generateMaze object
     */
    
    public Maze(GamePanel panel, Level level) {
        dimension = new Dimension(15, 15);
        this.panel = panel;
        nodes = new Node[level.layout.length][level.layout[0].length];
        buildMaze(level);
    }
    
    public Maze(GamePanel panel) {
        dimension = new Dimension(15, 15);
        random = new Random();
        this.panel = panel;
        generateMaze();
        nodes = new Node[dimension.width][dimension.height];
        buildMaze(this.maze);
    }

    /**
     * Takes a given 2D integer array and sets the occupants for the 2D Node
     * array accordingly. 0 = wall 'w' `1 = empty 'e'
     *
     * @param generateMaze A 2D integer Array that is used as a blueprint for the generateMaze
     */
    

    private int[][] generateMaze() {
        maze = new int[dimension.height][dimension.width];
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
        generate(r, c);
         level.ensureCapacity(maze[0].length);
         String[] line = new String[maze[0].length];
                
         for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
               // line[j]= ""+maze[i][j];
                //System.out.print(maze[i][j]);
                
            }
            //System.out.println();
//            level.set(i, line);
         }
        
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(maze.length + " " + maze[0].length);
        
        return maze;
    }

    public void generate(int r, int c) {
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
                        generate(r - 2, c);
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
                        generate(r, c + 2);
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
                        generate(r + 2, c);
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
                        generate(r, c - 2);
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
     * This recursive backtracker finds paths through mazes. Given the current location in
     * the generateMaze, it tries all possible values for the next location. If any of
     * these values are valid, it moves and recursively calls this method with a
     * new location. This recursive algorithm finishes when the
     * current location in the generateMaze is the exit or if the algorithm exhausted
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

    private boolean isExit(Node node) {
        if (node.getyInd() == nodes.length - 2 && node.getxInd() == nodes[0].length - 2) {
            return true;
        } else {
            return false;
        }
    }

    private void enterNode(Node node) {
        nodes[node.getyInd()][node.getxInd()].setPath(true);
        pathFindertotalSteps++;
    }

    private void exitNode(Node node) {
        nodes[node.getyInd()][node.getxInd()].setPath(false);
        pathFindertotalSteps--;
    }

    private boolean nodeClear(Node node) {
        if (node.getyInd() < 0 || node.getxInd() >= nodes.length || node.getxInd() < 0 || node.getyInd() >= nodes[node.getyInd()].length || node.isVisited()) {
            return false;
        }
        return (!(nodes[node.getxInd()][node.getyInd()]).isWall() || isExit(nodes[node.getxInd()][node.getyInd()]));
    }

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
     * Takes a given 2D integer array and sets the occupants for the 2D Node
     * array accordingly. 0 = wall 'w' `1 = empty 'e'
     *
     * @param maze A 2D integer Array that is used as a blueprint for the generateMaze
     */
     private void buildMaze(Level level) {
        Point pointer = new Point();
        for (int y = 0; y < level.layout.length; y++) {     
        
            for (int x = 0; x < level.layout[0].length; x++) {
                pointer.setLocation(x,y);
                nodes[y][x] = new Node(pointer);
                nodes[y][x].addOccupant(new Floor(nodes[y][x], panel));
                if(level.layout[y][x] == 0){
                    getNode(pointer).addOccupant(new Wall(getNode(pointer),panel));
                }
                if(level.layout[y][x] == 1){
                    getNode(pointer).addOccupant(new Floor(getNode(pointer),panel));
                }
                if(level.layout[y][x] == 2){
                    playerPoint = new Point(x,y);
                    System.out.println("check player");
                }
                if(level.layout[y][x] == 3){
                    portalGunPoint = new Point(x,y);
                    System.out.println("check gun");
                }
                if(level.layout[y][x] == 4){
                    timeMachinePoint = new Point(x,y);
                    System.out.println("check tm");
                }
                if(level.layout[y][x] == 5){
                    helperPoint = new Point(x,y);
                    System.out.println("check helper");
                }
                if(level.layout[y][x] == 6){
                    goalPoint = new Point(x,y);
                    System.out.println("check goal");
                }
            }
        }
    }
    
    private void buildMaze(int[][] maze) {
        Point pointer = new Point();
        
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                pointer.setLocation(x,y);
                nodes[y][x] = new Node(pointer);
                nodes[y][x].addOccupant(new Floor(nodes[y][x], panel));
                if(maze[y][x] == 1){
                    floors.add(nodes[y][x]);
                }
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
                if ((nodes[y][x].popOccupant()) instanceof Wall) {                    
                    ((Wall)getNode(x,y).popOccupant()).paintSelf(x,y,g);
                }
                if ((nodes[y][x].popOccupant()) instanceof Floor) {
                    ((Floor) nodes[y][x].popOccupant()).paintSelf( g, nodes[x][y].isPath(), showPath);
                }
                
            }
        }
        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {
                
                if ((nodes[y][x].popOccupant()) instanceof Player) {
                    
                    ((Player) nodes[y][x].popOccupant()).paintSelf(g);
                }
                
                if ((nodes[y][x].popOccupant()) instanceof PortalGun) {
                    ((PortalGun) nodes[y][x].popOccupant()).paintSelf(g);
                }
                
                if ((nodes[y][x].popOccupant()) instanceof Goal) {
                   
                    ((Goal) nodes[y][x].popOccupant()).paintSelf(g);
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
    public ArrayList<Node> getFloors() {
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
    public void setFloors(ArrayList<Node> floors) {
        this.floors = floors;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
    
    public Level buildLevel(){
        
        int [][] layout = new int[nodes.length][nodes[0].length];
        
        for(int y = 0; y < nodes.length; y++){
            for(int x = 0; x < nodes.length; x++){
                
                if(nodes[y][x].popOccupant() instanceof Wall){
                    layout[y][x] = 0;
                }
                if(nodes[y][x].popOccupant() instanceof Floor){
                    layout[y][x] = 1;
                }
                if(nodes[y][x].popOccupant() instanceof Player){
                    layout[y][x] = 2;
                }
                if(nodes[y][x].popOccupant() instanceof PortalGun){
                    layout[y][x] = 3;
                }
                if(nodes[y][x].popOccupant() instanceof TimeMachine){
                    layout[y][x] = 4;
                }
                if(nodes[y][x].popOccupant() instanceof Helper){
                    layout[y][x] = 5;
                }
                if(nodes[y][x].popOccupant() instanceof Goal){
                    layout[y][x] = 6;
                }                
            }
        }
        
        Level leveler = new Level(layout,panel.player.stepsTaken, panel.player.hasPortalGun);
        
        return leveler;
    }
}
