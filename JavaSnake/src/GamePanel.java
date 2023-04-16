import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    // Game Panel Settings
    static final int SCREEN_WIDTH = 600 ;
    static final int SCREEN_HEIGHT = 600 ;
    static final int UNIT_SIZE = 25 ;
    static final int GAME_UNITS = ( SCREEN_WIDTH * SCREEN_HEIGHT ) / UNIT_SIZE ;
    static final int DELAY = 75 ;

    // Arrays to hold all od the coordinates for the body parts of the snake
    final int x[] = new int [GAME_UNITS] ;
    final int y[] = new int [GAME_UNITS] ;
    int bodyParts = 6 ;


    int applesEaten = 0 ;
    int appleX ; // X - coordinate of where the apple is located and it's going to appear
    int appleY ; // Y - coordinate of where the apple is located and it's going to appear

    char direction = 'R' ;   // R - Right , L-left , D - Down and U - Up
    boolean running = false;

    Timer timer ;
    Random random ;






    GamePanel() {
        random = new Random();
        this.setPreferredSize( new Dimension(SCREEN_WIDTH , SCREEN_HEIGHT));
        this.setBackground( Color.BLACK );
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }

    public void startGame() {
        newApple() ;
        running = true;
        timer = new Timer(DELAY , this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

    }
    public void newApple () {

    }
    public void move() {

    }

    public void checkApple() {

    }

    public void checkCollision() {

    }

    public void gameOver () {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public class MyKeyAdapter extends KeyAdapter {

        public void KeyPressed(KeyEvent e) {

        }
    }
}
