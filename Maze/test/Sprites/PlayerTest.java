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
    
    public MainWindow main;
    public Player instance;
    
    public Point expected;
    
   
    
    public PlayerTest(String testName) {
        super(testName);
        
        
    }
     
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        main = new MainWindow();
        main.startGame();
        instance = main.getGame().player;; 
       
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * Test of move method, of class Player.
     */
    
     public void test1(){
        Point start;
        instance.setDirection(1);
        expected = new Point(999,999);
        start = main.getGame().player.getPosition(); 
        int steps = 0;
        while(!(main.getGame().maze.getNode(instance.facing).isWall())){
            expected.setLocation(instance.facing);
            instance.move();
            steps++;           
            this.assertEquals(expected, main.getGame().player.getPosition());
        }
         expected.setLocation(1+steps, 1);
         this.assertEquals(expected, main.getGame().player.getPosition());
         instance.moveSouth();
         this.assertEquals(expected, main.getGame().player.getPosition());
         this.assertEquals(2, main.getGame().player.direction);
    }
    
    public void test2(){
        Point start;
        start = main.getGame().player.getPosition(); 
        instance.setDirection(2);
        instance.moveWest();
        this.assertEquals(3, main.getGame().player.direction);
        this.assertEquals(start, main.getGame().player.getPosition());
        expected = new Point(999,999);
        
        int steps = 0;
        while(!(main.getGame().maze.getNode(instance.facing).isWall())){
            expected.setLocation(instance.facing);
            instance.move();
            steps++;           
            this.assertEquals(expected, main.getGame().player.getPosition());
        }
         System.out.println("FOUNDWALL"); 
         expected.setLocation(1, 1+steps);
         this.assertEquals(expected, main.getGame().player.getPosition());
    }
    
    public void test3(){
        Point start;
        instance.setDirection(3);
        expected = new Point(999,999);
        start = main.getGame().player.getPosition(); 
        int steps = 0;
        while(!(main.getGame().maze.getNode(instance.facing).isWall())){
            expected.setLocation(instance.facing);
            instance.move();
            steps++;           
            this.assertEquals(expected, main.getGame().player.getPosition());
        }
         expected.setLocation(1-steps, 1);
         this.assertEquals(expected, main.getGame().player.getPosition());
    }
}
