/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

/**
 *This class is used to take in File obects and translate them into processable data.
 *
 * @author Yasen and Nels
 */

        
public class FileReader {
    
    public PrintWriter scoreWriter;
    public Scanner scoreReader;
    
    public PrintWriter levelWriter;
    public Scanner levelReader;
    
    public FileLoader loader;
    
  
    public FileReader()
    {   
        loader = new FileLoader();
        try{
            scoreReader = new Scanner(loader.getHighScoreFile());
        }
        catch(Exception e){}
    }
     
    /**
     * Loads the "settings.txt" file through a FileLoader and translates each line
     * in a string[]. 
     *
     * string[0] holds the value for the master volume
     * string[1] holds the value for the music volume
     * string[2] holds the value for the global mute
     * string[3] holds the value for fullscreen
     * 
     * @return sting A String array object containing the settings data
     */
    public String[] readSettings()throws FileNotFoundException{        
        levelReader = new Scanner(loader.getSettings());
        String[] string = new String[4];
        string[0] = levelReader.nextLine();
        string[1] = levelReader.nextLine();
        string[2] = levelReader.nextLine();
        string[3] = levelReader.nextLine();
        return string;
    }
     /**
      * Reads a given file and formats it into a Level object. A Level object
      * contains all the data required to build a maze.
     *      * 
     * @param File a file object for the desired savefile.
     * @return level A Level object containing all the data needed to (re)build a game
     */
    public Level readLevel(File f)throws FileNotFoundException{
        int score = 0;
        boolean portalgun;
        boolean showPath;
        Scanner lvlReader = new Scanner(f);
        String line ="";
        Stack<Position> positions = new Stack<>();

        //Get the score
        lvlReader.hasNextLine();
        line = lvlReader.nextLine().split("=")[1];
        score = Integer.parseInt(line.trim());
        
        
        //Get portalGun
        line = lvlReader.nextLine().split("=")[1];       
        if(line.contains("true")){
            portalgun = true;
        }
        else{
            portalgun = false;
        }
        //Get showPath
        line = lvlReader.nextLine().split("=")[1];
        if(line.contains("true")){
            showPath = true;
        }
        else{
            showPath = false;
        }
        //Get stack
        line = lvlReader.nextLine();
        String[] stackString = new String[line.length()];
        stackString = line.split("-");
        String[] posString = new String[3];
        for(String pos : stackString){
            posString = pos.split(",");
            positions.push(new Position(Integer.parseInt(posString[0]),
                                       Integer.parseInt(posString[1]),
                                       Integer.parseInt(posString[2])));   
        }
        
        //Getting Layout
        ArrayList<String> vArray = new ArrayList<String>();        
        while(lvlReader.hasNextLine()){
            vArray.add(lvlReader.nextLine());            
        }
        int[][] values = new int[vArray.size()][vArray.get(0).length()];        
        for(int y = 0; y < vArray.size(); y++ ){
            for(int x = 0; x < vArray.get(0).length(); x++ ){
                values[y][x] = Integer.parseInt(""+vArray.get(y).charAt(x));
            }
        }
         
        Level level;
        
        level = new Level(values, score, portalgun, positions, showPath);      
        return level;
       
    }
    
    /**
     * Returns a sorted TreeMap containing the highscores from the high-score file;
     * Reads the file  containing the high-scores and formats it into a TreeMap.
     * The keys for this treemap are various player's names and the values contain their scores
     * 
     * @return Map A sorted TreeMap where the values of the players are sorted ascending by score
     */
     
    public Map<String,Integer> getHighScores(){
        String line;
        String name;
        
        Map<String, Integer> scores = new TreeMap<>();
        
        int score;
            for(int i = 0; i <5; i++){
                if(scoreReader.hasNext()){
                    line = scoreReader.nextLine();
                    name =  line.split(":")[0];
                    score = Integer.parseInt(line.split(":")[1]);
                    scores.put(name, score);
                }
            }
        return sortByComparator(scores);
    }
    
    /**
     * Returns a sorted TreeMap, where the keys are sorted ascending by their values.
     * 
     * @param unsortMap a raw unsorted TreeMap object
     * @return Map A sorted TreeMap where the keys are sorted ascending by value
     */
    private static Map sortByComparator(Map unsortMap) {
 
		List list = new LinkedList(unsortMap.entrySet());
 		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
    
    /**
     * Returns an ArrayList<Sting> containing sorted high score values in order separated by ":"
     * If there are less than 5 noted high scores, this method fills the empty slots with "****:9999"
     * 
     * @return scoreList An ArrayList<Sting> formatted: "PLAYERNAME:SCORE"
     */
    public ArrayList<String> printMap(){               
                Map<String,Integer> map =getHighScores();
                ArrayList<String> scoreList = new ArrayList<String>();
                for (Map.Entry entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
                                   + " Value : " + entry.getValue());
                        scoreList.add(""+entry.getKey()+":"+entry.getValue());
		}
                
                //Fills empty slots
                if(scoreList.size()<5){
                    int remainder = 5-scoreList.size();                   
                    for(int i = 0 ; i < remainder;i++){
                        scoreList.add(i,"****:9999");
                    }
                }
                return scoreList;
	}
}
   
