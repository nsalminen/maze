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
 *
 * @author Yasen
 */

        
public class FileReader {
    
    public PrintWriter scoreWriter;
    public Scanner scoreReader;
    
    public PrintWriter levelWriter;
    public Scanner levelReader;
    
    public FileLoader loader;
    public Map<String, Integer> scores = new TreeMap<>();
  
    public FileReader()
    {   
        loader = new FileLoader();
        try{
            scoreReader = new Scanner(loader.getHighScoreFile());
        }
        catch(Exception e){}
    }
    
    public String[] readSettings()throws FileNotFoundException{
        
        
        levelReader = new Scanner(loader.getSettings());
        String[] string = levelReader.nextLine().split(";");
        
        return string;
    }
    
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
        
        //System.out.println("GOT SOMETHING");
        level.print();        
        return level;
       
    }
    
    public String[] getLevelHeader(){
        
        String maxSize = levelReader.nextLine();
        String filledSize= levelReader.nextLine();
        //System.out.println("Max Size: "+maxSize);
        //.out.println("Filled Size: "+filledSize);
        
        String line = "";
        
        while(levelReader.hasNext()){
            line = line+levelReader.nextLine();
        }
        
        return line.split("-");
        
    }
    
    public Map<String,Integer> getHighScores(){
        String line;
        String name;
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
    public ArrayList<String> printMap(){
               
                Map<String,Integer> map =getHighScores();
                ArrayList<String> scoreList = new ArrayList<String>();
                for (Map.Entry entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
                                   + " Value : " + entry.getValue());
                        scoreList.add(""+entry.getKey()+":"+entry.getValue());
		}
                if(scoreList.size()<5){
                    //.out.println("small list");
                    System.out.println(scoreList.size());
                    int remainder = 5-scoreList.size();
                    
                    for(int i = 0 ; i < remainder;i++){
                        scoreList.add(i,"****:9999");
                        //.out.println("added empty");
                    }
                }
                return scoreList;
	}
}
   
