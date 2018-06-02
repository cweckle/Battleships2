import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Spot extends JButton implements MouseListener
{
    private int row;
    private String column;

    public Spot(int r, String c)
    {
        row = r;
        column = c;
        this.addMouseListener(this);
    }

    public int getRow()
    {
        return row;
    }

    public String getCol()
    {
        return column;
    }

    public ArrayList<Spot> getSpotsAbove()
    {
        ArrayList<Spot> answer = new ArrayList<Spot>();
        for(int r = row; r >= 0; r --)
        {
            answer.add(new Spot(r, column));
        }
        return answer;
    }
    
    public boolean equals(Spot compare)
    {
        if(this.getRow() == compare.getRow() && this.getCol().equals(compare.getCol()))
        {
            return true;
        }
        return false;
    }

    //public Spot getRight()
    //{
       // return new Spot(row, column + 1);
    //}

    //public Spot getLeft()
    //{
        //return new Spot(row, column - 1);
    //}

    //public Spot getUp()
    //{
        //return new Spot(row - 1, column);
    //}

    //public Spot getDown()
    //{
       // return new Spot(row + 1, column);
    //}

    public ArrayList<Spot> getAdjacentSpots()
    {
        ArrayList<Spot> answer = new ArrayList<Spot>();
        //answer.add(getRight());
        //answer.add(getLeft());
        //answer.add(getUp());
        //answer.add(getDown());
        return answer;
    }

    public boolean inBounds(int height, int width)
    {
        //return (row >= 0 && row < height && column >= 0 && column < width);
        return true;
    }

    public int compareTo(Object other)
    {
        //Spot test = (Spot)other;
        //if(test.row == row)
            //return -(column - test.column);
        //return row - test.row;
        return 0;
    }

    public boolean equals(Object other)
    {
        Spot test = (Spot)other;
        return (row == test.row && column == test.column);
    }

    public String toString()
    {
        return row + " " + column;
    }

    public void mousePressed(MouseEvent e){
        System.out.println("yo");
        int xPos = e.getX();
        int yPos = e.getY();
    }

    public void mouseClicked(MouseEvent e){
    }

    public void mouseReleased(MouseEvent e){
    }

    public void mouseEntered(MouseEvent e){
    }

    public void mouseExited(MouseEvent e){
    }
   
}