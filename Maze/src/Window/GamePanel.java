/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Utilities.MazeKeyListener;
import Game.*;
import Sprites.*;
import UserInterface.ScoreBoard;
import UserInterface.StepCounter;
import Utilities.FileReaderWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Yasen
 */
public class GamePanel extends javax.swing.JPanel {

    /**
     * Creates new form MazePanelForm
     */
    
    public FileReaderWriter frw;
    public Goal goal;
    public Player player;
    public Maze maze;
    public Cursor cursor;
    public PortalGun portalGun;
    public StepCounter counter;
    public TimeMachine timeMachine;
    public ScoreBoard scoreBoard;
    public Map<String, Integer> highscores;  
    //The size of each block in pixels
    public final int blockSize = 40;
    public int[][] hardMaze = {
        {1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
        {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
        {1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0},
        {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
        {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1},
        {1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1},
        {0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1}};

    public Helper helper;
    private MainWindow parent;
    
    public GamePanel(MainWindow p) {
        initComponents();
        parent = p;
        this.setSize(hardMaze.length * blockSize, hardMaze.length * blockSize);
        
        frw = new FileReaderWriter();
        prepGame(getGraphics());
        
        MazeKeyListener listener = new MazeKeyListener(this);
        this.addKeyListener(listener);
        this.setFocusable(true);
    }

    private int random() {
        Random random = new Random();
        return Math.abs(random.nextInt());
    }

    public void prepGame(Graphics g) {
        Point pointer = new Point(999,999);
        maze = new Maze(hardMaze, this);
        pointer.setLocation(hardMaze.length-1,hardMaze.length-1);        
        goal = new Goal(maze.getNode(pointer), this);
        pointer.setLocation(0,0);
        player = new Player(pointer, this);
        portalGun = new PortalGun(maze.getNode(4, 0), this);
        timeMachine = new TimeMachine(maze.floors.get(random()% maze.floors.size()), this);
        helper = new Helper(maze.floors.get(random()% maze.floors.size()), this);
        counter = new StepCounter((hardMaze.length*blockSize)+blockSize, 0 , this);
        scoreBoard = new ScoreBoard((hardMaze.length*blockSize)+blockSize, 0 , this);
        cursor = new Cursor(hardMaze[0].length-1, 0, this);        
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
        parent.gameOver();;
    }

    public void checkGoal() {
//        if (goal.getPosition()==(player.getPosition())) {
//            gameOver();
//        }
    }

    public void keyInput(int key) {
        switch (key) {
            case KeyEvent.VK_W:
                player.moveNorth();
                break;
            case KeyEvent.VK_D:
                player.moveEast();
                break;
            case KeyEvent.VK_S:
                player.moveSouth();
                break;
            case KeyEvent.VK_A:
                player.moveWest();
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
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        maze.paintMaze(g);
        counter.drawSteps(g);
        scoreBoard.drawBoard(g);
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
