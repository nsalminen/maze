/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Sprites.*;
import java.util.ArrayList;

/**
 *
 * @author Yasen
 */
public class Node {

    public int id;
    private Sprite occupant;
    
    public ArrayList<Sprite> occupants = new ArrayList<Sprite>();
    
    public int xInd;
    public int yInd;

    public Node(int x, int y, int nodeId) {
        xInd = x;
        yInd = y;
        id = nodeId;
    }

    public Sprite getOccupant(int index) {
        if(index > occupants.size()){
                return occupants.get(index);
        }
        else{
            return null;
        }
    }

    public void setOccupant(int index, Sprite occupant) {
        occupants.set(index, occupant);
    }
    
    public void trimOccupants(int trimSize){
        for (int i = 0 ; i < trimSize; i++){
            occupants.remove(i);
        }   
    }    
    
    public Sprite popOccupant(){
        Sprite sprite = occupants.get(occupants.size()-1);      
        return sprite;    
    }
    
    public void addOccupant(Sprite sprite){
        occupants.add(sprite);
    }

    @Override
    public String toString() {
        
        String occ = "";
//        
//        for (Sprite occup : occupants ){
//            occ.concat(occup.getClass().getCanonicalName()+" + ") ;
//          
//        }
        
        occ = occupants.toString();
        
        String string = "x = " + xInd + "; y = " + yInd + "; occupied by: " + occ;
          
        
        
        return string;
    }
}