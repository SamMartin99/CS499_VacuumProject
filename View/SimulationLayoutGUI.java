package View;

import Model.SimTileGUI;
import Model.Tile;
import Model.TileArray;
import Startup.Location;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.text.StyleConstants.setIcon;

//public class SimulationLayoutGUI (TileArray inpTileArray) {
public class SimulationLayoutGUI {

    // Attributes
    JFrame outputMainSimFrame = new JFrame("Simulation Output");
    JPanel simTiles = new JPanel(); // Panel that holds the buttons for the array

    Border simTilesWallBorder;
    Border simTilesChairBorder;

    // Default constructor
    public SimulationLayoutGUI(TileArray inpTileArray) {
        int simTileRow = inpTileArray.getLength();
        int simTileColumn = inpTileArray.getWidth();
        SimTileGUI simTileButton;
        Tile tileRef;

        simTilesWallBorder = BorderFactory.createLineBorder(Color.BLACK,10);
        simTilesChairBorder = BorderFactory.createLineBorder(Color.BLUE,10);

        GridLayout simTilePanelLayout = new GridLayout(10,10);
        simTilePanelLayout.setHgap(1);
        simTilePanelLayout.setVgap(1);
        simTiles.setLayout(simTilePanelLayout);

        int simComponents = 0;
        String tileName;
        // Create the new simTiles based on values in the Tile Array
        for (int i = 0; i < simTileRow; i++) {
            for (int j =0; j < simTileColumn; j++){
                Location loc = new Location(i,j);
                tileRef = inpTileArray.getTile(i,j);
                tileName = "SimTile" + i + j;
                simTileButton = new SimTileGUI(loc, tileRef);
                simTileButton.setName(tileName);


                simTiles.add(simTileButton);
          //      System.out.println(simTileButton.getSimTileName());
          //      String simComp;
          //      simComp = simTiles.getComponent(simComponents);


            }
        }

      //  outputMainSimFrame.add(simTiles,gblSimTiles);
        outputMainSimFrame.add(simTiles);
        outputMainSimFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

 //       inpTileArray.printTileArray();


    }

    /**
     * Display the Simulation Layout Screen
     *
     * @return
     * @parm
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
        for (int i = 0; i < 100; i++)
            System.out.println(simTiles.getComponent(i).getName());
    }

    public String getSimTileName(int x, int y) {
        String componentName = "";
        String compX;
        String compY;
        int icompX;
        int icompY;

        for (int i = 0; i < 100; i++) {
            componentName = simTiles.getComponent(i).getName();
          //  System.out.println("Component Name is " + componentName);
            compX = componentName.substring(7,8);
          //  System.out.println("compX = " + compX);
            compY = componentName.substring(8, 9);
          //  System.out.println("compY = " + compY);
            icompX = Integer.parseInt(compX);
            icompY = Integer.parseInt(compY);
            if (x == icompX && y == icompY) {
                return simTiles.getComponent(i).getName();
            }
        }
        return ("X Y Sim Tile not found");
    }

    public int getComponentNumber (String inpComponentName ){

        String compName;

        for (int i = 0; i < 100; i++) {
            compName = simTiles.getComponent(i).getName();
            if (inpComponentName.compareTo(compName) ==0 ){
                return i;
            }

        }
        return -1;
    }

    public void setVacuumTile(int inpX, int inpY, int inpComponentNumber){
        String tileName;
        int tileNumber;
        SimTileGUI vacuumTile = (SimTileGUI) simTiles.getComponent(inpComponentNumber);
        Location tilelocation = new Location(inpX,inpY);
        vacuumTile.setVacuumLocation(tilelocation);
   //     tileName = this.getSimTileName(inpX,inpY);
  //      tileNumber = this.getComponentNumber(tileName);
  //      ImageIcon icon;
  //      icon = new ImageIcon("vacuumTile.png");
   //     setIcon(icon);


        //    SimTileGUI simTile = (simTileGUI) tileName;
    //    simTile.setVacuumLocation(tilelocation);

 //       simTiles.getComponent(tileNumber).setVacuumLocation
    }

}




