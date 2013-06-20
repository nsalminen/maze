/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Window.MainWindow;
import java.awt.Point;
import junit.framework.TestCase;

/**
 *
 * @author Yasen
 */
public class PlayerTest extends TestCase {
    
    public MainWindow main = new MainWindow();
    public Player instance;
    
    public Point expected;
    
   
    
    public PlayerTest(String testName) {
        super(testName);
        
        
    }
     
    @Override
    protected void setUp() throws Exception {
        super.setUp();
       main.startGame();
        instance = main.game.player;; 
       
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * Test of move method, of class Player.
     */
    
     public void testMoveEast(){
        Point start;
        instance.setDirection(1);
        expected = new Point(999,999);
        start = main.game.player.getPosition(); 
        int steps = 0;
        while(!(main.game.maze.getNode(instance.facing).isWall())){
            expected.setLocation(instance.facing);
            instance.move();
            steps++;           
            this.assertEquals(expected, main.game.player.getPosition());
            //System.out.println("Moved!");
           // System.out.println("START"+start.toString());
        }
         //System.out.println("FOUNDWALL");
         expected.setLocation(1+steps, 1);
         this.assertEquals(expected, main.game.player.getPosition());
    }
    
    public void testMoveSouth(){
        Point start;
        instance.setDirection(2);
        expected = new Point(999,999);
        start = main.game.player.getPosition(); 
        int steps = 0;
        while(!(main.game.maze.getNode(instance.facing).isWall())){
            expected.setLocation(instance.facing);
            instance.move();
            steps++;           
            this.assertEquals(expected, main.game.player.getPosition());
            //System.out.println("Moved!");
           // System.out.println("START"+start.toString());
        }
         //System.out.println("FOUNDWALL");
         expected.setLocation(1, 1+steps);
         this.assertEquals(expected, main.game.player.getPosition());
    }
    
    public void testMoveWest(){
        Point start;
        instance.setDirection(3);
        expected = new Point(999,999);
        start = main.game.player.getPosition(); 
        int steps = 0;
        while(!(main.game.maze.getNode(instance.facing).isWall())){
            expected.setLocation(instance.facing);
            instance.move();
            steps++;           
            this.assertEquals(expected, main.game.player.getPosition());
            //System.out.println("Moved!");
           // System.out.println("START"+start.toString());
        }
        // System.out.println("FOUNDWALL");
         expected.setLocation(1-steps, 1);
         this.assertEquals(expected, main.game.player.getPosition());
    }
    public void testMoveNorth(){
        Point start;
        instance.setDirection(0);
        expected = new Point(999,999);
        start = main.game.player.getPosition(); 
        int steps = 0;
        while(!(main.game.maze.getNode(instance.facing).isWall())){
            expected.setLocation(instance.facing);
            instance.move();
            steps++;           
            this.assertEquals(expected, main.game.player.getPosition());
           // System.out.println("Moved!");
            //System.out.println("START"+start.toString());
        }
         //System.out.println("FOUNDWALL");
         expected.setLocation(1, 1-steps);
         this.assertEquals(expected, main.game.player.getPosition());
    }  
}
