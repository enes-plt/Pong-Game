import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Ball extends Rectangle{

    Random random; // Random object for randomizing initial directions
    int xVelocity; // Velocity in the x direction
    int yVelocity; // Velocity in the y direction
    int initialSpeed = 2; // Initial speed of the ball


    // Constructor for Ball, initializing position, size, and direction
    Ball(int x, int y, int width, int height){

        super(x, y, width, height); // Call to the superclass (Rectangle) constructor to set position and size
        random = new Random(); // Instantiate the Random object


        // Determine random initial direction for x-axis
        int randomXDirection = random.nextInt(2); // Generate a random number 0 or 1

        if(randomXDirection == 0){
            randomXDirection--; // If it's 0, change it to -1 to get directions -1 or 1
        }
        setXDirection(randomXDirection * initialSpeed); // Set the x direction with initial speed


        // Determine random initial direction for y-axis
        int randomYDirection = random.nextInt(2); // Generate a random number 0 or 1

        if(randomYDirection == 0){
            randomYDirection--; // If it's 0, change it to -1 to get directions -1 or 1
        }
        setYDirection(randomYDirection * initialSpeed); // Set the y direction with initial speed

    }

    // Set the x direction (velocity) of the ball
    public void setXDirection(int randomXDriection){

        xVelocity = randomXDriection; // Update xVelocity with the new direction

    }

    // Set the y direction (velocity) of the ball
    public void setYDirection(int randomYDriection){

        yVelocity = randomYDriection; // Update yVelocity with the new direction
        
    }

    // Move the ball by updating its position based on its velocity
    public void move(){

        x += xVelocity; // Update the x position
        y += yVelocity; // Update the y position

    }

    // Draw the ball on the screen
    public void draw(Graphics g){

        g.setColor(Color.white);
        g.fillOval(x, y, height, width);

    }
}
