/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Sprites.Player;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yasen
 */
public class StepCounter {
    
    private int x ;
    private int y ;
    private Player player;
    private int blockSize;
    
    public StepCounter(int xPos, int yPos, GamePanel p){
        
        player = p.player;
        blockSize = p.blockSize;
        x = xPos;
        y = yPos;
            
    }
    public void drawSteps(Graphics g)
    {
        String steps = ""+ player.stepsTaken;        
        g.setColor(Color.blue);
        g.drawRect(x, y, blockSize, blockSize);
        g.drawString(steps, x+(blockSize/2)-(3*steps.length()), y+25);   
    }
}
