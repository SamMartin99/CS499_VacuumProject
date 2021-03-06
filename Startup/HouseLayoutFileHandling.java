package Startup;
import Model.TileArray;

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
    private File fileHouseLayout;
    private Scanner fileHouseReader;
    private String houseFileName;

    // a default constructor that takes in a given file
    public HouseLayoutFileHandling(File f)
    {
        fileHouseLayout = f;
    }

    // overloaded constructor for not having a default house file
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
        this.fileHouseLayout = new File(houseFileName);
        Scanner fileHouseLayout = new Scanner(new File(houseFileName));
        this.fileHouseReader = fileHouseLayout;
    }

    public void close() throws IOException {
        // Close the file
        this.fileHouseReader.close();
    }

    // written by Guess Crow
    // Read in the data from the open house layout file, and returns it as a tile array object
    public TileArray readHouseLayoutFile() throws IOException
    {
        this.fileHouseReader = new Scanner(fileHouseLayout); // Make a new scanner with this object's file
        String houseLayoutName = this.fileHouseReader.nextLine(); // The first line contains the name
        int length = this.fileHouseReader.nextInt(); // the next line will contain the length and height of the house
        int width = this.fileHouseReader.nextInt();

        TileArray workingTA = new TileArray(length, width); // Create a new TileArray to populate with type data

        // Using for loops, i and j, to set the appropriate tile within our TileArray
        for(int i = 0; i < length; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(this.fileHouseReader.hasNextInt())
                {
                    int test = this.fileHouseReader.nextInt();
                    // System.out.print(test);
                    workingTA.setTile(i, j, test);
                }
            }
            // System.out.println();
        }
        return workingTA;
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

    // This method takes in a tile array and a file name, and writes a .txt file in the House Layout Files folder
    public int write(TileArray tileArr, String fileName){
        int writeStatus = 1; // 0 indicates a success ; 1 is failure
        int length = tileArr.getLength();
        int width = tileArr.getWidth();

        try {
            FileWriter fw = new FileWriter("House Layout Files\\" + fileName + ".txt"); // Make a new file writer and point it to the house layout files directory
            fw.write(fileName + "\n");  // write the name of the House Layout, and a new line
            fw.write(length + " "); // Write the length and width of the house
            fw.write( width + "\n");

            // Traverse the house matrix, and write its floorType values with a space between each one
            for (int row =   0 ; row < length; row++) {
                for (int column = 0; column < width; column++) {
                    int tileValue = tileArr.getTile(row, column).getType();
                    fw.write(tileValue + " ");
                }
                fw.write("\n"); // new line to separate the rows of the Tile Array
            }
            writeStatus = 0;  // indicate write was successful
            fw.close(); // close the file for write access

        } catch (IOException e) {
            e.printStackTrace();
            writeStatus = 1; // indicates a failure
        }
        return writeStatus; // Return failure or success
    }
}
