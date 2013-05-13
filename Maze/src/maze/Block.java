/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Point;

/**
 *
 * @author Nels
 */
public class Block {
    
    public int blockID;
    public boolean visited;
    public boolean legal;
    public Point position;
    
    public Block(){
        blockID = 0;
        visited = false;
        legal = false;
    }
    
    public Block ( int xPosition, int yPosition, int blockID){
        this.blockID = blockID;
        visited = false;
        legal = false;
        this.position.x = xPosition;
        this.position.y = yPosition;
    }
}
