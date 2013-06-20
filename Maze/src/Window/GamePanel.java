
package Window;

import Game.*;
import Sprites.*;
import UserInterface.StepCounter;
import Utilities.FileLoader;
import Utilities.Level;
import Utilities.MazeKeyListener;
import Utilities.Position;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yasen
 */
public class GamePanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
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
    public Map<String, Integer> highscores;
    
    
    //The size of each block in pixels
    public int blockSize = 40;
    public Helper helper;
    public MainWindow parent;
    public FileLoader loader = new FileLoader();
    public float volume;
    public Image floorImage;
    public Image wallImage;
    public Image playerImage0;
    public Image playerImage1;
    public Image playerImage2;
    public Image playerImage3;
       
    public Image portalImage;
    public Image timeMachineImage;
    public Image goalImage;
    public Image helperImage;
    public Image portalOverlay;
    
    /**
     * Build new Game panel with a random generated maze and set a Frame as a parent
     * 
     * @param p MainWindow Frame as parent
     */
    
    public GamePanel(MainWindow p) {
        initComponents();
        parent = p;
        getGameImages();
        prepGame(getGraphics());
        this.setSize(maze.getDimension().height * blockSize, maze.getDimension().width * blockSize);
        MazeKeyListener listener = new MazeKeyListener(this);
        this.addKeyListener(listener);
        this.setFocusable(true);
    }
    
    /**
     * Build new Game panel with a pre-loaded maze and set a Frame as a parent
     * 
     * @param level Leven from saveed-file
     * @param p MainWindow Frame as parent
     */
    
    public GamePanel(Level level, MainWindow p) {
        initComponents();
        getGameImages();
        parent = p;
        loadGame(level, getGraphics());
        this.setSize(maze.getDimension().height * blockSize, maze.getDimension().width * blockSize);
        MazeKeyListener listener = new MazeKeyListener(this);
        this.addKeyListener(listener);
        this.setFocusable(true);
    }

    /**
     * Loads all of the Images needed for the srites
     */
    private void getGameImages() {
        try {
            playerImage0 = ImageIO.read(loader.getImageFile("Player0"));
            playerImage1 = ImageIO.read(loader.getImageFile("Player1"));
            playerImage2 = ImageIO.read(loader.getImageFile("Player2"));
            playerImage3 = ImageIO.read(loader.getImageFile("Player3"));
            portalOverlay = ImageIO.read(loader.getImageFile("portalOverlay"));

            floorImage = ImageIO.read(loader.getImageFile("Floor"));
            wallImage = ImageIO.read(loader.getImageFile("Wall2"));
            portalImage = ImageIO.read(loader.getImageFile("Portal"));
            timeMachineImage = ImageIO.read(loader.getImageFile("TimeMachine"));
            goalImage = ImageIO.read(loader.getImageFile("Goal"));
            helperImage = ImageIO.read(loader.getImageFile("Helper"));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    


    
    /**
     * Creates random number
     */
    private int random() {
        Random random = new Random();
        return Math.abs(random.nextInt());
    }

     /**
     * This method builds a maze from a Level object
     * 
     * @param level The level object containing all the needed information
     * @param g A Graphics object used to draw the new maze
     */
    public void loadGame(Level level, Graphics g) {
        Stack<Position> pos = new Stack<>();
        maze = new Maze(this, level);
        player = new Player(maze.playerPoint, this);
        player.stepsTaken = level.score;
        player.hasPortalGun = level.portalGun;
        if (maze.portalGunPoint != null) {
            portalGun = new PortalGun(maze.getNode(maze.portalGunPoint), this);
        }
        if (maze.timeMachinePoint != null) {
            timeMachine = new TimeMachine(maze.getNode(maze.timeMachinePoint), this);
        }
        if (maze.helperPoint != null) {
            helper = new Helper(maze.getNode(maze.helperPoint), this);
        }
        if (maze.goalPoint != null) {
            goal = new Goal(maze.getNode(maze.goalPoint), this);
        }

        if (level.showPath) {
            maze.findPath(maze.getNode(player.position));
            maze.showPath = true;
        }

        while (!level.positions.isEmpty()) {
            pos.push(level.positions.pop());
        }
        player.steps = pos;
        counter = new StepCounter((maze.nodes.length * blockSize) + blockSize, 0, this);
        cursor = new Cursor(maze.nodes.length - 1, 0, this);
    }

    /**
     * This method builds a random maze and places items randomly around
     * 
     * @param g A Graphics object used to draw the new maze
     */
    public final void prepGame(Graphics g) {
        Point pointer = new Point(999, 999);
        maze = new Maze(this);
        pointer.setLocation(maze.maze[0].length - 2, maze.maze.length - 2);
        System.out.println("Pointer :" + pointer);
        goal = new Goal(maze.getNode(pointer), this);
        pointer.setLocation(1, 1);
        player = new Player(pointer, this);
        portalGun = new PortalGun(maze.floors.get(random() % maze.floors.size()), this);
        timeMachine = new TimeMachine(maze.floors.get(random() % maze.floors.size()), this);
        helper = new Helper(maze.floors.get(random() % maze.floors.size()), this);
        counter = new StepCounter((maze.maze.length * blockSize) + blockSize, 0, this);
        cursor = new Cursor(maze.maze[0].length - 1, 0, this);
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
    }

    
    /**
     * Triggers a gameOver in parent
     */
    public void gameOver() {
        parent.gameOver();
    }    
    /**
     * Triggers a gameOver in parent
     */
    public void checkGoal() {
        if (goal.getPosition() == (player.getPosition())) {
            gameOver();
        }
    }
    
    /**
     * This method determines the actions based on the key used 
     *
     */
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
                break;
            case KeyEvent.VK_ESCAPE:
                parent.goToMenu();
                break;
            case KeyEvent.VK_Z:
                player.undoMove();
                this.repaint();
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
    public int getBlockSize() {
        return blockSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        blockSize = parent.getSize().height / maze.nodes.length;
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
