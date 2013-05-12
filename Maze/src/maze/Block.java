/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author Nels
 */
public class Block {
    
    public int blockID;
    public boolean visited;
    public boolean legal;
    public int xPosition;
    public int yPosition;
    
    public Block(){
        blockID = 0;
        visited = false;
        legal = false;
        xPosition = 100;
        yPosition = 100;
    }
    
    public Block ( int xPosition, int yPosition, int blockID){
        this.blockID = blockID;
        visited = false;
        legal = false;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
