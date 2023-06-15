package demogame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainClass
{
    public static void main(String arg[])
    {
        JFrame f= new JFrame();
        f.setTitle("BrickBreaker : how far can you go");
        f.setSize(700,600);//width,height
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
          ImageIcon icon = new ImageIcon("C:\\Users\\rsart\\Music\\brickbreakerpng.png");
          f.setIconImage(icon.getImage());
        
        
        f.setLocationRelativeTo(null); //frame will be at the centre of screen
        f.setVisible(true);
        f.setResizable(false); //no one can resize the game frame while playing
    
    
        //creating gamePlay class object
        GamePlay gamePlay = new GamePlay();
        //adding the Gameplay class to this class
        f.add(gamePlay);
    
    
    
    
    
    
    
    
    
    }

    

   
}