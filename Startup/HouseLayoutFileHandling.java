package Startup;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;

// This class handles the House Layout file handling functions
// Reading the house layout
// Saving the house layout

public class HouseLayoutFileHandling {
//public class HouseLayoutFileHandling extends JFrame { // implements ActionListener{
    // File Attributes
    private final File fileHouseLayout;
    private Scanner fileHouseReader;
    private String houseFileName;

    // a default constructor {
    public HouseLayoutFileHandling()
    {
        fileHouseLayout = null;
    }

    // Methods

    // Creates a Windows file manager dialog for user to choose a house layout
    //  public Scanner openHouseLayoutFile(Scanner houseLayoutFile) throws IOException {
    public void open() throws IOException
    {
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
        this.fileHouseReader = fileHouseLayout;
    }

    public void close() throws IOException {
        // Close the file
        this.fileHouseReader.close();
    }

    // public HouseLayout readHouseLayout(Scanner inpfilehouseLayout){  // in final product
    public String read(){  // for intermediate coding only
        String tempLayoutname = "temp";
        String houseData = "";

        if (this.fileHouseReader.hasNext()) // check to see if data is available
        {
            houseData = this.fileHouseReader.nextLine();
        }
        return houseData;
        // return outHouseLayout;
    }

    public int write(HouseLayout inpHouseLayout){  // in final product
        int writeStatus = 1; // 0 indicates a success ; 1 is failure
        int dim1, dim2;
        dim1 = inpHouseLayout.getDim1();
        dim2 = inpHouseLayout.getDim2();
        int [][] TileArray = new int[dim1][dim2];
        char tileValue = ' ';
        char charFloorType = ' ';
        int intFloorType = 0;
        try {
          FileWriter fw; // opens file to append data
          fw = new FileWriter(this.fileHouseLayout,true);
          fw.append(inpHouseLayout.getLayoutName());  // write the name of the House Layout
          // need to convert TileArray to a series of characters
          TileArray = inpHouseLayout.getTileArray();
          for (int row =   0 ; row < dim1; row++) {
              for (int column = 0; column < dim2; column++) {
                  tileValue = (char) TileArray[row][column];
                  fw.append(tileValue);
              }
              fw.append(" "); // new line to separate the rows of the Tile Array
          }

           intFloorType = inpHouseLayout.getFloorType();
           charFloorType = (char)intFloorType;

           fw.append(charFloorType);
           writeStatus = 0;  // indicate write was successful
           fw.close(); // close the file for write access

        } catch (IOException e) {
            e.printStackTrace();
            writeStatus = 1; // indicates a failure
        }
        return writeStatus; // Return failure or success
    }
}
