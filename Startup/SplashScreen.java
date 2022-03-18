package Startup;// This is a test program for using java and GitHub for CS 499, team 6:
// Guess Crow
// Marie Held
// Samuel Martin
// Bryant Terry

// Program function: Display the project logo, followed by a team member's name.
// Program purpose: To test GitHub functionality

// Imports for using java's swing GUI functionality

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;




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

    public static void main(String[] args) throws IOException {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

        // create a new House Layout
        // This is just test code need to remove at some point
//        int index1, index2;
 //       String layoutName = "marie";
//        HouseLayout myHouse = new HouseLayout(layoutName);

//        System.out.println(myHouse.getLayoutName());
//        System.out.println(myHouse.getTileArray());
//        System.out.println(myHouse.getFloorType());

 //       myHouse.setLayoutName("Hello World");
//        System.out.println("The house layout name is: " + myHouse.getLayoutName());
//        int dim1 = 100;
//        int dim2 = 100;

//        int[][] myTileArray = new int[dim1][dim2];
//        for (index1 =0; index1 < dim1; index1++) {
//            for (index2 = 1; index2 < dim2; index2++) {
//                myTileArray[index1][index2] = 1;
//            }
//        }
//         myHouse.setTileArray(myTileArray);
//         myTileArray=myHouse.getTileArray();
//         System.out.println("The tile array is: ");
//         for ( index1 =0; index1 < dim1; index1++) {
//             for (index2 = 1; index2 < dim2; index2++) {
//                 System.out.print(myTileArray[index1][index2] + " ");
//             }
 //            System.out.println();
 //        }

//         myHouse.setFloorType(2);
//         System.out.println("The floor type is: " + myHouse.getFloorType());



        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

        // Create the new houseLayout Object
        HouseLayoutFileHandling houseLayout = new HouseLayoutFileHandling();
        // Open the HouseLayoutFile
        Scanner HouseLayoutFile;
        HouseLayoutFile = houseLayout.openHouseLayoutFile();

        // Read the HouseLayoutFile
        String houseLayoutData = "";
        houseLayoutData= houseLayout.readHouseLayout(HouseLayoutFile);
        System.out.println(houseLayoutData);


    }

}
