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

        
public class FileWriter {
    
    public PrintWriter writer;
    public Scanner reader;
    public File hsfile = new File("content\\files\\highscores.txt");
    
    
    public Map<String, Integer> scores = new TreeMap<>();
  
    public FileWriter()
    {   
        try{
            reader = new Scanner(hsfile);
        }
        catch(Exception e){}
    }
    
       public void writeScores(ArrayList<String> scores){
        for(String line : scores){
            writer.println(line);
        }
    }
}
   
