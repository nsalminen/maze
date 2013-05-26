package Sprites;

import java.awt.Color;
import java.awt.Graphics;
import Window.GamePanel;


/**
 *
 * @author Yasen
 */
public class Player extends Sprite{

    public int direction;
    
    public Player(int x, int y, GamePanel p){      
       xInd = x;
       yInd = y;       
       panel = p;       
       xPos = xInd * panel.blockSize;
       yPos = yInd * panel.blockSize;
    }
     
      public void paintSelf(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(xPos, yInd*panel.blockSize, panel.blockSize, panel.blockSize);
        g.setColor(Color.CYAN);
        
        
        if(getDirection() == 0){
            g.drawLine((xPos)+(panel.blockSize/2),
                        (yPos)+(panel.blockSize/2),
                        //This second vertex shows the direction
                        (xPos)+(panel.blockSize/2),
                        yPos);
        }
        else if(getDirection() == 1){
            g.drawLine((xPos)+(panel.blockSize/2),
                        (yPos)+(panel.blockSize/2),
                        //This second vertex shows the direction
                        (xPos)+(panel.blockSize),
                        yPos+(panel.blockSize/2));
        }
        else if(getDirection() == 2){
            g.drawLine((xPos)+(panel.blockSize/2),
                        (yPos)+(panel.blockSize/2),
                        //This second vertex shows the direction
                        (xPos)+(panel.blockSize/2),
                        yPos+(panel.blockSize));
        }
        else if(getDirection() == 3){
            g.drawLine((xPos)+(panel.blockSize/2),
                        (yPos)+(panel.blockSize/2),
                        //This second vertex shows the direction
                        (xPos),
                        yPos+(panel.blockSize/2));
        }
        getNeighbours();
    }
      
      
      
   
      
      public int getDirection(){
          return direction;
      }    
      
      public void setDirection(int dir){
            direction = dir;
      }
      
      
      public void move(int key){
                  
          //Check for 'W' key
          if(key == 87 ){
              setDirection(0);
            //Check for top border
            if(yInd-1 >= 0){ 
              //Check for open NORTH neighbour
              if(!neighbours[0].getClass().getCanonicalName().equals("Sprites.Wall")){
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
              if(!neighbours[1].getClass().getCanonicalName().equals("Sprites.Wall")){
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
                if(!neighbours[2].getClass().getCanonicalName().equals("Sprites.Wall")){
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
                if(!neighbours[3].getClass().getCanonicalName().equals("Sprites.Wall")){
                    xInd = xInd - 1;
                  updatePos();
                }
            }
        }
        panel.maze.nodes[yInd][xInd].setOccupant(this);
     }
}

      
