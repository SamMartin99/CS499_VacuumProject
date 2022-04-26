package Startup;// This is a test program for using java and GitHub for CS 499, team 6:
// Guess Crow
// Marie Held
// Samuel Martin
// Bryant Terry

// Program function: Display the project logo, followed by a team member's name.
// Program purpose: To test GitHub functionality

// Imports for using java's swing GUI functionality

import Model.TileArray;
import View.MainHouseLayoutGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/* FrameDemo.java requires no other files. */

public class SplashScreen {

    // Global variable to indicate the type of tile (wall, door, etc)
    static String tileType = "";

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    // Method to create and show the GUI of the splash screen
    private static void createAndShowGUI() {
        staticVariable arrayValues = new staticVariable();
        //Create and set up the window.
        JFrame splashScreenFrame = new JFrame("Splash");
        splashScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets up our logo
        ImageIcon logo = new ImageIcon("Senior Proj Logo.png");

        // Make a JLabel to hold our text (group member names) and our logo image
        JLabel namesLabel = new JLabel("Guess Crow, Marie Held, Samuel Martin, Bryant Terry");
        namesLabel.setPreferredSize(new Dimension(500, 200));
        namesLabel.setIcon(logo); // Sets the JLabel's icon to our logo image

        JButton startButton = new JButton("Press to start program");  // mh  Button to start the program

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splashScreenFrame.setVisible(false);  //mh
                splashScreenFrame.dispose();          //mh  Close out the splash screen and remove any associated memory
                staticVariable arrayBounds = new staticVariable();

                // Code block for loading default house layout from file
                HouseLayoutFileHandling fileReader = new HouseLayoutFileHandling(new File("House Layout Files\\defaultHouseLayoutFile.txt")); // Grab the default house file
                HouseLayout myhouse = null; // Create a new HouseLayout
                TileArray testArr = null;
                try {
                    fileReader.readHouseLayoutFile().printTileArray();
                    testArr = fileReader.readHouseLayoutFile();
                    System.out.println("Sucessfully got the default house layout");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Gotta put this in a try/catch block due to file handling
                myhouse = new HouseLayout("Default House", arrayBounds); // Populate the HouseLayout with data from the default file

                MainHouseLayoutGUI MainHouselayout = new MainHouseLayoutGUI(myhouse,arrayValues, testArr);  // mh create a house layout object
                MainHouselayout.DisplayHouseLayout(MainHouselayout);       // mh Display the MainHouseLayout

            }
        });  //mh react to the Start button click

        // Alignment/layout stuff to center the logo and text
        namesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        splashScreenFrame.getContentPane().add(namesLabel, BorderLayout.SOUTH);
        namesLabel.setHorizontalTextPosition(JLabel.CENTER);
        namesLabel.setVerticalTextPosition(JLabel.BOTTOM);

        splashScreenFrame.add(namesLabel); // Add our JLabel to the window
        splashScreenFrame.add(startButton, BorderLayout.SOUTH); // mh add button to splash screen

        //Display the window.
        splashScreenFrame.setSize(960, 540);
        splashScreenFrame.setVisible(true);

    }

    public static void main(String[] args) throws IOException {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

        // Added by Guess Crow
        // Sets look and feel to match Windows' default UI
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
