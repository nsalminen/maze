/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.awt.Point;

/**
 *
 * @author Yasen
 */
public class Position{
            
            public Point point;
            public int direction;
            
            public Position(Point p, int dir){
                point = p;
                direction = dir;
            }
            public Position(int x, int y, int dir){
                point = new Point(x,y);
                direction = dir;
            }

    @Override
    public String toString() {
        return ""+ point.x + "," + point.y + "," +direction+"-";
    }
            
            

}