package Sprites;

import Game.Node;
import java.awt.Color;
import java.awt.Graphics;
import Window.GamePanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yasen
 */
public class Wall extends Sprite {

    public boolean portal;
    private BufferedImage image = null;

    public Wall(Node n, GamePanel pan) {
        try {
            panel = pan;
            parent = n;
            position = parent.getPosition();
            image = ImageIO.read(panel.loader.getImageFile("Wall").toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paintSelf(int x, int y, Graphics g) {
        g.setColor(Color.red);
        g.drawImage(image, x * panel.blockSize, y * panel.blockSize, panel.blockSize, panel.blockSize, null);
    }
}
