/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Yasen
 */
public class FileLoader {

    String os = System.getProperty("os.name");
    File highScoreFile;

    public FileLoader() {
        highScoreFile = getHighScoreFile();
        System.out.println(os);
    }
    
    public File getImageFile(String name) {

        File image = null;

        if (os.equals("Windows 7")) {
            image = new File("content\\images\\"+name+".png");
        } else if (os.equals("Mac OS X")) {
            image = new File("content/images/"+name+".png");
        }
        return image;
    }

    public File getSoundEffect(String name) {

        File effect = null;

        if (os.equals("Windows 7")) {
            effect = new File("content\\sounds\\sfx_" + name + ".wav");
        } else if (os.equals("Mac OS X")) {
            effect = new File("content/sounds/sfx_" + name + ".wav");
        }
        return effect;
    }
    
    public File getSettings() {

        File effect = null;

        if (os.equals("Windows 7")) {
            effect = new File("content\\files\\settings.txt");
        } else if (os.equals("Mac OS X")) {
            effect = new File("content/files/settings.txt");
        }
        return effect;
    }

    public void newLevel(String name) {
        try {
            File level = null;

            if (os.equals("Windows 7")) {
                level = new File("content\\files\\saves\\" + name + ".txt");
            } else if (os.equals("Mac OS X")) {
                level = new File("content/files/saves/" + name + ".txt");
            }
            level.createNewFile();
        } catch (IOException e) {
        }
    }

    public File getLevel(String name) {
        File file = null;

        if (os.equals("Windows 7")) {
            file = new File("content\\files\\saves\\" + name + ".txt");
        } else if (os.equals("Mac OS X")) {
            file = new File("content/files/saves/" + name + ".txt");
        }
        System.out.println(file.toURI().toASCIIString());
        return file;
    }

    public File getLevelHeader() {
        File file = null;

        if (os.equals("Windows 7")) {
            file = new File("content\\files\\saves\\levelHeader.txt");
        } else if (os.equals("Mac OS X")) {
            file = new File("content/files/saves/levelHeader.txt");
        }
        System.out.println(file.toURI().toASCIIString());

        return file;
    }

    public File getHighScoreFile() {
        File file = null;

        if (os.equals("Windows 7")) {
            file = new File("content\\files\\highscores.txt");
        } else if (os.equals("Mac OS X") || os.equals("Linux")) {
            file = new File("content/files/highscores.txt");
        }

        System.out.println(file.toURI().toASCIIString());

        return file;
    }
}
