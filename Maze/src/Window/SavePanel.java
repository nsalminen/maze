/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import Utilities.FileWriter;

/**
 * A panel where a player can submit a file name and save their current game
 * @author Yasen
 */
public class SavePanel extends javax.swing.JPanel {

    /**
     * Creates new form SavePanel
     */
    MainWindow parent;

    public SavePanel(MainWindow p) {
        initComponents();
        parent = p;
        this.setSize(parent.getSize());
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
        saveFileName = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        saveTitle = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setFocusable(false);
        jPanel1.setOpaque(false);

        saveFileName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saveFileName.setToolTipText("Enter a name");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        saveTitle.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
        saveTitle.setText("Please enter a name for your save file");

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuLogo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(logo)
                            .addComponent(saveTitle)
                            .addComponent(saveFileName)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(logo)
                .addGap(45, 45, 45)
                .addComponent(saveTitle)
                .addGap(31, 31, 31)
                .addComponent(saveFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(saveButton)
                    .addComponent(cancel))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        add(jPanel1, new java.awt.GridBagConstraints());

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/menuBackground.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(background, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        FileWriter levelWriter = new FileWriter();
        levelWriter.writeLevel(saveFileName.getText(), parent.game.getMaze().buildLevel());
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        parent.goToMenu();       // TODO add your handling code here:
    }//GEN-LAST:event_cancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton cancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField saveFileName;
    private javax.swing.JLabel saveTitle;
    // End of variables declaration//GEN-END:variables
}
