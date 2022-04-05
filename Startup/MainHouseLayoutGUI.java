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
import Model.RunSimulation;
import Model.TileArray;
import Model.Tile;

import static java.lang.System.*;

/**
 * C
 * @parm
 *
 */
public class MainHouseLayoutGUI {
    // Startup.houseTile[][] cells;

    // The algorithm being chosen by the user.
    // 1 = Random
    // 2 = Spiral
    // 3 = Snaking
    // 4 = Wall Follow
    private int a = 1;
    /* The tile array. I'm defaulting to 10x10 for the size, for now.
     *
     * NOTE FROM BRYANT: No idea how we're supposed to actually get the type
     * from the buttons. We can set the type of buttons individually, but there needs
     * to be a way to get the type BACK from those buttons, so the tile array can be
     * prepared for the RunSimulation class. Otherwise there's no actual way to
     * work with the user input.
     */
    TileArray TA = new TileArray(10, 10);

    // Attributes
    JButton newHouseLayoutButton = new JButton("New HouseLayout");
    JButton saveHouseLayoutButton = new JButton("Save");
    JButton loadHouseLayoutButton = new JButton("Load");
    JButton runSimulationButton = new JButton("Run");
    JButton stopSimulationButton = new JButton("Stop");
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
    JButton Chairbtn = new JButton("Chair");
    JButton Tablebtn = new JButton("Table");
    JButton Chestbtn = new JButton("Chest");
    JButton ShagFloorbtn = new JButton("Shag");
    JButton HardwoodFloorbtn = new JButton("Hardwood");
    JButton LoopPilebtn = new JButton("LoopPile");
    JButton CutPilebtn = new JButton("CutPile");
    JButton randomPath = new JButton("Random");
    JButton spiralPath = new JButton("Spiral");
    JButton snakePath = new JButton("Snake");
    JButton wallFollowPath = new JButton("Wall Follow");
    JLabel simSpeed = new JLabel("Speed");
    JSlider simSpeedSlider = new JSlider(0,50);
    JSeparator houseHeaderTilesSeparator = new JSeparator();

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
     * @parm
     *
     * @param
     */
    <tileType> MainHouseLayoutGUI(HouseLayout inpHouseLayout){
        // set up borders definition
        Border houseTileBorder , houseLayoutBorder, houseActionsBorder, houseFileHandlingBorder, houseSimulationBorder,
               LayoutWallDoorwayBorder, LayoutFurnitureBorder, LayoutFloorsBorder, LayoutPathBorder, LayoutSimulationBorder, menuBorder ;
        houseTileBorder = BorderFactory.createTitledBorder("House Tiles");
        String houseLayoutName = "Testing";
        houseLayoutBorder = BorderFactory.createTitledBorder("Layout Name: "+ houseLayoutName);
        houseActionsBorder = BorderFactory.createTitledBorder("Actions");
        houseFileHandlingBorder = BorderFactory.createTitledBorder("File Handling");
        houseSimulationBorder = BorderFactory.createTitledBorder("Simulation");
        // menuBorder = BorderFactory.createTitledBorder("CleanBuddy");
        LayoutWallDoorwayBorder = BorderFactory.createTitledBorder("Layout");
        // LayoutWallDoorwayBorder = BorderFactory.createLineBorder(Color.blue,1);
        LayoutFurnitureBorder = BorderFactory.createTitledBorder("Furniture");
        LayoutFloorsBorder = BorderFactory.createTitledBorder("Floors");
        LayoutPathBorder = BorderFactory.createTitledBorder("Vacuum Cleaning Algorithms");
        LayoutSimulationBorder = BorderFactory.createTitledBorder("Simulation");

        // Add components to houseTile Panel
        houseTile.setBorder(houseLayoutBorder);
        // trying to set the panel to a square
        int sideLength = houseTile.getWidth() < houseTile.getHeight() ? houseTile.getHeight() : houseTile.getWidth();
        houseTile.setSize(sideLength,sideLength);
        houseTile.setLayout(gblHouseTilesLayout);
        // gblHouseTilesLayoutConstraints.fill = GridBagConstraints.VERTICAL;
        gblHouseTilesLayoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        gblHouseTilesLayoutConstraints.gridwidth = sideLength;
        gblHouseTilesLayoutConstraints.gridheight = sideLength;
        gblHouseTilesLayoutConstraints.weightx = 0;
        gblHouseTilesLayoutConstraints.weighty = 10;
        // gblHouseTilesLayoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        // houseTileHeader.setPreferredSize(new Dimension(300, 30));
        // houseTile.add(houseTileHeader,BorderLayout.NORTH);
        // houseTile.add(houseTileHeader,gblHouseTilesLayoutConstraints); //
        // gblHouseTilesLayoutConstraints.weightx = 0;
        // gblHouseTilesLayoutConstraints.weighty = 20;
        // houseHeaderTilesSeperator.setOrientation(SwingConstants.HORIZONTAL);
        // houseHeaderTilesSeperator.setPreferredSize(new Dimension(30, 30));
        // houseTile.add(houseHeaderTilesSeperator,gblHouseTilesLayoutConstraints);
        // gblHouseTilesLayoutConstraints.weightx = 0;
        // gblHouseTilesLayoutConstraints.weighty = 30;
        // houseTile.add(houseTileIndividualTiles, BorderLayout.CENTER);
        houseTile.add(houseTileIndividualTiles,gblHouseTilesLayoutConstraints); // ,BorderLayout.CENTER) ;

        // houseTileHeader.add(tileText);
        // houseTileHeader.setBorder(houseTileBorder);

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
        LayoutSimualtion.add(simSpeed);
        simSpeedSlider.setPaintLabels(true);
        simSpeedSlider.setPaintTicks(true);
        simSpeedSlider.setPaintTrack(true);
        simSpeedSlider.setMajorTickSpacing(10);
        simSpeedSlider.setMinorTickSpacing(1);
        LayoutSimualtion.add(simSpeedSlider);
        // Just in case we want to add run / stop simulation buttons to the simulation panel
        // LayoutSimulation.add(runSimulationButton);
        // LayoutSimulation.add(stopSimulationButton);

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

        // House Tiles - East side
        int tileRow = 0;
        int tileColumn = 0;
        int maxTitleRow = 10;
        int maxTitleColumn = 10;
        String tileName = "";
        // houseTileIndividualTiles.setLayout(gblHouseTile);
        houseTileIndividualTiles.setLayout(gblHouseTilesLayout);
        GridBagConstraints gblHouseTileConstraints = new GridBagConstraints();
        gblHouseTileConstraints.weightx = 1;
        gblHouseLayoutConstraints.weighty = 1;
        houseTile tileButton;

        for (tileRow = 0; tileRow < maxTitleRow; tileRow ++){
            for (tileColumn = 0; tileColumn < maxTitleColumn; tileColumn ++){
                tileName = "Tile" + tileRow + tileColumn;
                gblHouseTileConstraints.gridx = tileColumn;
                gblHouseTileConstraints.gridy = tileRow;

                tileButton = new houseTile(tileRow,tileColumn);
                // mh not sure why we need this; ide added in order to run
                Startup.houseTile finalTileButton = tileButton;
                // tileButton.addActionListener(e -> finalTileButton.printTile(inpHouseLayout));
                tileButton.addActionListener(e -> finalTileButton.clickTileAction(inpHouseLayout));

                houseTileIndividualTiles.add(tileButton,gblHouseTileConstraints);
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
        simulationActions.add(stopSimulationButton);

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

        Chairbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Chair button in Components Furniture was clicked");
                inpHouseLayout.chairClick();
                inpHouseLayout.getlayoutType();
            }
        });

        Tablebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Table button in Components Furniture was clicked");
                inpHouseLayout.tableClick();
                inpHouseLayout.getlayoutType();
            }
        });

        Chestbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Chair button in Components Furniture was clicked");
                inpHouseLayout.chestClick();
                inpHouseLayout.getlayoutType();
            }
        });

        ShagFloorbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Shag button in Components Floor was clicked");
                inpHouseLayout.shagClick();
                inpHouseLayout.getlayoutType();
            }
        });

        HardwoodFloorbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("HardwoodFloor button in Components Floor was clicked");
                inpHouseLayout.hardwoodClick();
                inpHouseLayout.getlayoutType();
            }
        });

        LoopPilebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Loop Pile button in Components Floor was clicked");
                inpHouseLayout.loopPileClick();
                inpHouseLayout.getlayoutType();
            }
        });

        CutPilebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Cut Pile button in Components Floor was clicked");
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

        newHouseLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String layoutName = "new";
                HouseLayout myHouse = new HouseLayout(layoutName);
            }
        });

        loadHouseLayoutButton.addActionListener(new ActionListener() {
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

        saveHouseLayoutButton.addActionListener(new ActionListener() {
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
         * t = time
         * rs = run speed
         * b = battery life
         * vs = vacuum speed
         * ft = floor type
         * TA = the tile array the run simulation works with
         */
        runSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t = 1;
                int rs = 1;
                int b = 1;
                int vs = 1;
                int ft = inpHouseLayout.getFloorType();

                RunSimulation RS = new RunSimulation (t, rs, a, ft, b, vs, TA);
                RS.run();
                // out.println("Run Simulation button on House Layout was clicked");
            }
        });

        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("Stop Simulation button on House Layout was clicked");
            }
        });

        // Add panels to the Frame
        this.MainHouseLayoutFrame.add(houseTile,BorderLayout.WEST);
        this.houseTile.setPreferredSize(new Dimension(380, 500));
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

