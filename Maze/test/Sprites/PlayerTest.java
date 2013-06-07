/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Window.GamePanel;
import Window.MainWindow;
import junit.framework.TestCase;
import Sprites.*;

/**
 *
 * @author Yasen
 */
public class PlayerTest extends TestCase {
    
    public Player instance;   
    
    public PlayerTest(String testName) {
        super(testName);
    }
     
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
       MainWindow main = new MainWindow();     
       main.startGame();
       
       instance = main.game.player;
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * Test of move method, of class Player.
     */
    
    public void testCaseMove1(){
        instance.setDirection(1);
        System.out.println("TEST CASE 1");     
        instance.move('S');
        System.out.println(instance.toString());
    }
    
    public void testCaseMove2(){
        System.out.println("TEST CASE 2");          
        instance.move('N');       
        System.out.println(instance.toString());
    }
    
    public void testCaseMove3(){
        System.out.println("TEST CASE 3:");        
        instance.move('S');
        System.out.println(instance.toString());
    }
    
    public void testCaseMove4(){
        System.out.println("TEST CASE 4:");        
        instance.move('E');
        System.out.println(instance.toString());
    }
    
    public void testCaseMove5(){
        System.out.println("TEST CASE 5:");        
        instance.move('W');
        System.out.println(instance.toString());
    }
    public void testCaseMove7(){
        System.out.println("TEST CASE 7:");        
        instance.move('S');
        System.out.println(instance.toString());
    }

}
