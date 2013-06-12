package Sprites;

import Game.Node;
import Window.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Yasen
 */
public class Player extends Sprite {

    public Point facing = new Point(999,999);
    public int direction;
    public boolean hasPortalGun = false;
    public int stepsTaken = 0;

    public Player(int x, int y, GamePanel p) {
        position.setLocation(x, y);
        panel = p;
        setDirection(1);
        panel.maze.nodes[y][x].addOccupant(this);
        parent = panel.maze.nodes[y][x];
    }
    
    public Player(Point p, GamePanel pan) {
        position = p;
        panel = pan;
        setDirection(1);
        panel.maze.nodes[position.y][position.x].addOccupant(this);
    }

    public void shoot() {
        if (hasPortalGun) {

            boolean shooting = true;

            int xOrigin = position.x;
            int yOrigin = position.y;

                 while (shooting) {
                     if (getDirection() == 0) {
                        yOrigin--;
                     }if (getDirection() == 1) {
                        xOrigin++;
                     }if (getDirection() == 2) {
                        yOrigin++;
                     }if (getDirection() == 3) {
                        xOrigin--;
                     }

                    if ((xOrigin-1 ==  0 || yOrigin-1 == 0) || yOrigin+1 == panel.maze.nodes.length || xOrigin+1 == panel.maze.nodes[0].length) {
                        
                        System.out.println("Fell off the deep end");
                        shooting = false;
                    }
                    
                    if (!panel.maze.nodes[yOrigin][xOrigin].popOccupant().getClass().getCanonicalName().equals("Sprites.Wall")) {
                        System.out.println("No Wall");
                                    
                    }
                    
                    if (panel.maze.nodes[yOrigin][xOrigin].popOccupant() instanceof  Wall) {
                        System.out.println("BOOM");                        
                        panel.maze.nodes[yOrigin][xOrigin].trimOccupants(1);
                        shooting = false;
                    }
            hasPortalGun = false;
          }
        }
    }
    public void paintSelf(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(getX(), getY(), panel.blockSize, panel.blockSize);

        g.setColor(Color.blue);
        g.drawRect(facing.x * panel.blockSize, facing.y * panel.blockSize, panel.blockSize, panel.blockSize);
        
        if (hasPortalGun) {
            int[] xp = {getX() + panel.blockSize, getX() + panel.blockSize, getX()};
            int[] yp = {getY(), getY() + panel.blockSize, getY() + panel.blockSize};

            g.setColor(Color.green);
            g.fillPolygon(xp, yp, 3);
        }

        g.setColor(Color.CYAN);

        if (getDirection() == 0) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()) + (panel.blockSize / 2),
                    getY());
        } else if (getDirection() == 1) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()) + (panel.blockSize),
                    getY() + (panel.blockSize / 2));
        } else if (getDirection() == 2) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()) + (panel.blockSize / 2),
                    getY() + (panel.blockSize));
        } else if (getDirection() == 3) {
            g.drawLine((getX()) + (panel.blockSize / 2),
                    (getY()) + (panel.blockSize / 2),
                    //This second vertex shows the direction
                    (getX()),
                    getY() + (panel.blockSize / 2));
        }
        checkPortalGun();
        checkTimeMachine();
        checkHelper();
    }

    public void checkPortalGun() {

        String portalnode = panel.maze.nodes[panel.portalGun.position.y][panel.portalGun.position.x].popOccupant().getClass().getCanonicalName();

        if (portalnode.equals("Sprites.Player") && !panel.portalGun.taken) {
            panel.maze.nodes[panel.portalGun.position.y][panel.portalGun.position.x].trimOccupants(1);
            this.hasPortalGun = true;
            panel.portalGun.taken = true;
        }

    }
    
    public void checkTimeMachine() {

        String tmnode = panel.maze.nodes[panel.timeMachine.position.y][panel.timeMachine.position.x].popOccupant().getClass().getCanonicalName();

        if(tmnode.equals("Sprites.Player") && !panel.timeMachine.taken) {
            panel.maze.nodes[panel.timeMachine.position.y][panel.timeMachine.position.x].trimOccupants(1);
            
            for ( int n  = 0; n < panel.timeMachine.stepsReduced; n++)
            {
                if(stepsTaken > 0 )
                {
                    stepsTaken--;
                }
                
            }
            panel.timeMachine.taken = true;

        }

    }
    
    private void checkHelper() {
        String hnode = panel.maze.getNodes()[panel.helper.position.y][panel.helper.position.x].popOccupant().getClass().getCanonicalName();

        if (hnode.equals("Sprites.Player") && !panel.helper.taken) {
            panel.maze.nodes[panel.helper.position.y][panel.helper.position.x].trimOccupants(1);
            panel.maze.findPath(panel.maze.nodes[position.y][position.x]);
            panel.maze.showPath = true;
            panel.repaint();
        }
    }    
    public void moveNorth(){
     if(getDirection() == 0){
      move();
       
       }
       else{
       setDirection(0);
       }
    }
    public void moveEast(){
       if(getDirection() == 1){
        move();
       
       }
       else{
       setDirection(1);
       }
    }
    public void moveSouth(){ 
       if(getDirection() == 2){       
       move();
        
       }
       else{
       setDirection(2);
       }
    }
       
    public void moveWest(){
       if(getDirection() == 3){
       move();
       
       }
        else{
       setDirection(3);
       }
       
    }
    
    public boolean canMove(){        
        boolean canMove = false;
        
        if(!(facing.x < 0) && !(facing.y < 0)){
            if(!(facing.x+1 > panel.maze.nodes.length) && !(facing.y+1 > panel.maze.nodes[0].length)){
                if(!panel.maze.getNode(facing).isWall()){
                    canMove = true;
                }
            }
        }
        return canMove;
    }

    /**
     * A method that determines what kind of movement is requested of
     * {@link Sprites.Player} and executes the movement.
     *
     * @param direction A variable that is used to determine in which way the
     * user would like to move the player
     */
    public void move() {
        if(canMove()){
            panel.maze.getNode(position).trimOccupants(1);
            System.out.println("MOVING");            
            panel.maze.getNode(facing).addOccupant(this);
            parent = panel.maze.nodes[facing.y][facing.x];
            position.setLocation(parent.xInd,parent.yInd);            
            System.out.println("Player"+position);
            System.out.println("STOPPED");
            updateFacing();
            
        }
    }

    @Override
    public String toString() {
        String string = "X:" + this.position.x + " Y:" + this.position.y + " DIR:" + this.direction;
        return string;
    }
    
    public int getDirection() {
        return direction;
    }
    
    private void updateFacing(){
        if (getDirection() == 0 ){
            facing.setLocation(position.getX(), position.getY()-1);
        }
        if (getDirection() == 1 ){
            facing.setLocation(position.getX()+1, position.getY());
            
        }
        if (getDirection() == 2 ){
            facing.setLocation(position.getX(), position.getY()+1);
            
        }
        if (getDirection() == 3 ){
            facing.setLocation(position.getX()-1, position.getY());
        }
        
        System.out.println("--------------");
    }

    public void setDirection(int dir) {
        direction = dir;
        updateFacing();
    }
}
