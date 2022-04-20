package View;

// Authors: Guess Crow, Marie Held, Bryant Terry, Sam Martin
// Class: CS 499 Senior Design
// Project: Vacuum Robot Simulator
// Purpose: This class creates all the basic GUI for the main project window, excluding the house editor view

// Class to provide the scaffolding for the HouseLayout
// West side is tile
// East side is components
// South is file handling and toggle running the simulation

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import Model.RunSimulation;
import Model.TileArray;
import Startup.HouseLayout;
import Startup.HouseLayoutFileHandling;
import Startup.Location;
import Startup.houseTile;
import Startup.staticVariable;
import static java.lang.System.*;

/**
 * Purpose: To allow the user to customize the house layout
 * Author: Guess Crow, Marie Held
 *
 */

public class MainHouseLayoutGUI {

    // The algorithm being chosen by the user.
    // 1 = Random
    // 2 = Spiral
    // 3 = Snaking
    // 4 = Wall Follow
    private int a = 1;
    private int rs = 25;  // Run speed


    // Attributes

    staticVariable arrayBounds = new staticVariable();
    // TileArray TA = new TileArray(maxRow, maxColumn);
    TileArray TA;
    // TileArray TA = new TileArray(arrayBounds);
    JButton newHouseLayoutButton = new JButton("New HouseLayout");
    JButton saveHouseLayoutButton = new JButton("Save");
    JButton loadHouseLayoutButton = new JButton("Load");
    JButton runSimulationButton = new JButton("Run");
    JButton stopSimulationButton = new JButton("Stop");
 //   JButton simResults = new JButton ("Previous Simulation Results");
    JPanel houseTile = new JPanel();
    JPanel houseTileHeader = new JPanel();
    JPanel houseTileIndividualTiles = new JPanel();
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
    JButton Clearbtn = new JButton("Clear");
    JButton ClearAllbtn = new JButton("Clear All");
    JButton Chairbtn = new JButton("Chair");
    JButton Tablebtn = new JButton("Table");
    JButton Chestbtn = new JButton("Chest");
    JButton Vacuumbtn = new JButton("Vacuum");

    JButton ShagFloorbtn = new JButton("Shag");
    JButton HardwoodFloorbtn = new JButton("Hardwood");
    JButton LoopPilebtn = new JButton("LoopPile");
    JButton CutPilebtn = new JButton("CutPile");

    JButton randomPath = new JButton("Random");
    JButton spiralPath = new JButton("Spiral");
    JButton snakePath = new JButton("Snake");
    JButton wallFollowPath = new JButton("Wall Follow");

    // JLabel simSpeedLabel = new JLabel("Speed");
    JRadioButton simSpeed1 = new JRadioButton("1");
    JRadioButton simSpeed5 = new JRadioButton("5");
    JRadioButton simSpeed50 = new JRadioButton("50");


    // In House Layout Panel used the grid layout
    GridBagLayout gblHouseLayout = new GridBagLayout();
    GridBagConstraints gblHouseLayoutConstraints = new GridBagConstraints();

    // Use Grid for House Tiles Panel
    GridBagLayout gblHouseTilesLayout = new GridBagLayout();
    GridBagConstraints gblHouseTilesLayoutConstraints = new GridBagConstraints();

    // In House Tile {anel use the grid layout
    GridBagLayout gblHouseTile = new GridBagLayout();
    GridBagConstraints gblHouseTileConstraints = new GridBagConstraints();

    /**
     * Default constructor
     *
     * @param
     */
    public MainHouseLayoutGUI(HouseLayout inpHouseLayout, staticVariable inpGlobal, TileArray tileArray){
        int maxRow,maxColumn,minRow,minColumn;
        TA = tileArray;
        maxRow = TA.getLength();
        maxColumn = TA.getWidth();
        minRow = 0;
        minColumn = 0;

        /*maxRow = inpGlobal.getMaxRow();
        maxColumn = inpGlobal.getMaxColumn();
        minRow = inpGlobal.getMinRow();
        minColumn = inpGlobal.getMinColumn();*/

        // TileArray TA = new TileArray(maxRow, maxColumn);
        // TileArray TA = new TileArray(inpGlobal);
        // set up borders definition
        Border houseTileBorder , houseLayoutBorder, houseActionsBorder, houseFileHandlingBorder, houseSimulationBorder,
               LayoutWallDoorwayBorder, LayoutFurnitureBorder, LayoutFloorsBorder, LayoutPathBorder, LayoutSimulationBorder, menuBorder ;
        houseTileBorder = BorderFactory.createTitledBorder("House Tiles");
        String houseLayoutName = "Testing";
        houseLayoutBorder = BorderFactory.createTitledBorder("Layout Name: "+ houseLayoutName);
        houseActionsBorder = BorderFactory.createTitledBorder("Actions");
        houseFileHandlingBorder = BorderFactory.createTitledBorder("File Handling");
        houseSimulationBorder = BorderFactory.createTitledBorder("Simulation");
        LayoutWallDoorwayBorder = BorderFactory.createTitledBorder("Layout");
        LayoutFurnitureBorder = BorderFactory.createTitledBorder("Furniture");
        LayoutFloorsBorder = BorderFactory.createTitledBorder("Floors");
        LayoutPathBorder = BorderFactory.createTitledBorder("Vacuum Cleaning Algorithms");
        LayoutSimulationBorder = BorderFactory.createTitledBorder("Simulation Speed");

        // Add components to houseTile Panel
        houseTile.setBorder(houseLayoutBorder);
        houseTile.setLayout(gblHouseTilesLayout);

        gblHouseTilesLayoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        gblHouseTilesLayoutConstraints.weightx = 0;
        gblHouseTilesLayoutConstraints.weighty = 10;
        houseTile.add(houseTileIndividualTiles,gblHouseTilesLayoutConstraints); // ,BorderLayout.CENTER) ;

        // Add components to HouseLayout Panel
        houseLayout.setBorder(houseLayoutBorder);
        MainHouseLayoutFrame.add(houseTile,BorderLayout.WEST);

        LayoutWallDoorway.setBorder(LayoutWallDoorwayBorder);
        LayoutWallDoorway.setPreferredSize(new Dimension(300,50));
        LayoutWallDoorway.add(Wallbtn);
        LayoutWallDoorway.add(Doorwaybtn);
        LayoutWallDoorway.add(Vacuumbtn);

        LayoutFurniture.setBorder(LayoutFurnitureBorder);
        LayoutFurniture.setPreferredSize(new Dimension(300,50));
        LayoutFurniture.add(Clearbtn);
        LayoutFurniture.add(ClearAllbtn);
        LayoutFurniture.add(Chairbtn) ;
        LayoutFurniture.add(Tablebtn);
        LayoutFurniture.add(Chestbtn);

        LayoutFloors.setBorder(LayoutFloorsBorder);
        LayoutFloors.setPreferredSize(new Dimension(300,50 ));
        LayoutFloors.add(ShagFloorbtn);
        LayoutFloors.add(HardwoodFloorbtn);
        LayoutFloors.add(LoopPilebtn);
        LayoutFloors.add(CutPilebtn);

        LayoutPaths.setBorder(LayoutPathBorder);
        LayoutPaths.setPreferredSize(new Dimension(300,50));
        LayoutPaths.add(randomPath);
        LayoutPaths.add(spiralPath);
        LayoutPaths.add(snakePath);
        LayoutPaths.add(wallFollowPath);

        LayoutSimualtion.setBorder(LayoutSimulationBorder);
        LayoutSimualtion.setPreferredSize(new Dimension(400,50));

        LayoutSimualtion.add(simSpeed1);
        LayoutSimualtion.add(simSpeed5);
        LayoutSimualtion.add(simSpeed50);

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

        // House Tiles - West side
        int tileRow = 0;
        int tileColumn = 0;
        String tileName = "";
        // houseTileIndividualTiles.setLayout(gblHouseTile);
        houseTileIndividualTiles.setLayout(gblHouseTilesLayout);
        GridBagConstraints gblHouseTileConstraints = new GridBagConstraints();
        gblHouseTileConstraints.weightx = 1;
        gblHouseLayoutConstraints.weighty = 1;
        Startup.houseTile tileButton;

        houseTile[][] houseTileArr = new houseTile[maxRow][maxColumn]; // This is an array to hold our houseTiles for the Table to access, GUESS WROTE THIS
        // for (tileRow = 0; tileRow < maxTitleRow; tileRow ++){
        for (tileRow = minRow; tileRow < maxRow; tileRow ++){
            for (tileColumn = minColumn; tileColumn < maxColumn; tileColumn ++){
                tileName = "Tile" + tileRow + tileColumn;
                gblHouseTileConstraints.gridx = tileColumn;
                gblHouseTileConstraints.gridy = tileRow;

                Location l = new Location(tileRow, tileColumn);
                tileButton = new houseTile(l, TA.getTile(tileRow, tileColumn), houseTileArr);
                houseTileArr[tileRow][tileColumn] = tileButton;
                tileButton.setImageIcon();

               // mh not sure why we need this; ide added in order to run
                Startup.houseTile finalTileButton = tileButton;
                // tileButton.addActionListener(e -> finalTileButton.printTile(inpHouseLayout));
                tileButton.addActionListener(e -> finalTileButton.clickTileAction(inpHouseLayout,TA));

                houseTileIndividualTiles.add(tileButton,gblHouseTileConstraints);

                // first row is a north border wall so nothing can be placed on it
                if (tileRow == 0) {
                    tileButton.setTileAvailable(false);
                    TA.setTile(tileRow,tileColumn,3);
                    try {
                        ImageIcon icon = new ImageIcon("wallTile.png");
                        tileButton.setIcon(icon);
                        tileButton.setMargin(new Insets(0, 0, 0, 0));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                // first column is a west border wall so nothing can be placed on it
                if (tileColumn == 0) {
                    tileButton.setTileAvailable(false);
                    TA.setTile(tileRow,tileColumn,3);
                    try {
                        ImageIcon icon = new ImageIcon("wallTile.png");
                        tileButton.setIcon(icon);
                        tileButton.setMargin(new Insets(0, 0, 0, 0));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }  // last row is a south border wall
                if (tileRow == (maxRow -1)) {
                    tileButton.setTileAvailable(false);
                    TA.setTile(tileRow,tileColumn,3);
                    try {
                        ImageIcon icon = new ImageIcon("wallTile.png");
                        tileButton.setIcon(icon);
                        tileButton.setMargin(new Insets(0, 0, 0, 0));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } // first column is an east border wall
                if (tileColumn == (maxRow -1 )) {
                    tileButton.setTileAvailable(false);
                    TA.setTile(tileRow,tileColumn,3);
                    try {
                        ImageIcon icon = new ImageIcon("wallTile.png");
                        tileButton.setIcon(icon);
                        tileButton.setMargin(new Insets(0, 0, 0, 0));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

        }

        // Add components to Action Panel
        houseActions.setBorder(houseActionsBorder);

        houseActions.add(fileHandling,BorderLayout.WEST);
        fileHandling.setBorder(houseFileHandlingBorder);
        fileHandling.add(newHouseLayoutButton);
        fileHandling.add(saveHouseLayoutButton);
        fileHandling.add(loadHouseLayoutButton);
        houseActions.add(simulationActions,BorderLayout.EAST);
        simulationActions.setBorder(houseSimulationBorder);
        simulationActions.add(runSimulationButton);
    //    simulationActions.add(simResults);
  //      simulationActions.add(stopSimulationButton);

        // set actions for when buttons are clicked

        Wallbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   out.println("Wall button in Components Layout was clicked");
                inpHouseLayout.wallClick();
                inpHouseLayout.getlayoutType();
              }
        });

        Doorwaybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   out.println("Doorway button in Components Layout was clicked");
                inpHouseLayout.doorClick();
                inpHouseLayout.getlayoutType();
            }
        });

        Vacuumbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   out.println("Wall button in Components Layout was clicked");
                inpHouseLayout.vacuumClick();
                inpHouseLayout.getlayoutType();
            }
        });

        Clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //          out.println("Chair button in Components Furniture was clicked");
                inpHouseLayout.clearClick();
                inpHouseLayout.getlayoutType();
            }
        });

        ClearAllbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //          out.println("Chair button in Components Furniture was clicked");
                inpHouseLayout.clearClick();
                inpHouseLayout.getlayoutType();
            }
        });

        Chairbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
      //          out.println("Chair button in Components Furniture was clicked");
                inpHouseLayout.chairClick();
                inpHouseLayout.getlayoutType();
            }
        });

        Tablebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
     //           out.println("Table button in Components Furniture was clicked");
                inpHouseLayout.tableClick();
                inpHouseLayout.getlayoutType();
            }
        });

        Chestbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // out.println("Chair button in Components Furniture was clicked");
                inpHouseLayout.chestClick();
                inpHouseLayout.getlayoutType();
            }
        });

        ShagFloorbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // out.println("Shag button in Components Floor was clicked");
                inpHouseLayout.shagClick();
                inpHouseLayout.getlayoutType();
            }
        });

        HardwoodFloorbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // out.println("HardwoodFloor button in Components Floor was clicked");
                inpHouseLayout.hardwoodClick();
                inpHouseLayout.getlayoutType();
            }
        });

        LoopPilebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // out.println("Loop Pile button in Components Floor was clicked");
                inpHouseLayout.loopPileClick();
                inpHouseLayout.getlayoutType();
            }
        });

        CutPilebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // out.println("Cut Pile button in Components Floor was clicked");
                inpHouseLayout.cutPileClick();
                inpHouseLayout.getlayoutType();
            }
        });

        randomPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 1;
                // out.println("Random Path button in Vacuum Algorithms was clicked");
            }
        });

        spiralPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 2;
                // out.println("Spiral Path button in Vacuum Algorithms was clicked");
            }
        });

        snakePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 3;
                // out.println("Snake Path button in Vacuum Algorithms was clicked");
            }
        });

        wallFollowPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 4;
                // out.println("Wall Follow Path button in Vacuum Algorithms was clicked");
            }
        });

        /**
         * Purpose: To retrieve the value for the simulation speed
         */
        simSpeed1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simSpeed5.setSelected(false);
                simSpeed50.setSelected(false);
                rs = 1;
 //               System.out.println("The simulation speed is 1");
            }
        });

        simSpeed5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simSpeed1.setSelected(false);
                simSpeed50.setSelected(false);
                rs = 5;
 //               System.out.println("The simulation speed is 5");
            }
        });

        simSpeed50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simSpeed1.setSelected(false);
                simSpeed5.setSelected(false);
                rs = 50;
  //              System.out.println("The simulation speed is 50");
            }
        });

        newHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String layoutName = "new";
                HouseLayout myHouse = new HouseLayout(layoutName, arrayBounds); // TEMPORARILY COMMENTED OUT
            }
        });

        saveHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Save button on House Layout was clicked");
                HouseLayoutFileHandling houseLayoutFile = new HouseLayoutFileHandling();
                String houseLayoutDetails;
                try {
                    houseLayoutFile.open();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Read the file and print the contents
                houseLayoutDetails = houseLayoutFile.read();
                out.println(houseLayoutDetails);

                try {
                    houseLayoutFile.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        loadHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Load button on House Layout was clicked");
            }
        });

        saveHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Save button on House Layout was clicked");
            }
        });

        /* Purpose: When the Run Simulation button is clicked, data is gathered in order
         * to pass to RunSimulation class.
         * rs = run speed
         * b = battery life
         * vs = vacuum speed
         * ft = floor type
         * TA = the tile array the run simulation works with
         */
        runSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int batteryLife = 150;
                int vs = 3;
                int ft = inpHouseLayout.getFloorType();

                // System.out.println("Simulation Speed is: " + rs);
                // if (a == 1) {System.out.println("The pathing algorithm is: Random");}
                // if (a == 2) {System.out.println("The pathing algorithm is: Spiral");}
                // if (a == 3) {System.out.println("The pathing algorithm is: Snake");}
                // if (a == 4) {System.out.println("The pathing algorithm is: Wall Follow");}
                // System.out.println("Floor type is: " + inpHouseLayout.getFloorTypeName());
                // System.out.println("Battery Life " + batteryLife);
                // System.out.println("The number of inches the vacuum covers in a second is " + vs);
                // TA.printTileArray();
                //TA.vacuumStartLoc.printLocation();
                RunSimulation RS = new RunSimulation (rs, a, ft, batteryLife, vs, TA, TA.vacuumStartLoc,inpGlobal);
                // RS.printSimValues();
                RS.run();
                // out.println("Run Simulation button on House Layout was clicked");
            }
        });

   //     stopSimulationButton.addActionListener(new ActionListener() {
   //         @Override
   //         public void actionPerformed(ActionEvent e) {
   //             out.println("Stop Simulation button on House Layout was clicked");
   //         }
   //     });

    //    simResults.addActionListener(new ActionListener() {
     //       @Override
     //       public void actionPerformed(ActionEvent e) {
      //          inpHouseLayout.showSimResults();
      //      }
      //  });

        // Add panels to the Frame
        this.MainHouseLayoutFrame.add(houseTile,BorderLayout.WEST);
        this.houseTile.setPreferredSize(new Dimension(460, 460));
        this.MainHouseLayoutFrame.add(houseCenter,BorderLayout.CENTER);
        // this.houseCenter.setPreferredSize(new Dimension(10, 500));
        this.MainHouseLayoutFrame.setPreferredSize(new Dimension(330, 500));
        this.MainHouseLayoutFrame.add(houseLayout,BorderLayout.EAST);
        this.houseLayout.setPreferredSize(new Dimension(400, 500));
        this.MainHouseLayoutFrame.add(houseActions,BorderLayout.SOUTH);

    }

    /**
     * Display the Main House Layout Screen
     * @parm
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
    // Set the values


    static void displayHouseLayout(JFrame inpFrame){
        inpFrame.setPreferredSize(new Dimension(900,600));
        inpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        inpFrame.pack();
        inpFrame.setVisible(true);
    }


}

