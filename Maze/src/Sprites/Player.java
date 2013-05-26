package Sprites;

import java.awt.Color;
import java.awt.Graphics;
import maze.MazePanelForm;
import MazeLevel.*;
import maze.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yasen
 */
public class Player{
    
    public boolean moving = false;    
    private int xPos;
    private int yPos;    
    private MazePanelForm panel;    
    public int direction;    
    private int xInd;
    private int yInd;    
    private char[] neighbours = new char[4];   
    
    public Player(int x, int y, MazePanelForm p){      
       xInd = x;
       yInd = y;       
       panel = p;       
       xPos = xInd * panel.maze.gridSize;
       yPos = yInd * panel.maze.gridSize;
    }
     
      public void paintSelf(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(xPos, yInd*panel.maze.gridSize, panel.maze.gridSize, panel.maze.gridSize);
        g.setColor(Color.CYAN);
        
        
        if(getDirection() == 0){
            g.drawLine((xPos)+(panel.maze.gridSize/2),
                        (yPos)+(panel.maze.gridSize/2),
                        //This second vertex shows the direction
                        (xPos)+(panel.maze.gridSize/2),
                        yPos);
        }
        else if(getDirection() == 1){
            g.drawLine((xPos)+(panel.maze.gridSize/2),
                        (yPos)+(panel.maze.gridSize/2),
                        //This second vertex shows the direction
                        (xPos)+(panel.maze.gridSize),
                        yPos+(panel.maze.gridSize/2));
        }
        else if(getDirection() == 2){
            g.drawLine((xPos)+(panel.maze.gridSize/2),
                        (yPos)+(panel.maze.gridSize/2),
                        //This second vertex shows the direction
                        (xPos)+(panel.maze.gridSize/2),
                        yPos+(panel.maze.gridSize));
        }
        else if(getDirection() == 3){
            g.drawLine((xPos)+(panel.maze.gridSize/2),
                        (yPos)+(panel.maze.gridSize/2),
                        //This second vertex shows the direction
                        (xPos),
                        yPos+(panel.maze.gridSize/2));
        }
        getNeighbours();
    }
      
      private  void getNeighbours(){
          
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
          for (char n :neighbours){
              System.out.println(n);
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
      
      public int getDirection(){
          return direction;
      }    
      
      public void setDirection(int dir){
            direction = dir;
      }
      
      public void updatePos(){
          xPos = xInd*panel.maze.gridSize;          
          yPos = yInd*panel.maze.gridSize;
      }
      
      public void move(int key){
                  
          //Check for 'W' key
          if(key == 87 ){
              setDirection(0);
            //Check for top border
            if(yInd-1 >= 0){ 
              //Check for open NORTH neighbour
              if(neighbours[0]!='w'){
                  yInd = yInd - 1;
                  updatePos();
              }
            }
          }
          //Check for 'D' key
          else if (key == 68){
                setDirection(1);
            //Check for right border
            if(xInd+1 < (panel.hardMaze[0].length)){ 
              //Check for open Index
              if(neighbours[1]!='w'){
                xInd = xInd + 1;
                  updatePos();
              }
            }
          }
          //Check for 'S' key 
          else if (key == 83){
              setDirection(2);
              //Check for bottom border
              if(yInd+1 < (panel.hardMaze.length)){
                //Check for open Index
                if(neighbours[2]!='w'){
                  yInd = yInd + 1;
                  updatePos();
                }
              }
          }
        //Check for 'A' key
        else if (key == 65){
            setDirection(3);
            //Check for left border
            if(xInd-1 >= 0){ 
                //Check for open Index
                if(neighbours[3]!='w'){
                    xInd = xInd - 1;
                  updatePos();
                }
            }
        }
        panel.maze.nodes[yInd][xInd].setOccupant('p');
     }
}

      
