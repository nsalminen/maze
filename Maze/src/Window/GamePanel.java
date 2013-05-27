/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Game.*;
import java.awt.Graphics;
import Sprites.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Yasen
 */
public class GamePanel extends javax.swing.JPanel {

    /**
     * Creates new form MazePanelForm
     */
    
    public Goal goal;
    public Player player;
    public Maze maze;
    public Cursor cursor;
    public PortalGun portalGun;
    //The size of each block in pixels
    public final int blockSize = 40;
    public int[][] hardMaze = {
        {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
        {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1}};

    public GamePanel() {
        initComponents();
        this.setSize (hardMaze.length * blockSize, hardMaze.length * blockSize);
        
        maze = new Maze(hardMaze, this);
        
        goal = new Goal((hardMaze.length), (hardMaze.length-1), this);
        player = new Player(0, 0, this);
        cursor = new Cursor(hardMaze.length, 0, this);
        portalGun = new PortalGun(4,2,this);
        MazeKeyListener listener = new MazeKeyListener(this);
        this.addKeyListener(listener);
        this.setFocusable(true);
    }

    /**
     * Determines which action has to be executed after the user activated a
     * key.
     *
     * @param key A variable that passes the key code of the active key
     */
    
    public void updateGame(KeyEvent e){
        keyInput(e.getKeyCode());
        repaint();
        checkGoal();
        checkPortalGun();
    }
    
    public void gameOver(){
        System.out.println("GAME OVER! YOU WIN!");
    }    
    public void checkGoal(){
           if(maze.nodes[goal.yIndex][goal.xIndex].getOccupant().getClass().getCanonicalName().equals("Sprites.Player")){
                gameOver(); 
            }
    }
     public void checkPortalGun(){
           if(maze.nodes[portalGun.yIndex][portalGun.xIndex].getOccupant().getClass().getCanonicalName().equals("Sprites.Player")){
                maze.nodes[portalGun.yIndex][portalGun.xIndex].setOccupant(new Floor(portalGun.xIndex, portalGun.yIndex,this));
                player.portalGun = true;
               }
               
            }
           
    
    
    public void keyInput(int key) {
        switch (key) {
            case KeyEvent.VK_W:
                player.move('N');
                break;
            case KeyEvent.VK_D:
                player.move('E');
                break;
            case KeyEvent.VK_S:
                player.move('S');
                break;
            case KeyEvent.VK_A:
                player.move('W');
                break;
            case KeyEvent.VK_SPACE:
                player.shoot();
                break;
            case KeyEvent.VK_UP:
                cursor.move('N');
                break;
            case KeyEvent.VK_RIGHT:
                cursor.move('E');
                break;
            case KeyEvent.VK_DOWN:
                cursor.move('S');
                break;
            case KeyEvent.VK_LEFT:
                cursor.move('W');
                break;
            case KeyEvent.VK_CONTROL:
                cursor.printCurrentNode();
                
        }       
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
          maze.paintMaze(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
