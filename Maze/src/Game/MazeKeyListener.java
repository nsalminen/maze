package Game;

import Window.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yasen
 */
public class MazeKeyListener implements KeyListener {

    private GamePanel panel;

    public MazeKeyListener(GamePanel p) {
        panel = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        panel.keyInput(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
