package Window;

import Utilities.FileReader;
import Utilities.FileWriter;
import java.io.FileNotFoundException;

/**
 *This panel controlls the volume for all the active SoundEffect objects in the game
 * and saves its settings to the settings.txt file
 * @author Nels
 */
public class OptionPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     */
    MainWindow parent;
    public boolean activeGame = false;
    public FileReader reader = new FileReader();
    public FileWriter writer = new FileWriter();
    public int volume;
    public int music;
    private boolean mute;
   
    public OptionPanel(MainWindow p) throws FileNotFoundException {
        initComponents();
        
        String[] settings = reader.readSettings();
        System.out.println(settings[0]+settings[1]+settings[1]);

        int vol = Integer.parseInt(settings[0].split(":")[1]);
        int mus = Integer.parseInt(settings[1].split(":")[1]);
        
        if( settings[2].split(":")[1].contains("true")){
            mute = true;
            muteButton.setText("ON");
            muteButton.setSelected(true);
        }
        else{
            mute = false;
            muteButton.setText("OFF");
             muteButton.setSelected(false);
        }
        
        //mute = muteButton.isSelected();
       
        
        System.out.println("fasdfasdfasdfasdf");
        volume = vol;
        this.setFocusable(true);
        parent = p;
        musicSlider.setMaximum(60);
        musicSlider.setMinimum(-800);
        musicSlider.setValue(mus);
        volumeSlider.setMaximum(60);
        volumeSlider.setMinimum(-800);
        volumeSlider.setValue(vol);
        this.setSize(parent.getSize());
;
        
    }

    /**
     * Takes settings data and returns a string used to overwrite the settings file
     */
    public String buildSettings() {
        return "masterVolume:" + volume + "\n"
                + "musicVolume:" + music + "\n"
                + "mute:"+ muteButton.isSelected();
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

        jPanel1 = new javax.swing.JPanel();
        musicSlider = new javax.swing.JSlider();
        muteButton = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        volumeSlider = new javax.swing.JSlider();
        backButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        mainMenuBackground = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        musicSlider.setMajorTickSpacing(100);
        musicSlider.setMaximum(60);
        musicSlider.setMinimum(-800);
        musicSlider.setPaintTicks(true);
        musicSlider.setToolTipText("");
        musicSlider.setValue(0);
        musicSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                musicSliderStateChanged(evt);
            }
        });

        muteButton.setSelected(true);
        muteButton.setText("ON");
        muteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteButtonActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sound");

        jLabel2.setText("Music Volume");

        jLabel1.setText("Sound Volume");

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

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuLogo.png"))); // NOI18N
        jLabel4.setToolTipText("");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(volumeSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 380, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(muteButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(musicSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 380, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(backButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .add(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel4)
                .add(56, 56, 56)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(muteButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(42, 42, 42)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(musicSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(47, 47, 47)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(volumeSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(41, 41, 41)
                .add(backButton)
                .add(30, 30, 30))
        );

        add(jPanel1, new java.awt.GridBagConstraints());

        mainMenuBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuBackground.png"))); // NOI18N
        mainMenuBackground.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(mainMenuBackground, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeSliderStateChanged
        volume = volumeSlider.getValue();
        writer.writeSettings(buildSettings());
        System.out.println(volumeSlider.getValue());

    }//GEN-LAST:event_volumeSliderStateChanged

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (parent.getGame() != null) {
            parent.setVolume(volume);
            
        } else {
            System.out.println("No game!");
        }
        parent.setMusicVolume(music);
        
        parent.goToMenu();
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void muteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteButtonActionPerformed
             if( muteButton.isSelected()){
                    muteButton.setText("ON");
                    parent.getMusic().play();
                }   
             else{
                    muteButton.setText("OFF");
                    parent.getMusic().stop();
                }
            writer.writeSettings(buildSettings());
    }//GEN-LAST:event_muteButtonActionPerformed

    private void musicSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_musicSliderStateChanged
        music = musicSlider.getValue();
        writer.writeSettings(buildSettings());
        System.out.println(musicSlider.getValue());
    }//GEN-LAST:event_musicSliderStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel mainMenuBackground;
    private javax.swing.JSlider musicSlider;
    private javax.swing.JToggleButton muteButton;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the mute
     */
    public boolean isMute() {
        return mute;
    }

    /**
     * @param mute the mute to set
     */
    public void setMute(boolean mute) {
        this.mute = mute;
    }
}
