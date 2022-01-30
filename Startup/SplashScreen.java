package Startup;// This is a test program for using java and GitHub for CS 499, team 6:
// Guess Crow
// Marie Held
// Samuel Martin
// Bryant Terry

// Program function: Display the project logo, followed by a team member's name.
// Program purpose: To test GitHub functionality

// Imports for using java's swing GUI functionality
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Image;

/* FrameDemo.java requires no other files. */
public class SplashScreen {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    // Method to create and show the GUI of the splash screen
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame splashScreenFrame = new JFrame("Splash");
        splashScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets up our logo
        ImageIcon logo = new ImageIcon("Senior Proj Logo.png");
        Image icon = logo.getImage();

        // Make a JLabel to hold our text (group member names) and our logo image
        JLabel namesLabel = new JLabel("Guess Crow, Bryant Terry, Samuel Martin, Marie Held");
        namesLabel.setPreferredSize(new Dimension(500, 200));
        namesLabel.setIcon(logo); // Sets the JLabel's icon to our logo image

        // Alignment/layout stuff to center the logo and text
        namesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        splashScreenFrame.getContentPane().add(namesLabel, BorderLayout.SOUTH);
        namesLabel.setHorizontalTextPosition(JLabel.CENTER);
        namesLabel.setVerticalTextPosition(JLabel.BOTTOM);

        splashScreenFrame.add(namesLabel); // Add our JLabel to the window

        //Display the window.
        splashScreenFrame.setSize(960, 540);
        splashScreenFrame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
