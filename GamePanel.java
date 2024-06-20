import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Class for the game panel where the game is drawn and updated
public class GamePanel extends JPanel implements Runnable{

    // Constants for game dimensions and component sizes
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); // Proportional height based on width
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread; // Thread for running the game loop
    Image image; // Off-screen image for double buffering
    Graphics graphics; // Graphics object for drawing on the off-screen image
    Random random; // Random object for randomizing ball start position
    Paddle paddle1; // Left paddle
    Paddle paddle2; // Right paddle
    Ball ball; // Ball
    Score score; // Score tracker
    
    // Constructor for initializing the game panel
    GamePanel() {

        newPaddles(); // Initialize paddles
        newBall(); // Initialize ball
        score = new Score(GAME_WIDTH, GAME_HEIGHT); // Initialize score
        this.setFocusable(true); // Make the panel focusable to receive key events
        this.addKeyListener(new AL()); // Add key listener for paddle control
        this.setPreferredSize(SCREEN_SIZE); // Set preferred size of the panel
        gameThread = new Thread(this); // Create and start the game thread
        gameThread.start();
    }

    // Method to create a new ball at a random vertical position
    public void newBall(){

        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);

    }

    // Method to create new paddles at the left and right sides
    public void newPaddles(){

        paddle1 = new Paddle(0,(GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH,(GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);

    }

    public void paint(Graphics g){
        
        // Draw the game components
        image = createImage(getWidth(), getHeight()); // Create an off-screen image
        graphics = image.getGraphics(); // Get the graphics object for the image
        draw(graphics); // Draw game components on the image
        g.drawImage(image, 0, 0, this); // Draw the image on the panel

    }

    // Method to draw paddles, ball, and score
    public void draw(Graphics g){

        paddle1.draw(g); // Draw left paddle
        paddle2.draw(g); // Draw right paddle
        ball.draw(g); // Draw ball
        score.draw(g); // Draw score

    }

    // Method to move paddles and ball
    public void move(){

        paddle1.move(); // Move left paddle
        paddle2.move(); // Move right paddle
        ball.move(); // Move ball

    }

    // Method to check and handle collisions
    public void checkCollision() {
        // Bounce the ball off top and bottom window edges
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        // Bounce ball off paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity); // Ensure positive x velocity
            ball.xVelocity++; // Increase x velocity for added difficulty

            // Increase or decrease y velocity for added difficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity); // Ensure positive x velocity
            ball.xVelocity++; // Increase x velocity for added difficulty

            // Increase or decrease y velocity for added difficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity); // Ensure negative x velocity to bounce back
            ball.setYDirection(ball.yVelocity);
        }

        // Stop paddles at window edges
        if (paddle1.y <= 0) {
            paddle1.y = 0; // Stop left paddle at top edge
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT; // Stop left paddle at bottom edge
        }

        if (paddle2.y <= 0) {
            paddle2.y = 0; // Stop right paddle at top edge
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT; // Stop right paddle at bottom edge
        }

        // Give player 1 point and create new paddles and ball if the ball goes out on the left side
        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }

        // Give player 2 point and create new paddles and ball if the ball goes out on the right side
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }
    }

    // Game loop for updating and rendering the game
    public void run(){

        long lastTime = System.nanoTime(); // Get the initial time
        double amountOfTicks = 60.0; // Set the target number of ticks per second
        double ns = 1000000000 / amountOfTicks; // Convert ticks to nanoseconds
        double delta = 0;

        while (true) {
            long now = System.nanoTime(); // Get the current time
            delta += (now - lastTime) / ns; // Calculate the time difference
            lastTime = now;

            if (delta >= 1) {
                move(); // Move paddles and ball
                checkCollision(); // Check and handle collisions
                repaint(); // Repaint the game panel
                delta--; // Decrement the delta
            }
        }
    }

    // Key adapter class for handling key events
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){

            paddle1.keyPressed(e); // Handle key press for left paddle
            paddle2.keyPressed(e); // Handle key press for right paddle

        }

        public void keyReleased(KeyEvent e){

            paddle1.keyReleased(e); // Handle key release for left paddle
            paddle2.keyReleased(e); // Handle key release for right paddle
            
        }
    }
}
