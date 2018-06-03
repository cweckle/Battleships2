import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Computer extends Player{
    private ArrayList<Marker> computerMarkers = new ArrayList<Marker>();
    private String lastXSpot = "";
    private int lastYSpot = -10;

    public Computer(){
        super();
        randomize();
    }

    public void randomize(){
        for(int i = 0; i < Player.NUMSHIPS; i ++){
            int x = (int)(Math.random()*((11-getShip(i).getLength())*Board.SIDE))+Board.LEFT;
            int y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
            while(shipOverlap(new Location(x,y),i+1)){
                x = (int)(Math.random()*((11-getShip(i).getLength())*Board.SIDE))+Board.LEFT;
                y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
                System.out.println("change");
            }
            getShip(i).move(x,y);
        }
        createLocs();
    }
    
    // public void placeHit(String x, int y)
    // {
        // computerMarkers.add(new Marker(true, x,y));
    // }

    // public void guess()
    // {
        // //check if the guessed place has hit something
        // boolean haveHit = false;
        // boolean sunkThisShip = false;
        // ArrayList <Spot> hitSpots = new ArrayList<Spot>();
        // while (haveHit == false || sunkThisShip == true)
        // {
            // boolean drop = false;
            // String xSpot = "";
            // int ySpot = 0;


            // if(lastXSpot.equals("") && lastYSpot == -10)
            // {
                // while (!drop)
                // {
                    // xSpot = Ship.convertIntX((int)(Math.random()* 600+20));
                    // ySpot = Ship.convertToGridY((int)(Math.random()*600+150));
                    // placeHit(xSpot,ySpot);
                // }
            // }
            // else
            // { 
                // while (!drop)
                // {
                    // int round = 0;
                    // for (int i = round; i <10; i+=4) //col x
                    // {
                        // for(int r = round; r <10; r+= 4) //row y
                        // {
                            // xSpot =  Ship.convertIntX(i);
                            // ySpot = r;
                            // placeHit(xSpot,ySpot);
                        // }
                        // round ++;
                    // }
                // }
            // }
            // for(int i = 0; i < hitSpots.size(); i++)
            // {
                // if(hitSpots.get(i).equals(new Spot(ySpot, xSpot)))
                // {
                    // i = hitSpots.size();
                // }
                // else
                // {
                    // hitSpots.add(new Spot(ySpot, xSpot));
                    // lastXSpot = xSpot;
                    // lastYSpot = ySpot;
                    // drop = true;
                    // if(overlap(Ship.getColVal(xSpot), ySpot) == true)
                    // {
                        // haveHit = true;
                    // }
                    // //check to see what happens when you drop the bomb here
                    // //and check to see if haveHit will be true
                    // //something needs to call this method guess
                // }
            // }
        // }
        // while(haveHit == true && sunkThisShip == false)
        // {
            // int xValue = 0;
            // int direction = 1;
            // if(direction == 1)
            // {
                // xValue = Ship.getColVal(lastXSpot) + 60;
                // placeHit(Ship.convertIntX(xValue), lastYSpot);
                // if(overlap(xValue,lastYSpot) == false)
                // {
                    // direction = -1;
                    // //check if entire ship has sunk
                    // //check if the entire ship has sunk
                // }
            // }
            // else if (direction == -1)
            // {
                // xValue = Ship.getColVal(lastXSpot) - 60;
                // placeHit(Ship.convertIntX(xValue), lastYSpot);
                // if(overlap(xValue,lastYSpot) == false)
                // {
                    // direction = 1;
                    // //check if this place has already been bombed
                    // //check if entire ship has sunk
                // }
            // }
            // for(int i = 0; i < hitSpots.size(); i++)
            // {
                // if(hitSpots.get(i).equals(new Spot(lastYSpot, Ship.convertIntX(xValue))))
                // {
                    // i = hitSpots.size();
                // }
                // else
                // {
                    // hitSpots.add(new Spot(lastYSpot, Ship.convertIntX(xValue)));
                    // lastXSpot = Ship.convertIntX(xValue);
                    // //drop = true;
                    // if(overlap(xValue, lastYSpot) == true)
                    // {
                        // haveHit = true;
                    // }
                    // //check to see what happens when you drop the bomb here
                    // //and check to see if haveHit will be true
                    // //something needs to call this method guess
                // }
            // }

            // //need to check if you have sunk the entire ship
        // }
    // }

}
