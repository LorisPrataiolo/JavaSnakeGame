import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    
   //-----------------------------------------------

    public void startGame() {
        newApple() ;
        running = true;
        timer = new Timer(DELAY , this);
        timer.start();
    }
    
    //-----------------------------------------------


    public void paintComponent(Graphics Field){
        super.paintComponent(Field);
        drawField(Field);
    }
    
    //-----------------------------------------------

    public void drawField(Graphics Field) {

        // draw the field
        for (int i = 0 ; i < SCREEN_HEIGHT / UNIT_SIZE ; i++ ) {
            Field.drawLine ( i* UNIT_SIZE , 0 , i*UNIT_SIZE , SCREEN_HEIGHT) ;
            Field.drawLine ( 0 , i* UNIT_SIZE , SCREEN_WIDTH , i* UNIT_SIZE) ;
        }

        // apple on the field
        Field.setColor(Color.RED);
        Field.fillOval(appleX , appleY, UNIT_SIZE, UNIT_SIZE);

        // snake on the field
        if(running) {
            for (int i = 0; i < bodyParts; i++) {

                // snake's head color
                if (i == 0) {
                    Field.setColor(Color.GREEN);
                    Field.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                // snake's body color
                else {
                    Field.setColor(new Color(177, 234, 130));
                    Field.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }

            }
            showScore(Field);
        }
        else{
            gameOver(Field);
        }

    }
    

     //-----------------------------------------------
    // Generate the coordinates of a new apple
    public void newApple () {
        appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }



    public void move() {
        // shifting the body parts of the snake
        for (int i = bodyParts; i > 0  ; i-- ) {

            x[i] = x [i-1] ;
            y[i] = y [i-1] ;

        }


        // switch the directions
        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }


    }

    
    //-----------------------------------------------

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten ++;
            newApple();
        }

    }

    
    //-----------------------------------------------

    public void checkCollision() {

        // check if head COLLIDES with the body
        for (int i = bodyParts; i > 0 ; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }

        // check if head touches LEFT border
        if ( x[0] < 0) {
            running = false ;
        }

        // check if head touches RIGHT border
        if ( x[0] > SCREEN_WIDTH) {
            running = false ;
        }

        // check if head touches TOP border
        if ( y[0] < 0) {
            running = false ;
        }

        // check if head touches BOTTOM border
        if ( y[0] > SCREEN_HEIGHT) {
            running = false ;
        }
        if(!running) {
            timer.stop();
        }
    }

    
    
    //-----------------------------------------------
    public void showScore (Graphics Score_Text) {
        // text
        Score_Text.setColor(Color.cyan);
        Score_Text.setFont(new Font("new" , Font.PLAIN,35));
        FontMetrics metrics = getFontMetrics(Score_Text.getFont());

        Score_Text.drawString("score : " +applesEaten,
                (SCREEN_WIDTH - metrics.stringWidth("score : " +applesEaten)) / 2 ,
                Score_Text.getFont().getSize() );


    }
    
    
    //-----------------------------------------------
    public void gameOver (Graphics GO_Text) {
        // text
        GO_Text.setColor(Color.orange);
        GO_Text.setFont(new Font("new" , Font.PLAIN,75));
        FontMetrics metrics = getFontMetrics(GO_Text.getFont());

        GO_Text.drawString("Game Over",
                            (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2 ,
                            SCREEN_HEIGHT/2 );

        showScore(GO_Text);


    }
    
    
    
    //-----------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        if( running) {
            move();
            checkApple();
            checkCollision();
        }
        repaint();

    }
    
    
    
    //-----------------------------------------------
    // key adapter transforms directional arrow inputs into direction inputs
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed (KeyEvent e) {

            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }

        }
    }
}
