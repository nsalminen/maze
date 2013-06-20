package Window;

import Utilities.MazeKeyListener;
import Utilities.SoundEffect;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

/**
 *  This class contains controlls to create and use other panels
 * @author Nels
 */
public class MenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     *
     */
    MainWindow parent;
    SoundEffect music;
    private boolean playing;
        
    public MenuPanel(MainWindow p)  {
        initComponents();
        MazeKeyListener listener = new MazeKeyListener(this);
        this.addKeyListener(listener);
        this.setFocusable(true);
                
        activeGame(false);
        parent = p;   
        
    }
    
    public void activeGame(boolean active){
         continueGame.setVisible(active);
         saveGame.setVisible(active);
         playing = active;
    }
    
     public void updateGame(KeyEvent e) {
        keyInput(e.getKeyCode());
    }
    
    public void keyInput(int key) {
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                parent.unPauseGame();
                break;
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
        java.awt.GridBagConstraints gridBagConstraints;

        menuPanel = new javax.swing.JPanel();
        continueGame = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        loadGameButton = new javax.swing.JButton();
        saveGame = new javax.swing.JButton();
        highScoreButton = new javax.swing.JButton();
        optionsButtons = new javax.swing.JButton();
        exitButton2 = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        mainMenuBackground = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        menuPanel.setMinimumSize(new java.awt.Dimension(666, 513));
        menuPanel.setOpaque(false);
        menuPanel.setPreferredSize(new java.awt.Dimension(666, 513));

        continueGame.setText("Continue");
        continueGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueGameActionPerformed(evt);
            }
        });

        startButton.setText("New Game");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        loadGameButton.setText("Load Game");
        loadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGameButtonActionPerformed(evt);
            }
        });

        saveGame.setText("Save Game");
        saveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGameActionPerformed(evt);
            }
        });

        highScoreButton.setText("High Scores");
        highScoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highScoreButtonActionPerformed(evt);
            }
        });

        optionsButtons.setText("Settings");
        optionsButtons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButtonsActionPerformed(evt);
            }
        });

        exitButton2.setText("Exit");
        exitButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton2ActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuLogo.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout menuPanelLayout = new org.jdesktop.layout.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(menuPanelLayout.createSequentialGroup()
                .addContainerGap(279, Short.MAX_VALUE)
                .add(menuPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(logo)
                    .add(continueGame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(startButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(loadGameButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(saveGame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(highScoreButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(optionsButtons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(exitButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 299, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(268, 268, 268))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(menuPanelLayout.createSequentialGroup()
                .add(71, 71, 71)
                .add(logo)
                .add(56, 56, 56)
                .add(continueGame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(startButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(loadGameButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(saveGame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(highScoreButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(optionsButtons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(exitButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 100;
        add(menuPanel, gridBagConstraints);

        mainMenuBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuBackground.png"))); // NOI18N
        mainMenuBackground.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(mainMenuBackground, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void optionsButtonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButtonsActionPerformed

        parent.showOptions();
        parent.getButton().play();
    }//GEN-LAST:event_optionsButtonsActionPerformed

    private void highScoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highScoreButtonActionPerformed
        parent.showHighScores();
        
        parent.getButton().play();
    }//GEN-LAST:event_highScoreButtonActionPerformed

    private void exitButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton2ActionPerformed
      
        parent.getButton().play();
        System.exit(0);
      
    }//GEN-LAST:event_exitButton2ActionPerformed

    private void saveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGameActionPerformed
        parent.saveGame();
        parent.getButton().play();
    }//GEN-LAST:event_saveGameActionPerformed

    private void loadGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameButtonActionPerformed
        parent.loadGame();
        
        parent.getButton().play();
    }//GEN-LAST:event_loadGameButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        parent.startGame();
        parent.getButton().play();
    }//GEN-LAST:event_startButtonActionPerformed

    private void continueGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueGameActionPerformed
        parent.unPauseGame();
        parent.getButton().play();
    }//GEN-LAST:event_continueGameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continueGame;
    private javax.swing.JButton exitButton2;
    private javax.swing.JButton highScoreButton;
    private javax.swing.JButton loadGameButton;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel mainMenuBackground;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton optionsButtons;
    private javax.swing.JButton saveGame;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
