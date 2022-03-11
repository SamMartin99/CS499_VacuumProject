package Startup;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.jar.JarFile;

public class FileHandling extends JFrame implements ActionListener{
    private String houseFileName = "houseLayout.txt";
    public File houseFile = new File(houseFileName);

    //JLable to show the files user selects
    static JLabel lbFileName;

    // a default constructor
    FileHandling()
    {

    }

    public Scanner openHouseFile() throws IOException {

        System.out.println("Entering the File Handling class openHouseFile method.");
        // frame to contains GUI elements
        JFrame fileFrame = new JFrame("Choose the ini file");

        // set the size of the frame
        fileFrame.setSize(400,400);

        // set the frame's visibility
        fileFrame.setVisible(true);

        fileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // button to open the save dialog for HouseLayout
        JButton saveHouseLayout = new JButton("Save");

        // button to open the dialog for choosing HouseLayout function
        JButton openHouseLayout = new JButton("Choose file for house layout");

        // make an object of the class filechooser

        FileHandling f1 = new FileHandling();

        // add active listener to the buttons to capture user response on buttons
        saveHouseLayout.addActionListener(f1);
        openHouseLayout.addActionListener(f1);

        //make a panel to add the buttons and lables
        JPanel panelOpenHouse = new JPanel();

        // add buttons to the frame
        panelOpenHouse.add(openHouseLayout);
        panelOpenHouse.add(saveHouseLayout);

        // set the label to it initial value
        lbFileName = new JLabel("no file selected");

        //add panel to the frame
        panelOpenHouse.add(lbFileName);
        fileFrame.add(panelOpenHouse);

        fileFrame.setVisible(true);



        //j.showDialog(null);
        // Bring up the windows file manager
        // Runtime.getRuntime().exec("explorer.exe .");

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

    public void actionPerformed(ActionEvent evt){
        // if the user presses the save button show the save dialog
        String com = evt.getActionCommand();

        if (com.equals("save")){
            // create an object of JFileChooser class
            JFileChooser jfcSave = new JFileChooser(FileSystemView.getFileSystemView());
            // invoke the showsSaveDialog function to show the save dialog
            int r = jfcSave.showSaveDialog(null);

            //if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION){
                //set the label to the path of the selected file
                lbFileName.setText(jfcSave.getSelectedFile().getAbsolutePath());
            }
            // if the user cancels the operation
            else
                lbFileName.setText("The user cancelled the operation");
        }
        // user presses the open dialog show the open dialog
        else {
            // create an object of JFileChooser class
            JFileChooser jfcSave = new JFileChooser(FileSystemView.getFileSystemView());
            // invoke the showsSaveDialog function to show the save dialog
            int r = jfcSave.showOpenDialog(null);

            //if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION){
                //set the label to the path of the selected file
                lbFileName.setText(jfcSave.getSelectedFile().getAbsolutePath());
            }
            // if the user cancels the operation
            else
                lbFileName.setText("The user cancelled the operation");
        }

    }
}
