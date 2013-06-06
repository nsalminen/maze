package Game;

import Game.Node;
import Window.GamePanel;
import java.awt.Graphics;
import Sprites.*;
import java.util.ArrayList;

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
    GamePanel panel;
    
    public ArrayList<Floor> floors = new ArrayList<Floor>();
    
    /**
     * The array of Node objects that makeup the maze's structure
     */
    public Node[][] nodes;

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
        
    }

    /**
     * Takes a given 2D int array and sets the occupants for the 2D Node array
     * accordingly. 0 = wall 'w' `1 = empty 'e'
     *
     * @param maze A 2D int Array that is used as a blueprint for the maze
     */
    public void buildMaze(int[][] maze) {
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
                    ((Floor) nodes[x][y].popOccupant()).paintSelf(y, x, g);
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
}