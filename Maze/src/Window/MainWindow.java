package Window;

import Utilities.FileLoader;
import Utilities.FileReader;
import Utilities.Level;
import Utilities.SoundEffect;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import javax.swing.JFrame;
//import com.apple.eawt.FullScreenUtilities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nels
 */
public class MainWindow extends JFrame {
    

    private FileReader reader = new FileReader();
    public FileLoader loader = new FileLoader();
    private static final long serialVersionUID = 1L;
    static MainWindow mazeWindow = new MainWindow();
    public OptionPanel setting;
    public MenuPanel menu = new MenuPanel(this);
    public GamePanel game;
    public OptionPanel option;
    WinPanel win;
    private Dimension windowDimension;
    private static GraphicsDevice vc;
    private boolean fullscreen = false;
    SoundEffect button;

    public MainWindow() {
        initComponents();
        if (System.getProperty("os.name").equals("Mac OS X")) {
            enableOSXFullscreen(this);
        }
        windowDimension = new Dimension(1220, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(windowDimension);
        setContentPane(menu);
        setLocationRelativeTo(null);
        button = new SoundEffect(loader.getSoundEffect("menu"));
    }

    public void saveGame() {
        SavePanel savepanel = new SavePanel(this);
        setContentPane(savepanel);
        savepanel.setVisible(true);
        savepanel.setFocusable(true);
        savepanel.requestFocus();
        savepanel.setSize(this.getSize());
    }

    public void showSettings() {
        try{
        setting = new OptionPanel(this);
        setContentPane(setting);
        setting.setVisible(true);
        setting.setFocusable(true);
        setting.requestFocus();
        setting.setSize(this.getSize());
        }catch(Exception e){
        }
        
    }

    public void loadGame() {

        LoadPanel loadpanel = new LoadPanel(this);
        setContentPane(loadpanel);
        loadpanel.setVisible(true);
        loadpanel.setFocusable(true);
        loadpanel.requestFocus();
        loadpanel.setSize(this.getSize());
    }

    public void goToMenu() {
        setContentPane(menu);
        menu.setFocusable(true);
        menu.requestFocus();
        menu.setSize(this.getSize());
        button.play();
    }

    public void unPauseGame() {
        setContentPane(game);
        game.setFocusable(true);
        game.requestFocus();
        game.setSize(this.getSize());
    }

    public void showHighScores() {
        HighScorePanel scores = new HighScorePanel(this);
        setContentPane(scores);
        scores.setSize(this.getSize());
    }

    public void loadNewGame(Level level) {
       
        game = new GamePanel(level, this);
        setContentPane(game);
        game.setFocusable(true);
        game.requestFocus();
        game.repaint();
        game.setSize(this.getSize());
    }

    public void startGame() {
        game = new GamePanel(this);
        setContentPane(game);
        game.setFocusable(true);
        game.requestFocus();
        game.repaint();
        game.setSize(this.getSize());
    }

    public void gameOver() {
        game.setVisible(false);
        win = new WinPanel(this);
        setContentPane(win);
        win.setFocusable(true);
        win.requestFocus();
        win.repaint();

        System.out.println("GAME");
    }

    /**
     * This method sets the current {@link javax.swing.JFrame} to full screen
     * mode It determines whether full screen is supported according to
     * {@link http://docs.oracle.com/javase/tutorial/extra/fullscreen/}
     */
    public void toggleFullscreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = ge.getDefaultScreenDevice();
        if (vc.isFullScreenSupported()) {
            System.out.println("Supported");
            if (!fullscreen) {
                vc.setFullScreenWindow(mazeWindow);
                game.setSize(this.getSize());
                fullscreen = true;
                setVisible(false);
                setVisible(true);
            } else if (fullscreen) {
                vc.setFullScreenWindow(null);
                fullscreen = false;
            }
        } else {
            System.out.println("Unsupported");
            if (!fullscreen) {
                mazeWindow.setExtendedState(MainWindow.MAXIMIZED_BOTH);
                mazeWindow.setUndecorated(true);
                mazeWindow.setResizable(false);
            } else {
                mazeWindow.setExtendedState(MainWindow.NORMAL);
                mazeWindow.setUndecorated(false);
                mazeWindow.setResizable(true);
            }
        }
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
                new MainWindow().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
