package Window;

import Utilities.FileReader;
import Utilities.FileWriter;
import java.util.ArrayList;

/**
 *  A panel that appears then the player has completed a level.
 * 
 * If the player has achieved a highscore he is promted to insert their name
 * @author Nels
 */
public class WinPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     */
    FileReader scoreReader;
    FileWriter scoreWriter;
    private MainWindow parent;
    public int score;
    String[] names = new String[5];
    int[] scores = new int[5];
    int place;
    ArrayList<String> data;

    public WinPanel(MainWindow p) {
        initComponents();
        parent = p;
        score = p.getGame().player.stepsTaken;
        scoreReader = new FileReader();
        if (!isHighScore()) {

            nameField.setVisible(false);
            subtitle.setText("Try again?");
        }
        this.setSize(parent.getSize());
    }

    /**
     * This method fills the labels with the sorted highscorers and their respective scores
     */
    void checkScore() {
        data = scoreReader.printMap();
        for (int i = 0; i < scores.length; i++) {
            names[i] = data.get(i).split(":")[0];
            scores[i] = Integer.parseInt(data.get(i).split(":")[1]);
        }
    }

    /**
     * This mehod inserts the current player into a certain spot on the high-score board 
     * 
     * @param p The current players spot on the leaderboards
     */    
    private void insertPlayer(int p) {
        data.set(p, nameField.getText() + ":" + score);
    }
    
    
    /**
     * This mehod checks if the current player has achieved a highscore or not and places them in the ranking 
     * @return boolean returns true if current score is in the leaderboards
     */ 
    private boolean isHighScore() {
        checkScore();
        boolean isHighScore = false;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > score && !isHighScore) {
                isHighScore = true;
                place = i;
            }
        }
        System.out.println(isHighScore + " " + place + " " + score);
        return isHighScore;
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
        congratulationsText = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(700, 380));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 380));

        congratulationsText.setFont(new java.awt.Font("Myriad Pro", 0, 24)); // NOI18N
        congratulationsText.setForeground(new java.awt.Color(51, 51, 51));
        congratulationsText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        congratulationsText.setText("Congratulations!");
        congratulationsText.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        subtitle.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        subtitle.setForeground(new java.awt.Color(51, 51, 51));
        subtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitle.setText("You got a new high score! Please enter your name below.");
        subtitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        nameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameField.setToolTipText("Please fill in your name here");
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        backButton.setText("Confirm and back to menu");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuLogo.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(backButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 549, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(nameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 549, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(subtitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 549, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(congratulationsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 549, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(163, 163, 163))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(0, 63, Short.MAX_VALUE)
                .add(jLabel2)
                .add(18, 18, 18)
                .add(congratulationsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(subtitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(nameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(backButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(33, 33, 33))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 100;
        add(jPanel1, gridBagConstraints);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuBackground.png"))); // NOI18N
        background.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(background, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        scoreWriter = new FileWriter();
        insertPlayer(place);
        scoreWriter.writeScores(data);
        try{
        MainWindow main = new MainWindow();
        main.setVisible(true);
        main.requestFocus();
        }catch(Exception e){}
        parent.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
    }//GEN-LAST:event_nameFieldActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel background;
    private javax.swing.JLabel congratulationsText;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel subtitle;
    // End of variables declaration//GEN-END:variables
}
