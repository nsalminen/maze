package Window;

import Utilities.FileReader;
import Utilities.FileWriter;
import java.util.ArrayList;

/**
 *
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
        score = p.game.player.stepsTaken;
        scoreReader = new FileReader();        
        if(!isHighScore()){
            
            nameField.setVisible(false);
            subtitle.setText("Try again?");
        }
    }
    void checkScore(){
        data = scoreReader.printMap();  
        for(int i = 0; i < scores.length; i++){
            names[i] = data.get(i).split(":")[0];
            scores[i] = Integer.parseInt(data.get(i).split(":")[1]);
        }
    }
    
    void printData(){
        
        for(int i = 0; i < data.size(); i++){ 
                System.out.println(""+data.get(i));        
            }
    }
    
    private void insertPlayer(int p ){
        data.set(p, nameField.getText()+":"+score);
    }
    private boolean isHighScore(){        
        checkScore();
        boolean isHighScore = false;
        for(int i = 0; i < scores.length; i++){
            if(scores[i] > score && !isHighScore){
                isHighScore = true;
                place = i;
            }
        }
        System.out.println(isHighScore +" "+place+" "+score);
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
        congratulation = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridLayout(4, 0, 0, 10));

        congratulation.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        congratulation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        congratulation.setText("Congratulation!");
        congratulation.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(congratulation);

        subtitle.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        subtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitle.setText("You won! Please Fill in your name");
        subtitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(subtitle);

        nameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(nameField);

        backButton.setText("Back to menu");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        jPanel1.add(backButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 100;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        scoreWriter= new FileWriter();
        insertPlayer(place);
        //printData();
        scoreWriter.writeScores(data);
        
        MainWindow main = new MainWindow();
        main.setVisible(true);
        main.requestFocus();
        parent.dispose();
        //MainWindow.mazeWindow.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel congratulation;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel subtitle;
    // End of variables declaration//GEN-END:variables
}
