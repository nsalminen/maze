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
    public FileLoader loader;
    
    void printData(ArrayList<String> data){
        
        for(int i = 0; i < data.size(); i++){ 
                System.out.println(""+data.get(i));        
            }
    }
    
    public Map<String, Integer> scores = new TreeMap<>();
  
    public FileWriter()
    {   
        loader = new FileLoader();
        try{
            writer = new PrintWriter(loader.getHighScoreFile());
        }
        catch(Exception e){}
    }
    
    public void writeScores(ArrayList<String> scores){
        printData(scores);
        String out ="";
        for(String line : scores){
            out = ( out + line+"\n");
            //System.out.println("reading: "+line);
            System.out.println(out);
            
        }
        writer.print(out);
        writer.close();
        System.out.println("PRINTED");
       }
}
   