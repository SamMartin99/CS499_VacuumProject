package Startup;
import java.io.*;
import java.util.Scanner;

public class FileHandling {
    private String houseFileName = "houseLayout.txt";
    public File houseFile = new File(houseFileName);

    public Scanner openHouseFile() throws FileNotFoundException {

        System.out.println("Entering the File Handling class openHouseFile method.");
        // Open the file

        Scanner fileHouseReader = new Scanner(new File(houseFileName));

        System.out.println("Exiting the File Handling class openHouseFile method.");

        return fileHouseReader;

    }
    
    public String [] readHouseFile(Scanner houseFile){

        String[] houseData = new String[100];
        String dataLine;
        houseData[0] = " ";
        int lineNumber = 0;

        System.out.println("Entering the File Handling class readHouseFile method.");

        // Iterate through the file until "filename = " is found
        while (houseFile.hasNextLine())
        {

            houseData[lineNumber] = houseFile.nextLine();

            System.out.println(houseData[lineNumber]);

            lineNumber ++;
        }
        
        System.out.println("Exiting the File Handling class readHouseFile method.");
        
        return houseData;
    }
}
