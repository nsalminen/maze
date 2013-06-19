/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Utilities.Position;
import java.util.Stack;

/**
 *
 * @author Yasen
 */
public class Level {
    
    
    public int score;
    public boolean portalGun;
    public int[][] layout;
    public Stack<Position> positions;
    public boolean showPath;
    
    public Level(int[][] lt, int sc, boolean pg, Stack<Position> path, boolean sp)
    {
        layout = lt;
        score = sc;
        portalGun = pg;
        positions = path;
        showPath = sp;
    }

    @Override
    public String toString() {
        String string;
        
        string = "Score= "+score+"\n"+
                 "Portal Gun= "+portalGun+"\n"+
                 "Show Path="+showPath+"\n";
        
        while(!positions.isEmpty()){
           string = string + positions.pop().toString();
        }
        
        string = string+"\n";
         
        for(int y = 0 ;y < layout.length; y++){
            for(int x = 0 ;x < layout.length; x++){
                string = string+(layout[y][x]);                
            }
           string = string+"\n";
           }
        
        return string;
    }
    
   
    
    public void print(){
         //System.out.println(toString());
    }
}
