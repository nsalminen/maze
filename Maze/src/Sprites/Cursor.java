/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import java.awt.Color;
import java.awt.Graphics;
import Window.GamePanel;
/**
 *
 * @author Yasen
 */
public class Cursor {
    
    private int xInd;
    private int yInd;
    private GamePanel panel;
    
    
    public Cursor(int x, int y, GamePanel p){
      
       xInd = x;
       yInd = y;
       
       panel = p;
       
    }
    
    public void paintSelf(Graphics g){
        g.setColor(Color.green);
        g.drawRect(xInd*panel.maze.gridSize, yInd*panel.maze.gridSize, panel.maze.gridSize, panel.maze.gridSize);
        g.drawRect(xInd*panel.maze.gridSize+1, yInd*panel.maze.gridSize, panel.maze.gridSize, panel.maze.gridSize);
        g.drawRect(xInd*panel.maze.gridSize, yInd*panel.maze.gridSize+1, panel.maze.gridSize, panel.maze.gridSize);
        g.drawRect(xInd*panel.maze.gridSize+1, yInd*panel.maze.gridSize+1, panel.maze.gridSize, panel.maze.gridSize);
   }
    
      public void move(int key){
                  
          //Check for 'W' key
          if(key == 38 ){
            //Check for top border
            if(yInd-1 >= 0){ 
                  yInd = yInd - 1;
            }
          }
          //Check for 'D' key
          else if (key == 39){
            //Check for right border
            if(xInd+1 < (panel.hardMaze[0].length)){ 
                xInd = xInd + 1;
            }
          }
          //Check for 'S' key 
          else if (key == 40){
              //Check for bottom border
              if(yInd+1 < (panel.hardMaze.length)){
                  yInd = yInd + 1;
              }
          }
        //Check for 'A' key
        else if (key == 37){
            //Check for left border
            if(xInd-1 >= 0){ 
                    xInd = xInd - 1;
            }
        }
        else if (key == 17){
           printCurrentNode();
        }         
     }
      
      private void printCurrentNode(){
          System.out.println(panel.maze.nodes[yInd][xInd].toString());
      }
}
