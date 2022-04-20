package View;

import Model.SimTileGUI;
import Model.Tile;
import Model.TileArray;
import Startup.Location;
import Startup.staticVariable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.text.StyleConstants.setIcon;

// public class SimulationLayoutGUI (TileArray inpTileArray) {

/**
 * Author Marie Held
 * Purpose To display the simulation gui
 */
public class SimulationLayoutGUI  {

    // Attributes
    JFrame outputMainSimFrame = new JFrame("Simulation Output");
    JPanel simTiles = new JPanel(); // Panel that holds the buttons for the array
    JPanel simActions = new JPanel();

    JButton stopSimulation = new JButton("Stop Simulation");

    Border simTilesWallBorder;
    Border simTilesChairBorder;

    private staticVariable global;
    private int simNumComponents;

    /**
     * Author Marie Held
     * @param inpTileArray the incoming tile array
     * @param inpGlobal the set of global variables that define direction and array dimensions
     *
     */
    // Default constructor
    public SimulationLayoutGUI(TileArray inpTileArray, staticVariable inpGlobal) {
        int simTileRow = inpTileArray.getLength();
        int simTileColumn = inpTileArray.getWidth();
        SimTileGUI simTileButton;
        Tile tileRef;

        inpGlobal.setSimStatus(0);

        this.global = inpGlobal;
        simNumComponents = global.getNumSimTilesComponent();

        simTilesWallBorder = BorderFactory.createLineBorder(Color.BLACK,10);
        simTilesChairBorder = BorderFactory.createLineBorder(Color.BLUE,10);

        GridLayout simTilePanelLayout = new GridLayout(simTileRow,simTileColumn);
        simTilePanelLayout.setHgap(1);
        simTilePanelLayout.setVgap(1);
        simTiles.setLayout(simTilePanelLayout);

        int simComponents = 0;
        String tileName;
        String si;
        String sj;
        // Create the new simTiles based on values in the Tile Array
        for (int i = 0; i < simTileRow; i++) {
            for (int j =0; j < simTileColumn; j++){
                Location loc = new Location(i,j);
                tileRef = inpTileArray.getTile(i,j);
                if (i <= 9) { // a single digit
                    si = "0";
                    si = si.concat(Integer.toString(i));
                }
                else{
                    si = Integer.toString(i);
                }
                if (j <= 9) { // a single digit
                    sj = "0";
                    sj = sj.concat(Integer.toString(j));
                }
                else{
                    sj = Integer.toString(j);
                }
                // tileName = "SimTile" + i + j;
                tileName = "SimTile";
                tileName = tileName.concat(si);
                tileName = tileName.concat(sj);
                simTileButton = new SimTileGUI(loc, tileRef);
                simTileButton.setName(tileName);
                simTileButton.setMargin(new Insets(0, 0, 0, 0));
                simTileButton.setBorderPainted(false);
                simTileButton.setBorder(null);

                simTiles.add(simTileButton);
                // System.out.println(simTileButton.getSimTileName());
                // String simComp;
                // simComp = simTiles.getComponent(simComponents);
            }
        }

        // outputMainSimFrame.add(simTiles,gblSimTiles);
        outputMainSimFrame.add(simTiles);
        simActions.add(stopSimulation);
        outputMainSimFrame.add(simActions,BorderLayout.SOUTH);
        outputMainSimFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        stopSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inpGlobal.setSimStatus(1); // stop indicator
            }
        });

        // inpTileArray.printTileArray();
    }

    /**
     * Purpose: To close the sumulaiion GUI window after the stop button has been pressed
     * Author: Marie Held
     */
    public void closeSimulationLayoutGUI(){
     //   boolean isDone = true;
        try {
           Thread.interrupted(); } // need to avoid Java errors
        catch (Exception e) {
               System.out.println(" in catch phase of disposing simulation gui (Thread.interrupted)" + e);
        }
    //    try {
     //      while (isDone) {isDone = Thread.currentThread().isAlive(); } }// loop until all background process have been completed
    //    catch (Exception e) {
    //            System.out.println(" in catch phase of disposing simulation gui" + e);
    //    }
        outputMainSimFrame.removeAll();
        outputMainSimFrame.setVisible(false);
        outputMainSimFrame.dispose();

    }

    /**
     * Purpose: To add the results of the latest simulation to the simulation Stats file
     * Author: Marie Held
     * @param algorithm which algorithm was used
     * @param floorType Floor type that was cleaned
     * @param minute How long the simulation ran
     * @param run_speed How fast the simulation ran
     * @param TA The tile array that will be use to calculated the cleaning statistics
     */
    public void storeRunStatistics(int algorithm, int run_speed, int floorType,TileArray TA , int [] minute){
        // calculate the simulation statistics such as size of house and percentage of house cleaned
        // add code to append data file manipulation
        System.out.println("In the storeRunStatistics method");

    }



    /**
     * Display the Simulation Layout Screen
     *
     * @return
     *
     */
    public void displaySimulationLayout(SimulationLayoutGUI inpSimulationLayoutGUI) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displaySimulationLayoutFrame(outputMainSimFrame);
            }
        });
    }

    public void displaySimulationLayoutFrame(JFrame inpFrame){
        inpFrame.setPreferredSize(new Dimension(800,600));
        inpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        inpFrame.pack();
        inpFrame.setVisible(true);
    }

    public void printSimTilesName(){
        System.out.println("In printSimTilesNames");
        int numComponens = this.global.getNumSimTilesComponent();
        for (int i = 0; i < numComponens; i++)
            System.out.println(simTiles.getComponent(i).getName());
    }

    /**
     * Author Marie Held
     * Purpose To retrieve the simulation component name
     * @param x x value of the tile
     * @param y y value of the tile
     * Simulation Names are in the format of SimTileXXYY where XX is the x value and YY is the Y value
     * @return a String that contains the component name
     */
    public String getSimTileName(int x, int y) {
        String componentName;
        String compX;
        String compY;
        int icompX;
        int icompY;

        for (int i = 0; i < simNumComponents; i++) {
            componentName = simTiles.getComponent(i).getName();
            // System.out.println("Component Name is " + componentName);
            compX = componentName.substring(7,9);
            // System.out.println("compX = " + compX);
            compY = componentName.substring(9, 11);
            // System.out.println("compY = " + compY);
            icompX = Integer.parseInt(compX);
            icompY = Integer.parseInt(compY);
            if (x == icompX && y == icompY) {
                return simTiles.getComponent(i).getName();
            }
        }
        return (x + " " + y + "Sim Tile not found");
    }

    /**
     *
     * Author Marie Held
     * @param inpComponentName The name of the simulation button component
     * @return If success returns The component number of the simulation button; if failure (simulation button does not exist) - 1
     *
     */
    public int getComponentNumber (String inpComponentName ){

        String compName;

        for (int i = 0; i < simNumComponents; i++) {
            compName = simTiles.getComponent(i).getName();
            if (inpComponentName.compareTo(compName) ==0 ){
                return i;
            }

        }
        return -1;  // The component number does not exit

    }

    /**
     * Author Marie Held, Bryant Terry
     * @param inpX
     * @param inpY
     * @param inpComponentNumber
     * @param inpOldComponentNumber
     * @param prev
     *
     * @Purpose: Set the current vacuum tile and send necessary info to the output.
     * Also, send the previously-occupied tile, so that its appearance can be reset.
     */
    public void setVacuumTile(int inpX, int inpY, int inpComponentNumber, int inpOldComponentNumber, Location prev){
        String tileName;
        int tileNumber;

        SimTileGUI vacuumTile = (SimTileGUI) simTiles.getComponent(inpComponentNumber);
        Location tilelocation = new Location(inpX,inpY);
        vacuumTile.setVacuumLocation(tilelocation);

        SimTileGUI nonvacuumTile = (SimTileGUI) simTiles.getComponent(inpOldComponentNumber);
        nonvacuumTile.setNonVacuumLocation(prev);

        // vacuumTile.setNonVacuumLocation(prev);
        // tileName = this.getSimTileName(inpX,inpY);
        // tileNumber = this.getComponentNumber(tileName);
        // ImageIcon icon;
        // icon = new ImageIcon("vacuumTile.png");
        // setIcon(icon);

        // SimTileGUI simTile = (simTileGUI) tileName;
        // simTile.setVacuumLocation(tilelocation);

        // simTiles.getComponent(tileNumber).setVacuumLocation
    }

}




