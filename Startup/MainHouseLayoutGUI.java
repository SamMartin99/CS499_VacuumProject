package Startup;

// Class to provide the scaffolding for the HouseLayout
// West side is tile
// East side is components
// South is file handling and toggle running the simulation

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



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
    JPanel LayoutWallDoorway = new JPanel();
    JPanel LayoutFurniture = new JPanel();
    JPanel LayoutFloors = new JPanel();
    JPanel LayoutPaths = new JPanel();
    JPanel LayoutSimualtion = new JPanel();
    JFrame MainHouseLayoutFrame = new JFrame("Clean Buddy -- General View");
    JTextArea tileText = new JTextArea("Layout Name");
    JButton layoutbtn = new JButton();
    JButton Wallbtn = new JButton("Wall");
    JButton Doorwaybtn = new JButton("Doorway");
    JButton Chairbtn = new JButton("Chair");
    JButton Tablebtn = new JButton("Table");
    JButton Chestbtn = new JButton("Chest");
    JButton ShagFloor = new JButton("Shag");
    JButton HardwoodFloor = new JButton("Hardwood");
    JButton LoopPile = new JButton("LoopPile");
    JButton CutPile = new JButton("CutPile");
    JButton randomPath = new JButton("Random");
    JButton spiralPath = new JButton("Spiral");
    JButton snakePath = new JButton("Snake");
    JButton wallFollowPath = new JButton("Wall Follow");
    JLabel simSpeed = new JLabel("Speed");
    JSlider simSpeedSlider = new JSlider(0,50);

    // In House Layout Panel used the grid layout
    GridBagLayout gblHouseLayout = new GridBagLayout();
    GridBagConstraints gblHouseLayoutConstraints = new GridBagConstraints();


    /**
     * Default constructor
     * @parm
     *
     */
    MainHouseLayoutGUI(){
    // set up boarders definistion
        Border houseTileBorder , houseLayoutBorder, houseActionsBorder, houseFileHandlingBorder, houseSimulationBorder,
               LayoutWallDoorwayBorder, LayoutFurnitureBorder, LayoutFloorsBorder, LayoutPathBorder, LayoutSimulationBorder, menuBorder ;
        houseTileBorder = BorderFactory.createTitledBorder("House Tiles");
        houseLayoutBorder = BorderFactory.createTitledBorder("Components / Options");
        houseActionsBorder = BorderFactory.createTitledBorder("Actions");
        houseFileHandlingBorder = BorderFactory.createTitledBorder("File Handling");
        houseSimulationBorder = BorderFactory.createTitledBorder("Simulation");
   //     menuBorder = BorderFactory.createTitledBorder("CleanBuddy");
        LayoutWallDoorwayBorder = BorderFactory.createTitledBorder("Layout");
        //LayoutWallDoorwayBorder = BorderFactory.createLineBorder(Color.blue,1);
        LayoutFurnitureBorder = BorderFactory.createTitledBorder("Furniture");
        LayoutFloorsBorder = BorderFactory.createTitledBorder("Floors");
        LayoutPathBorder = BorderFactory.createTitledBorder("Vacuum Cleaning Algorithms");
        LayoutSimulationBorder = BorderFactory.createTitledBorder("Simulation");




        // Add components to houseTile Panel
            this.houseTile.add(tileText);
            this.houseTile.setBorder(houseTileBorder);

        // Add components to HouseLayout Panel
        houseLayout.setBorder(houseLayoutBorder);
        MainHouseLayoutFrame.add(houseTile,BorderLayout.WEST);

        LayoutWallDoorway.setBorder(LayoutWallDoorwayBorder);
        LayoutWallDoorway.setPreferredSize(new Dimension(300,50));
        LayoutWallDoorway.add(Wallbtn);
        LayoutWallDoorway.add(Doorwaybtn);

        LayoutFurniture.setBorder(LayoutFurnitureBorder);
        LayoutFurniture.setPreferredSize(new Dimension(300,50));
        LayoutFurniture.add(Chairbtn) ;
        LayoutFurniture.add(Tablebtn);
        LayoutFurniture.add(Chestbtn);

        LayoutFloors.setBorder(LayoutFloorsBorder);
        LayoutFloors.setPreferredSize(new Dimension(300,50 ));
        LayoutFloors.add(ShagFloor);
        LayoutFloors.add(HardwoodFloor);
        LayoutFloors.add(LoopPile);
        LayoutFloors.add(CutPile);

        LayoutPaths.setBorder(LayoutPathBorder);
        LayoutPaths.setPreferredSize(new Dimension(300,50));
        LayoutPaths.add(randomPath);
        LayoutPaths.add(spiralPath);
        LayoutPaths.add(snakePath);
        LayoutPaths.add(wallFollowPath);

        LayoutSimualtion.setBorder(LayoutSimulationBorder);
        LayoutSimualtion.setPreferredSize(new Dimension(400,50));
        LayoutSimualtion.add(simSpeed);
        simSpeedSlider.setPaintLabels(true);
        simSpeedSlider.setPaintTicks(true);
        simSpeedSlider.setPaintTrack(true);
        simSpeedSlider.setMajorTickSpacing(10);
        simSpeedSlider.setMinorTickSpacing(1);
        LayoutSimualtion.add(simSpeedSlider);

  //      LayoutSimualtion.add(runSimulationButton);
 //       LayoutSimualtion.add(stopSimulationButton);


        houseLayout.setLayout(gblHouseLayout);


        gblHouseLayoutConstraints.weightx = 1;
        gblHouseLayoutConstraints.weighty = 1;
        gblHouseLayoutConstraints.fill = GridBagConstraints.HORIZONTAL;

        gblHouseLayoutConstraints.gridx = 0;
        gblHouseLayoutConstraints.gridy = 0;
        houseLayout.add(LayoutWallDoorway,gblHouseLayoutConstraints);

        gblHouseLayoutConstraints.gridx = 0;
        gblHouseLayoutConstraints.gridy = 5;
        houseLayout.add(LayoutFurniture,gblHouseLayoutConstraints);

        gblHouseLayoutConstraints.gridx = 0;
        gblHouseLayoutConstraints.gridy = 10;
        houseLayout.add(LayoutFloors,gblHouseLayoutConstraints);

        gblHouseLayoutConstraints.gridx = 0;
        gblHouseLayoutConstraints.gridy = 15;
        houseLayout.add(LayoutPaths,gblHouseLayoutConstraints);

        gblHouseLayoutConstraints.gridx = 0;
        gblHouseLayoutConstraints.gridy = 20;
        houseLayout.add(LayoutSimualtion ,gblHouseLayoutConstraints);


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

        // set actions for when buttons are clicked
        loadHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save button on House Layout was clicked");
                HouseLayoutFileHandling houseLayoutFile = new HouseLayoutFileHandling();
                String houseLayoutDetails;
                try {
                    houseLayoutFile.open();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Read the file and print the contents
                houseLayoutDetails = houseLayoutFile.read();
                System.out.println(houseLayoutDetails);

                try {
                    houseLayoutFile.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        saveHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load button on House Layout was clicked");


            }
        });

        saveHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save button on House Layout was clicked");
            }
        });

        runSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run Simulation button on House Layout was clicked");
            }
        });

        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Stop Simulation button on House Layout was clicked");
            }
        });


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

