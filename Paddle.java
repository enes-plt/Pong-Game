import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{

    int id; // Identifier for the paddle (1 or 2)
    int yVelocity; // Velocity in the y direction
    int speed = 10; // Speed of the paddle
    
    // Constructor for Paddle, initializing position, size, and id
    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT); // Call to the superclass (Rectangle) constructor to set position and size
        this.id = id; // Set the paddle id (1 for left paddle, 2 for right paddle)

    }

    // Handle key press events to control paddle movement
    public void keyPressed(KeyEvent e){

        // Handle paddle movement on key press
        switch(id){ // Check the id to determine which paddle to control
            case 1: // Left paddle
                if(e.getKeyCode() == KeyEvent.VK_W){ // Move up if 'W' key is pressed
                    setYDirection(-speed); // Set y direction to negative speed
                    move(); // Move the paddle
                }
                if(e.getKeyCode() == KeyEvent.VK_S){ // Move down if 'S' key is pressed
                    setYDirection(speed); // Set y direction to positive speed
                    move();
                }
                break;
            case 2: // Right paddle
                if(e.getKeyCode() == KeyEvent.VK_UP){ // Move up if up arrow key is pressed
                    setYDirection(-speed); // Set y direction to negative speed
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){ // Move down if down arrow key is pressed
                    setYDirection(speed); // Set y direction to positive speed
                    move();
                }
                break;
        }

    }

    // Handle key release events to stop paddle movement
    public void keyReleased(KeyEvent e){

        switch(id){ // Check the id to determine which paddle to control
            case 1: // Left paddle
                if(e.getKeyCode() == KeyEvent.VK_W){ // Stop moving if 'W' key is released
                    setYDirection(0); // Set y direction to 0
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){ // Stop moving if 'S' key is released
                    setYDirection(0);
                    move();
                }
                break;
            case 2: // Right paddle
                if(e.getKeyCode() == KeyEvent.VK_UP){ // Stop moving if up arrow key is released
                    setYDirection(0); // Set y direction to 0
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){ // Stop moving if down arrow key is released
                    setYDirection(0); // Set y direction to 0
                    move();
                }
                break;
        }
        
    }

    // Set the y direction (velocity) of the paddle
    public void setYDirection(int yDirection){

        yVelocity = yDirection; // Update yVelocity with the new direction

    }

    // Move the paddle by updating its position based on its velocity
    public void move(){

        y = y + yVelocity; // Update the y position

    }

    // Draw the paddle on the screen
    public void draw(Graphics g){

        if(id == 1){ // If id is 1, set paddle color to blue
            g.setColor(Color.blue);
        }
        else{ // If id is 2, set paddle color to red
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height); // Draw a rectangle representing the paddle at its current position

    }
}
