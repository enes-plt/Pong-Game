import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Class for the game panel where the game is drawn and updated
public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    
    GamePanel(){
        // Initialize paddles and ball
        newPaddles();
        newBall();
        // Initialize score
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        // Enable key listener
        this.setFocusable(true);
        this.addKeyListener(new AL());
        // Set the preferred size of the panel
        this.setPreferredSize(SCREEN_SIZE);
        // Start the game thread
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newBall(){

        // Create a randomly new ball
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);

    }

    public void newPaddles(){

        // Create paddles at the left and right
        paddle1 = new Paddle(0,(GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH,(GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);

    }

    public void paint(Graphics g){
        
        // Draw the game components
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g){

        // Draw paddles, ball, and score
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);

    }

    public void move(){

        // Move paddles and ball
        paddle1.move();
        paddle2.move();
        ball.move();

    }

    public void checkCollision(){
        // Bounce the ball off top and bottom window edges
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }

        // Bounce ball off paddles
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // optional for more difficulty

            if(ball.yVelocity > 0){
                ball.yVelocity++; // optional for more difficulty
            }
            else{
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // optional for more difficulty

            if(ball.yVelocity > 0){
                ball.yVelocity++; // optional for more difficulty
            }
            else{
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }


        // Stop paddles at window edges
        if(paddle1.y <= 0){
            paddle1.y = 0;
        }
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)){
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        if(paddle2.y <= 0){
            paddle2.y = 0;
        }
        if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)){
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // Give player 1 point and creates new paddles and ball
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2:" + score.player2);
        }

        if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1:" + score.player1);
        }

    }

    public void run(){
        // Game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }

        }

    }

    // Key adapter class for handling key events
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){

            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }

        public void keyReleased(KeyEvent e){

            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
            
        }
    }
}
