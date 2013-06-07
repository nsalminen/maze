/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import Game.User;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Yasen
 */

        
public class FileReaderWriter {
    
    public FileOutputStream writer;
    public Scanner console;
    public File hsfile = new File("content\\highscores.txt");
    public Pattern hsp = Pattern.compile("XXXX\n");    
    final static Charset ENCODING = StandardCharsets.UTF_8;
    public ArrayList <String> nameList = new ArrayList<String>();
    public ArrayList <Integer> scoreList = new ArrayList<Integer>();
    public ArrayList <String> outList = new ArrayList<String>();
    
    public FileReaderWriter()
    {
        try{
      
        console = new Scanner(hsfile);
        
        
        
        }catch(Exception e){}
        fillLists();
        
    }
    
    public void compare(User u){
        boolean checked = false;
        for(int i = 0; i < scoreList.size();i++){
            if(scoreList.get(i) < u.score && !checked){
                scoreList.set(i, u.score);
                nameList.set(i, u.name);  
                checked = true;
            }
            
            outList.add(i, nameList.get(i)+":"+scoreList.get(i));
            
            
        }
        for(int i = 0; i < outList.size();i++){
            System.out.println(outList.get(i));
        }
         try{
      
            writeSmallTextFile(outList,"content\\highscores.txt");
         }
        catch(Exception e){}
    }

    
    void writeSmallTextFile(ArrayList<String> aLines, String aFileName) throws IOException {
        
        
        try{
      
        console = new Scanner(hsfile);
        Path path = Paths.get("content\\highscores.txt");
        
        for(int i = 0; i < scoreList.size();i++){
            Files.write(path, outList, ENCODING);   
            
        }
        
        Files.write(path, outList, ENCODING);
        
        
        }catch(Exception e){}
        fillLists();

  }
    
//    public void writeList(OutputStream out){
//        try{
//        writer.;
//        for(int i = 0; i < 5 ; i++){
//            writer.write(nameList.get(i)+":"+scoreList.get(i)+"\n");            
//        }
//        }catch(Exception e){}
//    }
    
    
    public void fillLists(){
       
        while(console.hasNextLine()){
            String[] strAr = new String[2];
            String score = console.nextLine();
            strAr = score.split(":");
            nameList.add(strAr[0]);
            scoreList.add(Integer.parseInt(strAr[1]));
            }
    
    }
    
   
    
    
}
