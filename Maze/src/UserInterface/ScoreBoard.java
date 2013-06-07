/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Sprites.Player;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Yasen
 */
public class ScoreBoard {
    
    private int x ;
    private int y ;
    private int blockSize;
    TreeMap<String, Integer> map;
    
    ArrayList <String> nameList;
    ArrayList <Integer> scoreList ;
    ArrayList <Integer> sortedScoreList = new ArrayList <Integer>() ;
    ArrayList <String> sortedNameList  = new ArrayList <String>();
    ArrayList <String> boardList  = new ArrayList <String>();
    

    public ScoreBoard(int xPos, int yPos, GamePanel p){
        
        nameList = p.frw.nameList;
        scoreList = p.frw.scoreList;
        blockSize = p.blockSize;
        x = xPos;
        y = yPos;
        
    }
    
    
    
    public String printLine(int i){
              String string = scoreList.get(i)+("  \t  "+nameList.get(i));
              System.out.println(string);
              return string;
    }
    
    public void drawBoard(Graphics g){
        
        g.setColor(Color.white);
        g.drawRect(x, y, blockSize, blockSize);
        
       g.drawLine(x-blockSize+20, y+blockSize+10, x+2*blockSize+30, y+blockSize+10); 
        
        for (int i = 0 ; i < 5; i++){
            
            g.drawString(""+scoreList.get(i), x+blockSize+20, y+blockSize*i+70);
            g.drawLine(x-blockSize+20, y+blockSize*i+85, x+2*blockSize+30, y+blockSize*i+85);
        }
        
        for (int i = 0 ; i < 5; i++){
            
            g.drawString(nameList.get(i), x, y+blockSize*i+70);
        }
    }
    
    
    
    public void sortScores(){
       
        sortedScoreList.clear();
        sortedScoreList.add(0);
        sortedNameList.clear();
        boardList.clear();
        while(boardList.size()<6){
            
            for(int i = 0; i < scoreList.size(); i++){
               if(sortedScoreList.get(0) < scoreList.get(i)){                    
                    sortedScoreList.add(i, scoreList.get(i));
                    sortedNameList.add(i,nameList.get(scoreList.indexOf(scoreList.get(i))));                    
                    boardList.add(i,sortedNameList.get(i)+":"+sortedScoreList.get(i));                    
                    scoreList.remove(i);
                    nameList.remove(i);
                    
                    System.out.println("sortinglist");
                }
            }
            System.out.println("A sorting run");
            
        }
        for(String string : boardList){
            System.out.println(string);
        }
    }
    
}
