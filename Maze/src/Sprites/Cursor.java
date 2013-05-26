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
        g.drawRect(xInd*panel.blockSize, yInd*panel.blockSize, panel.blockSize, panel.blockSize);
        g.drawRect(xInd*panel.blockSize+1, yInd*panel.blockSize, panel.blockSize, panel.blockSize);
        g.drawRect(xInd*panel.blockSize, yInd*panel.blockSize+1, panel.blockSize, panel.blockSize);
        g.drawRect(xInd*panel.blockSize+1, yInd*panel.blockSize+1, panel.blockSize, panel.blockSize);
   }
    
      public void move(int key){
                  
          //Check for ´UpArrow' key
          if(key == 38 ){
            //Check for top border
            if(yInd-1 >= 0){ 
                  yInd = yInd - 1;
            }
          }
          //Check for 'RightArrow'' key
          else if (key == 39){
            //Check for right border
            if(xInd+1 < (panel.hardMaze[0].length)){ 
                xInd = xInd + 1;
            }
          }
          //Check for 'DownArrow'' key 
          else if (key == 40){
              //Check for bottom border
              if(yInd+1 < (panel.hardMaze.length)){
                  yInd = yInd + 1;
              }
          }
        //Check for 'LeftpArrow' key
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
