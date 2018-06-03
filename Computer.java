import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Computer extends Player{
    private ArrayList<Marker> computerMarkers = new ArrayList<Marker>();
    private int lastXSpot;
    private Location lastLocation;
    private int lastYSpot;
    private int[] spotsHit;
    private ArrayList <Spot> hitSpots = new ArrayList<Spot>();
    private int direction;

    public Computer(){
        super();
        randomize();
        lastYSpot = -10;
        lastXSpot = -10;
        spotsHit = new int[5];
        direction = 0;
    }

    public int getLastXSpot()
    {
        return lastXSpot;
    }

    public int getLastYSpot()
    {
        return lastYSpot;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int n)
    {
        direction = n;
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

    public void placeHit(int x, int y)
    {
        computerMarkers.add(new Marker(true, x,y));
    }

    public Location guess(int xPlace, int yPlace, int direc)
    {
        int xGuess = 0;
        if(direc == 1)
        {
            xGuess = xPlace + 60;
        }
        else if (direc == -1)
        {
            xGuess = xPlace - 60;
        }
        else if (direc == 0)
        {
            guess(xPlace, yPlace);
        }
        for(int i = 0; i < hitSpots.size(); i++)
        {
            if(hitSpots.get(i).equals(new Spot(yPlace, Location.convertIntX(xPlace))))
            {
                i = hitSpots.size();
                guess(xPlace, yPlace);
            }
            else
            {
                hitSpots.add(new Spot(yPlace, Location.convertIntX(xPlace)));
                lastXSpot = xPlace;
                lastYSpot = yPlace;
            }
        }
        return new Location(xGuess, yPlace);
    }

    public Location guess(int xPlace, int yPlace) //the parameters are the last place it guessed
    {
        int xSpot = 0;
        int ySpot = 0;
        if(xPlace == -10 && lastYSpot == -10)
        {
            xSpot = (int)(Math.random()*600+20);
            ySpot = (int)(Math.random()*600+150);
            hitSpots.add(new Spot(ySpot, Location.convertIntX(xSpot)));
        }
        else
        { 
            xSpot =  (lastXSpot+240)%1000;
            ySpot = (lastYSpot+240)%800;
        }

        for(int i = 0; i < hitSpots.size(); i++)
        {
            if(hitSpots.get(i).equals(new Spot(ySpot, Location.convertIntX(xSpot))))
            {
                i = hitSpots.size();
                guess(xPlace+40, yPlace+60);
            }
            else
            {
                hitSpots.add(new Spot(ySpot, Location.convertIntX(xSpot)));
                lastXSpot = xSpot;
                lastYSpot = ySpot;
            }
        }
        return new Location(xSpot, yPlace);
    }
} 

