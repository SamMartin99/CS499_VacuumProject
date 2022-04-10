package View;

import Model.SimTileGUI;
import Model.Tile;
import Model.TileArray;
import Startup.Location;
import Model.SimTileGUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

//public class SimulationLayoutGUI (TileArray inpTileArray) {
public class SimulationLayoutGUI{

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

        // Create the new simTiles based on values in the Tile Array
        for (int i = 0; i < simTileRow; i++) {
            for (int j =0; j < simTileColumn; j++){
                Location loc = new Location(i,j);
                tileRef = inpTileArray.getTile(i,j);
                simTileButton = new SimTileGUI(loc, tileRef);
                if (3 == inpTileArray.getTile(0,0).getType()) {
                    simTileButton.setName("Wall");
                    simTileButton.setBorder(simTilesWallBorder);
                }
                if (4 == inpTileArray.getTile(2,2).getType()) {
                    simTileButton.setName("Chest");
                    simTileButton.setBorder(simTilesChairBorder);
                }
                simTileButton.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        System.out.println("Some tile property changed");
                    }

                });
                simTiles.add(simTileButton);

            }
        }

      //  outputMainSimFrame.add(simTiles,gblSimTiles);
        outputMainSimFrame.add(simTiles);
        outputMainSimFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inpTileArray.printTileArray();


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
}




