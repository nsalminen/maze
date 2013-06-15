package Utilities;

import Window.GamePanel;
import Window.MenuPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yasen
 */
public class MazeKeyListener implements KeyListener {

    private JPanel panel;

    public MazeKeyListener(GamePanel p) {
        panel = p;
    }
    public MazeKeyListener(MenuPanel p) {
        panel = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Typed");
       if(panel instanceof GamePanel){
       ((GamePanel) panel).updateGame(e);
       }
       else if(panel instanceof MenuPanel){
       ((MenuPanel) panel).updateGame(e);
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
