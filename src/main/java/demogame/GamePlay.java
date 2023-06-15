package demogame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePlay extends JPanel implements ActionListener, KeyListener/*extending jPanel is important otherwise f.add(gameplay) will show error*/ {

    private boolean play = false; //wen want user to play and not pc to automatically play
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int playerX = 350;
    private MapGenerator map;

    //creating the constructor
    public GamePlay() {
        
        super();
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay, this);
        timer.start();

        map = new MapGenerator(3, 7);
    }

    //i will use the method below to set the color of my canvas
    @Override
    public void paint(Graphics g) {
        //black canvas
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //border color
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 692, 5);
        g.fillRect(0, 5, 5, 587);
        g.fillRect(680, 5, 8, 592);
        g.fillRect(0, 558, 692, 5);

        //giving paddle 
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        //bricks
        map.draw((Graphics2D) g);

        //creating ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY, 20, 20);

        //scoore
        g.setColor(Color.green);
        g.setFont(new Font ("serif",Font.BOLD,20));
        g.drawString("Score : "+score,550,30);
        
        
        //gameOver
        if(ballposY>=570){
           play=false;
           ballXdir=0;
           ballYdir=0;
           g.setColor(Color.green);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("GameOver!! ,score :" +score,200,300 );
           g.setFont(new Font("serif",Font.BOLD,25));
           g.drawString(" Press ENTER to restart !!" +score,200,350 );
        }
        if(totalBricks<=0){
             play=false;
           ballXdir=0;
           ballYdir=0;
           g.setColor(Color.green);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("Hurrah!! You won the game , Score:" +score,150,300 );
           g.setFont(new Font("serif",Font.BOLD,25));
           g.drawString(" Press ENTER to restart !!" +score,200,350 );
        }
            
    }

    private void moveLeft() {
        play = true;//this makes the ball move
        playerX = playerX - 20;
    }

    private void moveRight() {
        play = true;// this makes the ball move
        playerX = playerX + 20;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //code for ball motion
        if (play) {
            if (ballposX <= 5) {
                ballXdir = -ballXdir;
            }

            if (ballposY <= 5) {
                ballYdir = -ballYdir;
            }

            if (ballposX >= 660) {
                ballXdir = -ballXdir;
            }
            // for lower surface dont use this concept

            //
            //creating a rectangle around ball
            Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
            Rectangle paddleRect = new Rectangle(playerX, 550, 200, 8);

            if (ballRect.intersects(paddleRect)) {
                ballYdir = -ballYdir;
            }

            //creating a new rectangle around bricks
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int width = map.brickWidth;
                        int height = map.brickHeight;
                        int brickXpos = 80 + j * width;
                        int brickYpos = 50 + i * height;

                        Rectangle brickRect = new Rectangle(brickXpos, brickYpos, width, height);

                        if (ballRect.intersects(brickRect)) {
                            map.setBrick(0, i, j);
                            totalBricks--;
                            score+=5;

                            if (ballposX + 19 <= brickXpos || ballposX + 1 >= brickXpos + width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }

                            break;

                        }

                    }

                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;

        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //we check for slider border ke bahar na chala jaye..
            if (playerX <= 5) {
                playerX = 5;
            } else {
                moveLeft();
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 580) {
                playerX = 580;
            } else {
                moveRight();
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                score = 0;
                totalBricks =21;
                ballposY =120;
                ballXdir = -1;
                ballYdir = 2;
                playerX = 320;
                map=new MapGenerator(3,7);
                
            }
        }
        
        

        //we will have to reapaint in order to make it moving
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
