/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Utilities.Position;
import java.util.Stack;

/**
 * This class is used to easily take a pre-built maze and format it into its essential components
 * A level object can be used to re-build a maze.
 * 
 * @author Yasen
 */
public class Level {
    
    public int score;
    public boolean portalGun;
    public int[][] layout;
    public Stack<Position> positions;
    public boolean showPath;
    
        /**
        * This class is used to easily take a pre-built maze and format it into its essential components
        * A level object can be used to re-build a maze.
        * 
        * @param lt Layout: An int array that holds the values for each node in a maze.
        *           value 0 = Wall
        *           value 1 = open Floor
        *           value 2 = Player
        *           value 3 = PortalGun
        *           value 4 = TimeMachine
        *           value 5 = Helper
        *           value 6 = Goal
        * 
        * @param sc Score: The Player's score at the moment of saving
        * @param pg PortalGon: Determines whether or not the Player is carrying the PortalGun
        * @param path A Stack of Position objects that determine the playes moves up until th emoment of saving
        * @param sp Show Path: Determines whether or not the Maze is showing a path to the exit.
        * 
        * @author Yasen
        */
    public Level(int[][] lt, int sc, boolean pg, Stack<Position> path, boolean sp)
    {
        layout = lt;
        score = sc;
        portalGun = pg;
        positions = path;
        showPath = sp;
    }

    @Override
    /**
      * This method takes all the values of the Level and formats them into a single string
      * This methof is used to write down a level in a saveFile
      * 
      * @return string The level in the following format:
      *     "Score= SCORE
      *     Portal Gun= boolean
      *     Show Path=boolean
      *     <<positions Stack>>
      *     <<layout Array>>"
      * @author Yasen
      */
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
            for(int x = 0 ;x < layout[0].length; x++){
                string = string+(layout[y][x]);                
            }
           string = string+"\n";
           }
        
        return string;
    }    
}
