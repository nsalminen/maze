package Game;


import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import Sprites.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yasen
 */
public class Maze {
   
    public final int gridSize = 40;
    
    private int xPos;
    private int yPos;
    
    private int[][] maze;
    GamePanel panel;
    
    public Node[][] nodes;
   
    public Maze(GamePanel p){
       xPos = gridSize;
       yPos = gridSize;
       panel = p;
       maze = panel.hardMaze;
       nodes = new Node[maze.length][maze[0].length];  
       buildMaze();
    }
    public void buildMaze(){
        int id = 0;
        for (int x = 0; x < maze.length; x++ ){
            for(int y = 0; y < maze[0].length; y++){
               nodes[x][y] = new Node(x,y,id);               
               if(maze[x][y] == 0){
               nodes[x][y].setOccupant('w');
               }
               else{
               nodes[x][y].setOccupant('n');
               }
               id++;
            }
        }
    }
    
    public void paintMaze (Graphics g){
        buildMaze();        
        for(int x = 0 ; x < nodes.length;x++){        
            for(int y = 0 ; y < nodes[x].length; y++){
                if(nodes[x][y].getOccupant() == 'w'){
                   paintWall(y,x, g);
               }
            }            
        }
    }
    
        public void paintWall(int x, int y,Graphics g){   
       g.setColor(Color.red);
       g.fillRect(x*gridSize, y*gridSize, gridSize, gridSize);
    }
    
    
    
     
}