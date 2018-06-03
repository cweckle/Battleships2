import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Ship{
    private int length;
    private int direction;
    private boolean selected = false;
    public static final int BORDER = 3;
    
    private Location loc;
    
    //player's ship constructor
    public Ship(int l){
        length = l; 
        setStartLoc();
        System.out.println(loc.getXVal() + ", " + loc.getYVal());
    }
    
    public Ship(){
    }
    
    public void snapLoc(){
        loc.snapTo();
    }
    
    public void setStartLoc(){
        if(length == 2 || length == 5){
            loc = new Location(20+(50*length)+200*(length%2),20, length);
        }
        else{
            int y = 80;
            if(length!=1)
                loc = new Location(20+300*(length%2+1), y, length);
            else
                loc = new Location(50, y, length);
        }
    }
    
    public int getLength(){
        return length;
    }
    
    public Location getLoc(){
        return loc;
    }
    
    public boolean overlapsWith(Location next){
        if(loc.equals(next)){
            selected = true;
            return true;
        }
        return false;
    }
    
    public void move(int x, int y){
        loc = new Location(x,y);
        selected = false;
    }
    
    public void changeState(){
        if(selected)
            selected = false;
        else
            selected = true;
    }
    
    public void draw( Graphics page ){
        if(loc.checkBounds())
            loc.snapTo();
        
        if (selected){
            page.setColor(Color.YELLOW);
            page.fillRect(loc.getXVal() - BORDER, loc.getYVal() - BORDER, 60*length -14, 36);
        }
        
        page.setColor(new Color(100,100,100));
        page.fillRect(loc.getXVal(), loc.getYVal(), 60*length -20, 30);
        page.setColor( new Color( 255, 255, 255 ) );
        for(int i = 0; i < length; i ++)
            page.fillOval(loc.getXVal() + (60*i) + 20, loc.getYVal() + 10, 7, 7);
    }
    
    public void drawMini(Graphics page){
        page.setColor(new Color(100,100,100));
        int miniX = Location.getColVal(loc.getGridX());
        int miniY = loc.getGridY(); 
        page.fillRect(Board.MINI_LEFT + miniX * Board.MINI_SIDE + 5, Board.MINI_TOP + miniY * Board.MINI_SIDE + 5, Board.MINI_SIDE*length-6, 10);
        page.setColor( new Color( 255, 255, 255 ) );
        for(int i = 0; i < length; i ++)
            page.fillOval(Board.MINI_LEFT + miniX * Board.MINI_SIDE + (Board.MINI_SIDE*i) + 9, Board.MINI_TOP + miniY * Board.MINI_SIDE +9, 2, 2);
    }
}