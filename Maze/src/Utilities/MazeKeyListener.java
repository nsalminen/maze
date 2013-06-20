package Utilities;

import Window.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/*
 * A key listener class that updates a the game when a key is pressed
 * 
 * @author Yasen and Nels
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
        if (panel instanceof GamePanel) {
            ((GamePanel) panel).updateGame(e);
        } else if (panel instanceof MenuPanel) {
            ((MenuPanel) panel).updateGame(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
