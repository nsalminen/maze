/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Game.*;
import Sprites.*;
import UserInterface.StepCounter;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Random;
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
    public StepCounter counter;
    public TimeMachine timeMachine;
    //The size of each block in pixels
    public final int blockSize = 40;
    public int[][] hardMaze = {
        {1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
        {0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1}};


    public GamePanel() {
        initComponents();
        this.setSize(hardMaze.length * blockSize, hardMaze.length * blockSize);
        prepGame(getGraphics());
        MazeKeyListener listener = new MazeKeyListener(this);
        this.addKeyListener(listener);
        this.setFocusable(true);
    }        
    
    private int random(){
        Random random = new Random();
        return Math.abs(random.nextInt());
    }
    
    public void prepGame(Graphics g){
        maze = new Maze(hardMaze, this);
        goal = new Goal((hardMaze.length-1), (hardMaze[0].length-1), this);
        player = new Player(0, 0, this,getGraphics());
        cursor = new Cursor(hardMaze[0].length-1, 0, this);
        counter = new StepCounter(hardMaze.length*blockSize, 0 , this);
        
        
            int porty;
            int portx ;
        
       //Collections.shuffle(maze.floors);
       
            portx = maze.floors.get(random()% maze.floors.size()).yIndex;
            porty = maze.floors.get(random()% maze.floors.size()).xIndex;
            
        portalGun = new PortalGun(portx, porty, this);
        
        //Collections.shuffle(maze.floors); 
            portx = maze.floors.get(random()% maze.floors.size()).yIndex;
            porty = maze.floors.get(random()% maze.floors.size()).xIndex;
       
                           
        timeMachine = new TimeMachine(portx, porty, this);
    }

    /**
     * Determines which action has to be executed after the user activated a
     * key.
     *
     * @param key A variable that passes the key code of the active key
     */
    public void updateGame(KeyEvent e) {
        keyInput(e.getKeyCode());
        repaint();
        checkGoal();
        //checkPortalGun();
    }

    public void gameOver() {
        MainWindow.mazeWindow.setContentPane(MainWindow.mazeWindow.win);
        MainWindow.mazeWindow.setVisible(true);
    }

    public void checkGoal() {
        if (maze.nodes[goal.yIndex][goal.xIndex].popOccupant().getClass().getCanonicalName().equals("Sprites.Player")) {
            gameOver();
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
        counter.drawSteps(g);
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
