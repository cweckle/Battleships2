//Last Changed 6/1/2018
//duplicates method in computer 

import javax.swing.*;
import java.awt.*;
public class Driver
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    
    public static void driver()
    {
        //create a JFrame (window) that will be visible on screen
        JFrame frame = new JFrame( "Player 1" );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //make the red close button work
        frame.setLocation( 0, 0 ); //place the frame in the upper left corner
        Game game = new Game(Driver.WIDTH, Driver.HEIGHT); //create a Game object with width = 1000, height = 800
        frame.getContentPane().add(game); //add game to the frame so it will be on the screen
        frame.pack();
        frame.setVisible(true);
       
        JButton button = new JButton("Click here when you are done placing ships");

        frame.add(button);
        frame.setVisible(true);
        
        game.playGame();//call the playGame() method to intitiate the game
    }
}