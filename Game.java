import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Game extends JPanel implements MouseListener,ActionListener,KeyListener
{
    public static String TEXT = "DONE PLACING YOUR SHIPS?";
    public static String COMPTURN = "The computer is guessing now.";
    public static String YOURTURN = "It's your turn to guess!";
    public static String HIT = "HIT!";
    public static String MISS = "MISS!";
    public static String IN_GRID = "All of your ships must be in the grid.";
    public static String START = "PRESS SPACE TO BEGIN";
    
    private Player player;
    private Board board;
    private Computer computer;
    private boolean placed = false;
    private boolean selected = false;
    private JButton button;
    private JLabel turn;
    private JLabel result;
    private JLabel error;
    private JLabel titleBack;
    private JLabel logo;
    private JLabel begin;
    private boolean begun;

    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        //make a panel with dimensions width by height with a black background
        this.setLayout( null );//Don't change
        this.setBackground( new Color(0, 100, 180) );
        this.setPreferredSize( new Dimension( width, height ) );//Don't change

        //initialize the instance variables
        player = new Player();  //change these numbers and see what happens
        board = new Board();
        computer = new Computer();
        this.addMouseListener(this);//allows the program to respond to key presses - Don't change
        this.addKeyListener(this);
        
        button = new JButton(TEXT);
        button.setBounds(700,500,200,50);
        this.add(button);
        button.setVisible(false);
        button.addActionListener(this);

        turn = new JLabel(YOURTURN);
        turn.setForeground(Color.WHITE);
        turn.setBounds(80,50,800,50);
        this.add(turn);
        turn.setVisible(false);
        turn.setFont(new Font("Courier New", Font.ITALIC, 36));

        error = new JLabel(IN_GRID);
        error.setForeground(Color.WHITE);
        error.setBounds(80,50,800,50);
        this.add(error);
        error.setVisible(false);
        error.setFont(new Font("Courier New", Font.BOLD, 25));

        result = new JLabel();
        result.setForeground(Color.RED);
        result.setBounds(700, 700, 300, 80);
        this.add(result);
        result.setVisible(false);
        result.setFont(new Font("Courier New", Font.BOLD, 80));
        
        begin = new JLabel(START);
        begin.setBounds(300, 500, 500,30);
        begin.setForeground(Color.WHITE);
        this.add(begin);
        begin.setVisible(true);
        begin.setFont(new Font("Courier New", Font.BOLD, 35));
        
        ImageIcon battleLogo = new ImageIcon("logo.jpg");
        logo = new JLabel(battleLogo);
        logo.setBounds(50,250,900,240);
        this.add(logo);
        logo.setVisible(true);
        
        ImageIcon iconLogo = new ImageIcon("boats.jpg");
        titleBack = new JLabel(iconLogo);
        titleBack.setBounds(0,0,1000,800);
        this.add(titleBack);
        titleBack.setVisible(true);
        
        begun = false;
        
        this.setFocusable(true);//I'll tell you later - Don't change
    }

    public void playGame()
    {
        boolean over = false;
        while( !over )
        {
            try
            {
                Thread.sleep( 200 );//pause for 200 milliseconds
                if(!begun)
                    begin.setVisible(!begin.isVisible());
            }catch( InterruptedException ex ){}
            if(error.isVisible() || result.isVisible())
                try{
                    Thread.sleep(800);
                    result.setVisible(false);
                    error.setVisible(false);
                }catch(InterruptedException ex){}

            this.repaint();//redraw the screen with the updated locations; calls paintComponent below

        }
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );//I'll tell you later.
        player.snapTo();
        computer.snapTo();
        if(!placed)
        {
            board.draw(page);
            player.draw( page );//calls the draw method in the Player class
        }
        else
        {
            turn.setVisible(true);
            board.drawMini(page);
            player.drawMini(page);
            board.drawGame(page);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Location clicked = new Location(x,y);
        result.setVisible(false);
        if(!placed){
            if(!selected){
                player.act(clicked);
                System.out.println("hola");
                selected = true;
            }
            else{
                selected = player.move(x, y);
            }
        }
        else if(clicked.checkBounds()){
            if((computer.bombHit(new Location(x, y)) == true)) //not sure if this is supposed to be this. We need to drop a bomb, and that bomb is what needs to call bombHit i think
            {
                result.setForeground(Color.RED);
                result.setText(HIT);
                result.setVisible(true);
                board.placeHit(new Location(x,y));
            }
            else{
                result.setForeground(new Color(200,200,200));
                result.setText(MISS);
                result.setVisible(true);
                board.placeMiss(new Location(x,y));
            }
            changeText();
        }
    }

    public void changePlaceBoolean(){
        if (selected)
            selected = false;
        else
            selected = true;
    }

    public void changeText(){
        if(turn.getText().equals(YOURTURN))
            turn.setText(COMPTURN);
        else
            turn.setText(YOURTURN);
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode() == KeyEvent.VK_SPACE ){
            titleBack.setVisible(false);
            logo.setVisible(false);
            begin.setVisible(false);
            button.setVisible(true);
            begun = true;
        }
    }
    
    public void keyTyped(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent event){
        player.createLocs();
        computer.createLocs();
        if(player.outOfBounds())
            error.setVisible(true);
        else{
            placed = true;
            button.setVisible(false);
        }
    }
}