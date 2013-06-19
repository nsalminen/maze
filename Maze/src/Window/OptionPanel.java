package Window;

/**
 *
 * @author Nels
 */
public class OptionPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     */
    MainWindow parent;
    public boolean activeGame = false;

    public OptionPanel(MainWindow p) {
        initComponents();

        this.setFocusable(true);
        parent = p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        volumeSlider = new javax.swing.JSlider();
        soundToggleButton = new javax.swing.JToggleButton();
        mainMenuBackground = new javax.swing.JLabel();

        setLayout(null);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton);
        backButton.setBounds(400, 490, 75, 29);

        volumeSlider.setMajorTickSpacing(100);
        volumeSlider.setMaximum(60);
        volumeSlider.setMinimum(-800);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setToolTipText("");
        volumeSlider.setValue(0);
        volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeSliderStateChanged(evt);
            }
        });
        add(volumeSlider);
        volumeSlider.setBounds(280, 340, 380, 38);

        soundToggleButton.setSelected(true);
        soundToggleButton.setText("ON");
        soundToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundToggleButtonActionPerformed(evt);
            }
        });
        add(soundToggleButton);
        soundToggleButton.setBounds(290, 180, 140, 29);

        mainMenuBackground.setIcon(new javax.swing.ImageIcon("/Users/Nels/Dropbox/maze/Maze/content/images/menuBackground.png")); // NOI18N
        mainMenuBackground.setText("jLabel1");
        add(mainMenuBackground);
        mainMenuBackground.setBounds(0, 0, 945, 751);
    }// </editor-fold>//GEN-END:initComponents

    private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeSliderStateChanged
        float value = volumeSlider.getValue() / 10;
        parent.game.setVolume(value);
    }//GEN-LAST:event_volumeSliderStateChanged

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        parent.goToMenu();
    }//GEN-LAST:event_backButtonActionPerformed

    private void soundToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundToggleButtonActionPerformed
        System.out.println(soundToggleButton.isSelected());
        if (soundToggleButton.isSelected()) {
            soundToggleButton.setText("ON");
            parent.game.volumeOn();
        } else {
            soundToggleButton.setText("OFF");
            parent.game.volumeOff();
            System.out.println("False");
        }
    }//GEN-LAST:event_soundToggleButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel mainMenuBackground;
    private javax.swing.JToggleButton soundToggleButton;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables
}