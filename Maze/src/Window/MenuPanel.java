package Window;

/**
 *
 * @author Nels
 */
public class MenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     */
    public MenuPanel() {
        initComponents();
        if(System.getProperty("os.name").equals("Mac OS X")){
            fullScreenButton.setVisible(false);
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

        fullScreenButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        fullScreenButton.setText("Fullscreen");
        fullScreenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullScreenButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 123.0;
        gridBagConstraints.weighty = 123.0;
        gridBagConstraints.insets = new java.awt.Insets(29, 19, 19, 19);
        add(fullScreenButton, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(2, 0, 0, 10));

        startButton.setText("Start Game");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startButton);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(exitButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 100;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        MainWindow.mazeWindow.setContentPane(MainWindow.mazeWindow.game);
        MainWindow.mazeWindow.setVisible(true);     
    }//GEN-LAST:event_startButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void fullScreenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullScreenButtonActionPerformed
        MainWindow.mazeWindow.toggleFullscreen();
    }//GEN-LAST:event_fullScreenButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton fullScreenButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
