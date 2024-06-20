import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Class for the game frame (window)
public class GameFrame extends JFrame{

    GamePanel panel;
    
    GameFrame(){

        // Initialize the game panel
        panel = new GamePanel();

        // Add the panel to the frame
        this.add(panel);

        // Set the title of the frame
        this.setTitle("Pong Game");

        // Disable resizing
        this.setResizable(false);

        // Set the background color
        this.setBackground(Color.black);

        // Close the application when the frame is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pack the components within the frame
        this.pack();

        // Make the frame visible
        this.setVisible(true);
        
        // Center the frame on the screen
        this.setLocationRelativeTo(null);

    }
}
