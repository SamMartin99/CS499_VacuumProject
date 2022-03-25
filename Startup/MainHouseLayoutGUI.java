package Startup;

// Class to provide the scaffolding for the HouseLayout
// West side is tile
// East side is components
// South is file handling and toggle running the simulation

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


/**
 * Imports for using java's swing GUI functionality
 * @parm
 *
 */
public class MainHouseLayoutGUI {
    // Attributes
    JButton saveHouseLayoutButton = new JButton("Save");
    JButton loadHouseLayoutButton = new JButton("Load");
    JButton runSimulationButton = new JButton("Run");
    JButton stopSimulationButton = new JButton("Stop");
    JPanel houseTile = new JPanel();
    JPanel houseLayout = new JPanel();
    JPanel houseCenter = new JPanel();
    JPanel houseActions = new JPanel();
    JPanel fileHandling = new JPanel();
    JPanel simulationActions = new JPanel();
    JFrame MainHouseLayoutFrame = new JFrame("House Layout");
    JTextArea tileText = new JTextArea("this is a tile");
    JButton layoutbtn = new JButton();

    /**
     * Default constructor
     * @parm
     *
     */
    MainHouseLayoutGUI(){
// set up boarders
        Border houseTileBorder , houseLayoutBorder, houseActionsBorder, houseFileHandlingBorder, houseSimulationBorder ;
        houseTileBorder = BorderFactory.createTitledBorder("House Tiles");
        houseLayoutBorder = BorderFactory.createTitledBorder("House Components");
        houseActionsBorder = BorderFactory.createTitledBorder("Actions");
        houseFileHandlingBorder = BorderFactory.createTitledBorder("File Handling");
        houseSimulationBorder = BorderFactory.createTitledBorder("Simulation");

// Add components to houseTile Panel
            this.houseTile.add(tileText);
            this.houseTile.setBorder(houseTileBorder);

            // Add components to HouseLayout Panel

            this.houseLayout.setBorder(houseLayoutBorder);
            this.houseLayout.add(layoutbtn);

            // Add components to Action Panel
            this.houseActions.setBorder(houseActionsBorder);
            this.houseActions.add(fileHandling,BorderLayout.WEST);
            this.fileHandling.setBorder(houseFileHandlingBorder);
            this.fileHandling.add(saveHouseLayoutButton);
            this.fileHandling.add(loadHouseLayoutButton);
            this.houseActions.add(simulationActions,BorderLayout.EAST);
            this.simulationActions.setBorder(houseSimulationBorder);
            this.simulationActions.add(runSimulationButton);
            this.simulationActions.add(stopSimulationButton);


            // Add panels to the Frame

            this.MainHouseLayoutFrame.add(houseTile,BorderLayout.WEST);
            this.houseTile.setPreferredSize(new Dimension(380, 500));
            this.MainHouseLayoutFrame.add(houseCenter,BorderLayout.CENTER);
         //   this.houseCenter.setPreferredSize(new Dimension(10, 500));
            this.MainHouseLayoutFrame.setPreferredSize(new Dimension(330, 500));
            this.MainHouseLayoutFrame.add(houseLayout,BorderLayout.EAST);
            this.houseLayout.setPreferredSize(new Dimension(400, 500));
            this.MainHouseLayoutFrame.add(houseActions,BorderLayout.SOUTH);


    }


    /**
     * Display the Main House Layout Screen
     * @parm
     * @return
     *
     */
    public void DisplayHouseLayout(MainHouseLayoutGUI inpHouseLayoutGUI) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayHouseLayout(MainHouseLayoutFrame);
            }
        });

    }


    static void displayHouseLayout(JFrame inpFrame){
        inpFrame.setPreferredSize(new Dimension(800,600));
        inpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        inpFrame.pack();
        inpFrame.setVisible(true);
    }


}

