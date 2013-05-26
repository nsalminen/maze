/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Window.GamePanel;

/**
 *
 * @author Yasen
 */
public abstract class Sprite {
    
    public int xPos;
    public int yPos;    
        
    public int xInd;
    public int yInd;  
    
    public GamePanel panel;    
 
    public Sprite[] neighbours = new Sprite[4]; 
    
    public Sprite(){
    }
    
    public  void getNeighbours(){
          
          if(yInd-1 >= 0){ 
            neighbours[0] = panel.maze.nodes[yInd-1][xInd].getOccupant(); // NORTH NEIGHBOUR at Index:0;
          }
          if(xInd+1 < (panel.hardMaze[0].length)){ 
            neighbours[1] = panel.maze.nodes[yInd][xInd+1].getOccupant(); // EAST NEIGHBOUR at Index:1;
          }
          if(yInd+1 < (panel.hardMaze.length)){
          neighbours[2] = panel.maze.nodes[yInd+1][xInd].getOccupant(); // SOUTH NEIGHBOUR at Index:2;
          }
          if(xInd-1 >= 0){ 
          neighbours[3] = panel.maze.nodes[yInd][xInd-1].getOccupant(); // WEST NEIGHBOUR at Index:3;
          }
    }
    
    public int getX(){          
          System.out.print(xPos);
          return xPos;           
      }
      
      public int getY(){
          System.out.print(yPos);
          return yPos;
      }
      
      public void updatePos(){
          xPos = xInd*panel.blockSize;          
          yPos = yInd*panel.blockSize;
      }
}
