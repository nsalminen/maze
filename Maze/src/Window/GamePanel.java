

package Window;

import Game.*;
import Sprites.*;
import UserInterface.ScoreBoard;
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
    private Goal goal;
    private Player player;
    private Maze maze;
    private PortalGun portalGun;
    private StepCounter counter;
    private TimeMachine timeMachine;
    private Map<String, Integer> highscores;
    private ScoreBoard scoreboard;
    
    
    //The size of each block in pixels
    private int blockSize = 40;
    private Helper helper;
    private MainWindow parent;
    private FileLoader loader = new FileLoader();
    private float volume;
    private Image floorImage;
    private Image wallImage;
    private Image playerImage0;
    private Image playerImage1;
    private Image playerImage2;
    private Image playerImage3;
       
    private Image portalImage;
    private Image timeMachineImage;
    private Image goalImage;
    private Image helperImage;
    private Image portalOverlay;
    
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
            setPlayerImage0(ImageIO.read(getLoader().getImageFile("Player0")));
            setPlayerImage1(ImageIO.read(getLoader().getImageFile("Player1")));
            setPlayerImage2(ImageIO.read(getLoader().getImageFile("Player2")));
            setPlayerImage3(ImageIO.read(getLoader().getImageFile("Player3")));
            setPortalOverlay(ImageIO.read(getLoader().getImageFile("portalOverlay")));

            setFloorImage(ImageIO.read(getLoader().getImageFile("Floor")));
            setWallImage(ImageIO.read(getLoader().getImageFile("Wall2")));
            setPortalImage(ImageIO.read(getLoader().getImageFile("Portal")));
            setTimeMachineImage(ImageIO.read(getLoader().getImageFile("TimeMachine")));
            setGoalImage(ImageIO.read(getLoader().getImageFile("Goal")));
            setHelperImage(ImageIO.read(getLoader().getImageFile("Helper")));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Update the master volume according to the Settings panel
     */
    public void setVolume() {       
        getPlayer().getSfb().setVolume(parent.setting.volume/10);        
    }
    
    /**
     * Update the music volume according to the Settings panel
     */    
    public void setMusicVolume() {        

        parent.menu.music.setVolume(parent.setting.music/10);
    }

    /**
     * Turns sound on
     */
    public void volumeOn() {
        getPlayer().getSfb().volumeOn();
        parent.menu.music.volumeOn();
    }
    /**
     * Turns sound on
     */
    public void volumeOff() {
        getPlayer().getSfb().volumeOff();
        parent.menu.music.volumeOff();
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
     * @param level The level objed containing all the needed information
     * @param g A Graphics object used to draw the new maze
     */
    public void loadGame(Level level, Graphics g) {
        Stack<Position> pos = new Stack<>();
        setMaze(new Maze(this, level));
        setPlayer(new Player(getMaze().getPlayerPoint(), this));
        player.stepsTaken = level.score;
        player.setHasPortalGun(level.portalGun);
        if (getMaze().getPortalGunPoint() != null) {
            setPortalGun(new PortalGun(getMaze().getNode(getMaze().getPortalGunPoint()), this));
        }
        if (getMaze().getTimeMachinePoint() != null) {
            setTimeMachine(new TimeMachine(getMaze().getNode(getMaze().getTimeMachinePoint()), this));
        }
        if (getMaze().getHelperPoint() != null) {
            setHelper(new Helper(getMaze().getNode(getMaze().getHelperPoint()), this));
        }
        if (getMaze().getGoalPoint() != null) {
            setGoal(new Goal(getMaze().getNode(getMaze().getGoalPoint()), this));
        }

        if (level.showPath) {
            getMaze().findPath(getMaze().getNode(getPlayer().position));
            maze.setShowPath(true);
        }

        while (!level.positions.isEmpty()) {
            pos.push(level.positions.pop());
        }
        player.steps = pos;
        setCounter(new StepCounter((getMaze().getNodes().length * getBlockSize()) + getBlockSize(), 0, this));
       
    }

    /**
     * This method builds a random maze and places items randomly around
     * 
     * @param g A Graphics object used to draw the new maze
     */
    public final void prepGame(Graphics g) {
        Point pointer = new Point(999, 999);
        setMaze(new Maze(this));
        pointer.setLocation(getMaze().getMaze()[0].length - 2, getMaze().getMaze().length - 2);
        System.out.println("Pointer :" + pointer);
        setGoal(new Goal(getMaze().getNode(pointer), this));
        pointer.setLocation(1, 1);
        setPlayer(new Player(pointer, this));
        setPortalGun(new PortalGun(getMaze().getFloors().get(random() % getMaze().getFloors().size()), this));
        setTimeMachine(new TimeMachine(getMaze().getFloors().get(random() % getMaze().getFloors().size()), this));
        setHelper(new Helper(getMaze().getFloors().get(random() % getMaze().getFloors().size()), this));
        setCounter(new StepCounter((getMaze().getMaze().length * getBlockSize()) + getBlockSize(), 0, this));
       
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
        if (getGoal().getPosition() == (getPlayer().getPosition())) {
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
                getPlayer().moveNorth();
                break;
            case KeyEvent.VK_D:
                getPlayer().moveEast();
                break;
            case KeyEvent.VK_S:
                getPlayer().moveSouth();
                break;
            case KeyEvent.VK_A:
                getPlayer().moveWest();
                break;
            case KeyEvent.VK_SPACE:
                getPlayer().shoot();
                break;
            
            case KeyEvent.VK_ESCAPE:
                parent.goToMenu();
                break;
            case KeyEvent.VK_Z:
                getPlayer().undoMove();
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
        setBlockSize(parent.getSize().height / getMaze().getNodes().length);
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        getMaze().paintMaze(g);
        getCounter().drawSteps(g);

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

    /**
     * @return the goal
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * @param goal the goal to set
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * @param maze the maze to set
     */
    public void setMaze(Maze maze) {
        this.maze = maze;
    }


    /**
     * @return the portalGun
     */
    public PortalGun getPortalGun() {
        return portalGun;
    }

    /**
     * @param portalGun the portalGun to set
     */
    public void setPortalGun(PortalGun portalGun) {
        this.portalGun = portalGun;
    }

    /**
     * @return the counter
     */
    public StepCounter getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(StepCounter counter) {
        this.counter = counter;
    }

    /**
     * @return the timeMachine
     */
    public TimeMachine getTimeMachine() {
        return timeMachine;
    }

    /**
     * @param timeMachine the timeMachine to set
     */
    public void setTimeMachine(TimeMachine timeMachine) {
        this.timeMachine = timeMachine;
    }

    /**
     * @return the highscores
     */
    public Map<String, Integer> getHighscores() {
        return highscores;
    }

    /**
     * @param highscores the highscores to set
     */
    public void setHighscores(Map<String, Integer> highscores) {
        this.highscores = highscores;
    }

    /**
     * @return the scoreboard
     */
    public ScoreBoard getScoreboard() {
        return scoreboard;
    }

    /**
     * @param scoreboard the scoreboard to set
     */
    public void setScoreboard(ScoreBoard scoreboard) {
        this.scoreboard = scoreboard;
    }

    /**
     * @param blockSize the blockSize to set
     */
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * @return the helper
     */
    public Helper getHelper() {
        return helper;
    }

    /**
     * @param helper the helper to set
     */
    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    /**
     * @return the loader
     */
    public FileLoader getLoader() {
        return loader;
    }

    /**
     * @param loader the loader to set
     */
    public void setLoader(FileLoader loader) {
        this.loader = loader;
    }

    /**
     * @return the volume
     */
    public float getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(float volume) {
        this.volume = volume;
    }

    /**
     * @return the floorImage
     */
    public Image getFloorImage() {
        return floorImage;
    }

    /**
     * @param floorImage the floorImage to set
     */
    public void setFloorImage(Image floorImage) {
        this.floorImage = floorImage;
    }

    /**
     * @return the wallImage
     */
    public Image getWallImage() {
        return wallImage;
    }

    /**
     * @param wallImage the wallImage to set
     */
    public void setWallImage(Image wallImage) {
        this.wallImage = wallImage;
    }

    /**
     * @return the playerImage0
     */
    public Image getPlayerImage0() {
        return playerImage0;
    }

    /**
     * @param playerImage0 the playerImage0 to set
     */
    public void setPlayerImage0(Image playerImage0) {
        this.playerImage0 = playerImage0;
    }

    /**
     * @return the playerImage1
     */
    public Image getPlayerImage1() {
        return playerImage1;
    }

    /**
     * @param playerImage1 the playerImage1 to set
     */
    public void setPlayerImage1(Image playerImage1) {
        this.playerImage1 = playerImage1;
    }

    /**
     * @return the playerImage2
     */
    public Image getPlayerImage2() {
        return playerImage2;
    }

    /**
     * @param playerImage2 the playerImage2 to set
     */
    public void setPlayerImage2(Image playerImage2) {
        this.playerImage2 = playerImage2;
    }

    /**
     * @return the playerImage3
     */
    public Image getPlayerImage3() {
        return playerImage3;
    }

    /**
     * @param playerImage3 the playerImage3 to set
     */
    public void setPlayerImage3(Image playerImage3) {
        this.playerImage3 = playerImage3;
    }

    /**
     * @return the portalImage
     */
    public Image getPortalImage() {
        return portalImage;
    }

    /**
     * @param portalImage the portalImage to set
     */
    public void setPortalImage(Image portalImage) {
        this.portalImage = portalImage;
    }

    /**
     * @return the timeMachineImage
     */
    public Image getTimeMachineImage() {
        return timeMachineImage;
    }

    /**
     * @param timeMachineImage the timeMachineImage to set
     */
    public void setTimeMachineImage(Image timeMachineImage) {
        this.timeMachineImage = timeMachineImage;
    }

    /**
     * @return the goalImage
     */
    public Image getGoalImage() {
        return goalImage;
    }

    /**
     * @param goalImage the goalImage to set
     */
    public void setGoalImage(Image goalImage) {
        this.goalImage = goalImage;
    }

    /**
     * @return the helperImage
     */
    public Image getHelperImage() {
        return helperImage;
    }

    /**
     * @param helperImage the helperImage to set
     */
    public void setHelperImage(Image helperImage) {
        this.helperImage = helperImage;
    }

    /**
     * @return the portalOverlay
     */
    public Image getPortalOverlay() {
        return portalOverlay;
    }

    /**
     * @param portalOverlay the portalOverlay to set
     */
    public void setPortalOverlay(Image portalOverlay) {
        this.portalOverlay = portalOverlay;
    }
}
