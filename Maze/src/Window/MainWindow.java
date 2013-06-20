package Window;

import Utilities.FileLoader;
import Utilities.Level;
import Utilities.SoundEffect;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import javax.swing.JFrame;

/**
 *
 * @author Nels
 */
public class MainWindow extends JFrame {

    /**
     * @return the mazeWindow
     */
    public static MainWindow getMazeWindow() {
        return null;//mazeWindow;
    }

    /**
     * @param aMazeWindow the mazeWindow to set
     */
    public static void setMazeWindow(MainWindow aMazeWindow) {
        //mazeWindow = aMazeWindow;
    }
    private FileLoader loader = new FileLoader();
    private static final long serialVersionUID = 1L;
    //private static MainWindow mazeWindow = new MainWindow();
    private MenuPanel menu = new MenuPanel(this);
    private OptionPanel setting;
    private SoundEffect music = new SoundEffect(getLoader().getSoundEffect("music"));
    private GamePanel game;
    private OptionPanel option;
    private WinPanel win;
    private Dimension windowDimension;
    private static GraphicsDevice vc;
    private boolean fullscreen = false;
    private boolean playing;
    private SoundEffect button;

    /**
     * Creates new MainWindow
     */
    public MainWindow() throws FileNotFoundException {
        initComponents();
        if (System.getProperty("os.name").equals("Mac OS X")) {
            enableOSXFullscreen(this);
        }

        windowDimension = new Dimension(1220, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(windowDimension);
        setContentPane(menu);
        setLocationRelativeTo(null);
        //setSetting(new OptionPanel(this));
        setting = new OptionPanel(this);
        button = new SoundEffect(getLoader().getSoundEffect("menu"));

        setMusicVolume(setting.music);
        if (setting.isMute()) {
            music.play();
        }

        if (setting.isFullScreen()) {
            this.setExtendedState(this.MAXIMIZED_BOTH);
            this.removeNotify();
            this.setUndecorated(true);
            this.setVisible(true);
        }

    }

    /**
     * opens a new SavePanel
     */
    public void saveGame() {
        SavePanel savepanel = new SavePanel(this);
        setContentPane(savepanel);
        savepanel.setVisible(true);
        savepanel.setFocusable(true);
        savepanel.requestFocus();
        savepanel.setSize(this.getSize());
    }

    /**
     * opens a new OptionPanel
     */
    public void showOptions() {
        try {
            setContentPane(setting);
            setting.setVisible(true);
            setting.setFocusable(true);
            setting.requestFocus();
            setting.setSize(this.getSize());
        } catch (Exception e) {
        }

    }

    /**
     * opens a new LoadPanel
     */
    public void loadGame() {

        LoadPanel loadpanel = new LoadPanel(this);
        setContentPane(loadpanel);
        loadpanel.setVisible(true);
        loadpanel.setFocusable(true);
        loadpanel.requestFocus();
        loadpanel.setSize(this.getSize());
    }

    /**
     * Returns to the menu
     */
    public void goToMenu() {
        setContentPane(getMenu());
        getMenu().setFocusable(true);
        getMenu().requestFocus();
        getMenu().setSize(this.getSize());
        getButton().play();
    }

    /**
     * Returns to the Active Game
     */
    public void unPauseGame() {
        setContentPane(getGame());
        getGame().setFocusable(true);
        getGame().requestFocus();
        getGame().setSize(this.getSize());
    }

    /**
     * Opens new HighScoreanel
     */
    public void showHighScores() {
        HighScorePanel scores = new HighScorePanel(this);
        setContentPane(scores);
        scores.setSize(this.getSize());
    }

    /**
     * Opens new HighScoreanel
     */
    public void showInstruction() {
        InstructionPanel instruction = new InstructionPanel(this);
        setContentPane(instruction);
        instruction.setSize(this.getSize());
    }

    /**
     * This method starts a new game based on a saved Level object
     *
     * @param level pre-built Level object
     */
    public void loadNewGame(Level level) {

        setGame(new GamePanel(level, this));
        setContentPane(getGame());
        getGame().setFocusable(true);
        getGame().requestFocus();
        getGame().repaint();
        getGame().setSize(this.getSize());
    }

    /**
     * This method starts a new game with a randombly genrated maze
     *
     */
    public void startGame() {
        setGame(new GamePanel(this));
        setContentPane(getGame());
        getGame().setFocusable(true);
        getGame().requestFocus();
        getGame().repaint();
        getGame().setSize(this.getSize());
        menu.activeGame(true);
    }

    /**
     * Opens a new WinPanel
     */
    public void gameOver() {
        getGame().setVisible(false);
        setWin(new WinPanel(this));
        setContentPane(getWin());
        getWin().setFocusable(true);
        getWin().requestFocus();
        getWin().repaint();
    }

    public boolean isPlaying() {
        return playing;
    }

    /**
     * Update the master volume according to the Settings panel
     */
    public void setVolume(int vol) {
        getGame().player.getSfb().setVolume(vol / 10);
        getGame().player.getPortalPickup().setVolume(vol / 10);
        getGame().player.getHelperpickup().setVolume(vol / 10);
        getGame().player.getTmpickup().setVolume(vol / 10);
    }

    /**
     * Update the music volume according to the Settings panel
     */
    public void setMusicVolume(int vol) {
        getMusic().setVolume(vol / 10);
    }

    /**
     * Turns sound on
     */
    public void volumeOn() {
        getGame().player.getSfb().volumeOn();
        getMenu().music.volumeOn();
    }

    /**
     * Turns sound on
     */
    public void volumeOff() {

        getGame().player.getSfb().volumeOff();
        getMenu().music.stop();
    }

    /**
     * This method turns on the native full screen support that is shipped with
     * OS X 10.7 or later
     *
     * @param window This parameter indicates which window has to be set to OS X
     * full screen mode.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void enableOSXFullscreen(Window window) {
        try {
            Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
            Class params[] = new Class[]{Window.class, Boolean.TYPE};
            Method method = util.getMethod("setWindowCanFullScreen", params);
            method.invoke(util, window, true);
        } catch (ClassNotFoundException e1) {
        } catch (Exception e) {
            System.out.println("Failed to enable Mac Fullscreen: " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maze");
        getContentPane().setLayout(new java.awt.CardLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("null".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainWindow().setVisible(true);
                } catch (FileNotFoundException e) {
                }

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

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
     * @return the setting
     */
    public OptionPanel getSetting() {
        return setting;
    }

    /**
     * @param setting the setting to set
     */
    public void setSetting(OptionPanel setting) {
        this.setting = setting;
    }

    /**
     * @return the menu
     */
    public MenuPanel getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(MenuPanel menu) {
        this.menu = menu;
    }

    /**
     * @return the game
     */
    public GamePanel getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(GamePanel game) {
        this.game = game;
    }

    /**
     * @return the option
     */
    public OptionPanel getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(OptionPanel option) {
        this.option = option;
    }

    /**
     * @return the win
     */
    public WinPanel getWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(WinPanel win) {
        this.win = win;
    }

    /**
     * @return the button
     */
    public SoundEffect getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(SoundEffect button) {
        this.button = button;
    }

    /**
     * @return the music
     */
    public SoundEffect getMusic() {
        return music;
    }

    /**
     * @param music the music to set
     */
    public void setMusic(SoundEffect music) {
        this.music = music;
    }
}
