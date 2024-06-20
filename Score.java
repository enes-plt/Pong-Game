import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score {

    // Static variables for game dimensions
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    // Variables for keeping track of players' scores
    int player1;
    int player2;

    // Constructor to initialize the score with game dimensions
    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH; // Assign the game width to the static variable
        Score.GAME_HEIGHT = GAME_HEIGHT; // Assign the game height to the static variable
    }

    // Method to draw the score on the screen
    public void draw(Graphics g) {
        g.setColor(Color.white); // Set the drawing color to white
        g.setFont(new Font("Consolas", Font.PLAIN, 60)); // Set the font to "Consolas", plain style, and size 60

        // Draw a vertical line at the center of the screen to divide it into two halves
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

        // Draw player 1's score on the left side of the screen
        // The score is split into tens and units for proper formatting
        g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), (GAME_WIDTH / 2) - 85, 50);

        // Draw player 2's score on the right side of the screen
        // The score is split into tens and units for proper formatting
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (GAME_WIDTH / 2) + 20, 50);
    }
}
