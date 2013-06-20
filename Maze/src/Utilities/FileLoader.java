/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.IOException;
/**
     * This class is responsible for the formatting  of URL's and returning
     * fileobjects
     * 
     * @author Yasen D. and Nels S.
     */
public class FileLoader {

    String os = System.getProperty("os.name");
    File highScoreFile;

    public FileLoader() {
        highScoreFile = getHighScoreFile();
        System.out.println(os);
    }
    
    /**
     * Takes the name of an image file as a string and formats the corresponding URL
     * 
     * @param name The name of that is looked up
     * @return image A File object for the corresponding file name
     */
    public File getImageFile(String name) {

        File image = null;

        if (os.equals("Windows 7")) {
            image = new File("content\\images\\"+name+".png");
        } else if (os.equals("Mac OS X")) {
            image = new File("content/images/"+name+".png");
        }
        return image;
    }
    
    /**
     * Takes the name of a sound file as a string and formats the corresponding URL
     * 
     * @param name The name of that is looked up
     * @return effect A File object for the corresponding file name
     */
    public File getSoundEffect(String name) {

        File effect = null;

        if (os.equals("Windows 7")) {
            effect = new File("content\\sounds\\sfx_" + name + ".wav");
        } else if (os.equals("Mac OS X")) {
            effect = new File("content/sounds/sfx_" + name + ".wav");
        }
        return effect;
    }


    
     /**
     * Takes the name for a save file as a string.
     * The method creates a new file with that name if said file does not aleadu exist
     * 
     * @param name A name for the save file
     */

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
    
     /**
     * Takes the name of a text file as a string and formats the corresponding URL
     * 
     * @param name The name of that is being looked up
     * @return file a File object for the corresponding file name
     */
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
    
    
     /**
     * Takes the name of a text file as a string and formats the corresponding URL
     * 
     * @return a File object for the corresponding file name
     */
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
     /**
     * Returns a File object for the highscore file for this application
     * 
     * @return highScore effect a File object for the corresponding file name
     */
    public File getSettings() {

        File highScore = null;

        if (os.equals("Windows 7")) {
            highScore = new File("content\\files\\settings.txt");
        } else if (os.equals("Mac OS X")) {
            highScore = new File("content/files/settings.txt");
        }
        return highScore;
    }
}
