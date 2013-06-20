/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Window.GamePanel;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Yasen
 */
public class ScoreBoard {

    private int xPos;
    private int yPos;
    private int blockSize;
    TreeMap<String, Integer> map;
    ArrayList<String> nameList;
    ArrayList<Integer> scoreList;
    ArrayList<Integer> sortedScoreList = new ArrayList<Integer>();
    ArrayList<String> sortedNameList = new ArrayList<String>();
    ArrayList<String> boardList = new ArrayList<String>();

    public ScoreBoard(int xPos, int yPos, GamePanel p) {
        blockSize = p.blockSize;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String printLine(int i) {
        String string = scoreList.get(i) + ("  \t  " + nameList.get(i));
        System.out.println(string);
        return string;
    }

    public void sortScores() {
        sortedScoreList.clear();
        sortedScoreList.add(0);
        sortedNameList.clear();
        boardList.clear();
        while (boardList.size() < 6) {

            for (int i = 0; i < scoreList.size(); i++) {
                if (sortedScoreList.get(0) < scoreList.get(i)) {
                    sortedScoreList.add(i, scoreList.get(i));
                    sortedNameList.add(i, nameList.get(scoreList.indexOf(scoreList.get(i))));
                    boardList.add(i, sortedNameList.get(i) + ":" + sortedScoreList.get(i));
                    scoreList.remove(i);
                    nameList.remove(i);

                    System.out.println("sortinglist");
                }
            }
            System.out.println("A sorting run");

        }
        for (String string : boardList) {
            System.out.println(string);
        }
    }
}
