package Startup;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

// This class handles the House Layout file handling functions
// Reading the house layout
// Saving the house layout

public class HouseLayoutFileHandling extends JFrame { // implements ActionListener{
    // File Attributes
    private File fileHouseLayout;
    private Scanner fileHouseReader;
    private String houseFileName;

    // a default constructor
    HouseLayoutFileHandling()
    {
        fileHouseLayout = null;
    }

    // Methods

    // Creates a Windows file manager dialog for user to choose a house layout
    public Scanner openHouseLayoutFile() throws IOException {

        // create an object of JFileChooser class
        // By default goes to the user's default directory
        JFileChooser jfcOpen = new JFileChooser();
        // invoke the showsSaveDialog function to show the save dialog
        int r = jfcOpen.showOpenDialog(null);

        //if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            //set the houseFileName to the path of the selected file
            this.houseFileName = jfcOpen.getSelectedFile().getAbsolutePath();
        }

        // Open the file

        Scanner fileHouseLayout = new Scanner(new File(houseFileName));

     //   System.out.println("Exiting the File Handling class openHouseFile method.");

       return fileHouseLayout;

    }

 //   public HouseLayout readHouseLayout(Scanner inpfilehouseLayout){  // in final product
    public String readHouseLayout(Scanner inpfilehouseLayout){  // for intermediate codeing only
        String tempLayoutname = "temp";
        String houseData = "";
        HouseLayout outHouseLayout = new HouseLayout(tempLayoutname);

        if (inpfilehouseLayout.hasNext()) // check to see if data is available
        {
            houseData = inpfilehouseLayout.nextLine();
        }
        return houseData;
 //       return outHouseLayout;
    }

    public int writeHouseLayout(Scanner inpfilehouseLayout, HouseLayout inpHouseLayout){  // in final product
        int writeStatus = 0; // 0 indicates a failure ; 1 is success

        inpfilehouseLayout.

        if (inpfilehouseLayout.hasNext()) // check to see if data is available
        {
            houseData = inpfilehouseLayout.nextLine();
        }
        return houseData;
        return writeStatus;
    }
}
