package Window;

import Utilities.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Yasen and Nels
 */
public class LoadPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoadPanel
     */
    MainWindow parent;
    public String os;
    private File def;

    public LoadPanel(MainWindow p) {
        initComponents();
        parent = p;
        os = System.getProperty("os.name");
        initComponents();
        this.setSize(parent.getSize());
        try {
            if (os.equals("Windows 7")) {
                def = new File("content\\files\\saves");
            } else if (os.equals("Mac OS X")) {
                def = new File("content/files/saves");
            }

        } catch (Exception e) {
        }
        fileChooser.setCurrentDirectory(def);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();

        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        if (evt.getActionCommand().equals("ApproveSelection")) {
            parent.menu.activeGame(true);
            FileReader levelReader = new FileReader();
            try {
                parent.loadNewGame(levelReader.readLevel(fileChooser.getSelectedFile()));

            } catch (FileNotFoundException e) {
            }
        } else if (evt.getActionCommand().equals("CancelSelection")) {

            parent.goToMenu();
        }
    }//GEN-LAST:event_fileChooserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration//GEN-END:variables
}
