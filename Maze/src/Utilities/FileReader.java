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
import java.util.TreeMap;

/**
 *
 * @author Yasen
 */

        
public class FileReader {
    
    public PrintWriter writer;
    public Scanner reader;
    public File hsfile = new File("content\\files\\highscores.txt");
    
    
    public Map<String, Integer> scores = new TreeMap<>();
  
    public FileReader()
    {   
        try{
            reader = new Scanner(hsfile);
        }
        catch(Exception e){}
    }
    
    public Map<String,Integer> getHighScores(){
        String line;
        String name;
        int score;
            while(reader.hasNextLine()){
                line = reader.nextLine();
                name =  line.split(":")[0];
                score = Integer.parseInt(line.split(":")[1]);
                scores.put(name, score);
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
                    for(int i = 0 ; i < (5 -scoreList.size());i++){
                        scoreList.add("****:***");
                    }
                }
                return scoreList;
	}
}
   
