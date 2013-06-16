/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;

/**
 *
 * @author Yasen
 */
public class FileLoader {
    
    String os = System.getProperty("os.name");
    File highScoreFile;
    
    public FileLoader(){       
           highScoreFile = getHighScoreFile();
           System.out.println(os);
    }
    
    public File getHighScoreFile(){
        File file = null;
        
        if(os.equals("Windows 7")){
            file = new File("content\\files\\highscores.txt");    
        }
        
        else if(os.equals("Mac OS X")){
            file = new File("/content/files/highscores.txt");
        }
        
        else if(os.equals("Mac OS X")){
            file = new File("/content/files/highscores.txt");
        }
        
        System.out.println(file.toURI().toASCIIString());
        
        return file;
        
    
    
    }
}
