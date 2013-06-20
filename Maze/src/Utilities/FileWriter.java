/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


    /**
     * This class is used to write data onto text-files for later use. 
     *     
     * @return sting A String array object containing the settings data
     * @author Yasen
     */
        
public class FileWriter {
    
    private PrintWriter writer;
    private FileLoader loader;

    public Map<String, Integer> scores = new TreeMap<>();
  

    
    public FileWriter()
    {   
        loader = new FileLoader();
        try{
            writer = new PrintWriter(loader.getHighScoreFile());            
        }
        catch(Exception e){}
    }
    
    /**
     * Writes settings-data taken from a String
     * 
     * @param settings, a string containgin settings data formatted as follows:
     * "masteVolume:VALUE\nmusicVolume:VALUE"  
     */    
    public void writeSettings(String settings){   
        try{
            PrintWriter levelWriter = new PrintWriter(loader.getSettings());
            levelWriter.print(settings);
            levelWriter.close();
         }
        catch(FileNotFoundException e){}
    }
    
    /**
     * (Over)writes savefile with a name taken from a given String and based
     * on a given Level object.
     * 
     * @param name, a string containing the desired filename:
     * @param level, a Level containing all the data required to (re)build a game
     * "masteVolume:VALUE\nmusicVolume:VALUE"  
     */
     public void writeLevel(String name, Level level){
        loader.newLevel(name);
        try{
            PrintWriter levelWriter = new PrintWriter(loader.getLevel(name));
            levelWriter.print(level.toString());
            levelWriter.close();
         }
        catch(FileNotFoundException e){}
       }
     
    /**
     * Overwrites the hishScores file with a new highscores taken from an ArrayList
     * on a given Level object.
     * 
     * @param score, Anstring containing the desired filename. Each String is formatted as follows:
     * "PLAYERNAME:SCORE\n"     * 
     */
    
    public void writeScores(ArrayList<String> scores){
        String out ="";
        for(String line : scores){
            out = ( out + line+"\n");
        }
        writer.print(out);
        writer.close();
    }
}
   
