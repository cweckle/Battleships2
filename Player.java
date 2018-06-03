import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Player{
    private Ship[] ships;
    public static final int NUMSHIPS=5;
    private Ship clicked;
    private ArrayList<Location> locs;

    public Player(){
        ships = new Ship[NUMSHIPS];
        clicked = new Ship();
        for(int i = 0; i < ships.length; i ++){
            ships[i] = new Ship(i+1); 
        }
        locs = new ArrayList<Location>();
    }
    
    public void createLocs(){
        locs = new ArrayList<Location>();
        for(Ship next : ships){
            if(next.getLoc().checkBounds()){
            String x = next.getLoc().getGridX();
            for(int i = 0; i < next.getLength(); i ++){
                locs.add(new Location(x, next.getLoc().getGridY()));
                x = Location.increment(x);
            }
        }
        }
        System.out.println();
    }
    
    public boolean outOfBounds(){
        for(Ship next : ships)
            if(!next.getLoc().checkBounds())
                return true;
        return false;
    }

    public Ship getShip(int i){
        return ships[i];
    }

    public boolean bombHit(Location next){
        // for(Location loc : locs)
            // if(loc.equals(next))
                // return true;
        if(pointOverlap(next))
            return true;
        return false;
    }
    
    public void snapTo(){
        for(Ship next : ships)
            next.snapLoc();
    }
    
    public ArrayList<Location> getLocs(){
        return locs;
    }
    

    // public boolean inBounds(){
        // //doesnt work yet
        // for(int i = 0; i < ships.length; i++){
            // int x = ships[i].getXLoc();
            // int y = ships[i].getY();
            // //for (int j = ships[i].length - 1; j > 0; j--){
            // if((x <= 20 || x>= 620) || (y <= 150 || y>= 750)){
                // return false;
            // }
            // //}
        // }
        // return true;
    // }

    //draws ships
    public void draw( Graphics page ){
        //color defined using rgb values (0-255 each)
        for(int i = 0; i < ships.length; i ++){
            page.setColor( new Color( 100, 100, 100 ) );
            ships[i].draw(page);
        }
    }

    public void drawMini(Graphics page){
        for(int i = 0; i < ships.length; i ++){
            page.setColor( new Color( 100, 100, 100 ) );
            ships[i].drawMini(page);
        }
    }

    public boolean move(int x, int y){
        if(!shipOverlap(new Location(x,y), clicked.getLength())){
            clicked.move(x,y);
            return false;
        }
        return true;
    }

    public void act(Location next){
        boolean overlaps = false;
        int num = 0;

        while(!overlaps && num < 5){
            overlaps = ships[num].overlapsWith(next);
            num++;
        }
        if(overlaps)
            clicked = ships[num-1];
    }
    
    public boolean shipOverlap(Location next, int length){
        if(!next.checkBounds())
            return false;
        createLocs();
        System.out.println("ship overlapppppp");
        String x = next.getGridX();
        int y = next.getGridY();
        System.out.println(new Location(x,y));
        int k = 0;
        while(k < length){
            // if(pointOverlap(next))
                // return true;
            // x = Location.increment(x);
            // System.out.println("wheer arej we ninow " + x);
            // k++;
            for(Location check : locs)
                if(check.equals(new Location(x,y))){
                    System.out.println("why " + check + " : " + new Location(x,y));
                    return true;
                }
            k++;
            x = Location.increment(x);
        }
        System.out.println("made it");
        return false;
    }

    public boolean pointOverlap(Location next){
        if(!next.checkBounds())
            return false;
            for(int i = 0; i < ships.length; i++){
            String storedX = ships[i].getLoc().getGridX();
            int storedY = ships[i].getLoc().getGridY();
            if(next.getGridY() == storedY){
                for(int check = 0; check < ships[i].getLength(); check++){
                    if(next.getGridX().equals(storedX))
                        return true;
                    storedX = Location.increment(storedX);
                } 
            }
        }
        return false;
    }
}