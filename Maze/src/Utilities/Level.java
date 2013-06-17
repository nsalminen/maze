/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.ArrayList;

/**
 *
 * @author Yasen
 */
public class Level {
    
    
    public int score;
    public boolean portalGun;
    public int[][] layout;
    
    public Level(int[][] lt, int sc, boolean pg)
    {
        layout = lt;
        score = sc;
        portalGun = pg;
    }

    @Override
    public String toString() {
        String string;
        
        string = "Score= "+score+"\n"+
                          "Portal Gun= "+portalGun+"\n";
                         
        for(int y = 0 ;y < layout.length; y++){
            for(int x = 0 ;x < layout.length; x++){
                string = string+(layout[y][x]);                
            }
           string = string+"\n";
           }
        
        return string;
    }
    
   
    
    public void print(){
         System.out.println(toString());
    }
}
