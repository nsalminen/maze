/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.awt.Point;

/**
 * This class combines a Point object and a direction integer to determine
 * the position of a player sprite and direction it is facing. It is used to record
 * the steps taken and enable the Undo function
 * 
 * @author Yasen
 */
public class Position{
            
        public Point point;
        public int direction;


       /**
        * @param p Point: determines the point
        * @param dir DirectionL determines the direction
        */            

        public Position(Point p, int dir){
            point = p;
            direction = dir;
        }

       /**
        * @param x Point: determines the x Index
        * @param y Point: determines the y Index
        * @param dir DirectionL determines the direction
        */        
        public Position(int x, int y, int dir){
            point = new Point(x,y);
            direction = dir;
        }

        /**
         *   Translates the position obkect into a String with the following format:
         *       "XCOORDINATE,YCOORDITANE,DIRECTION-"
         */   
        @Override
        public String toString() {
            return ""+ point.x + "," + point.y + "," +direction+"-";
        }
            
            

}