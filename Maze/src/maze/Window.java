
package maze;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;


/**
 *
 * @author Yasen
 */
public class Window {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                createFrame();
            }
        });
    }
    
    
     private static void createFrame() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Maze Yay!");
        
        //Gets the size of the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
       //Makes a frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(640,640);
        f.add(new MainPanel());
        f.pack();
        f.setVisible(true);
        
        //Centers location of new frame to 1/2 the screensize - 1/2 panel size
        f.setLocation((dimension.width/2)-(f.getWidth()/2), (dimension.height/2)-(f.getHeight()/2));
        
       
        
    }
}
