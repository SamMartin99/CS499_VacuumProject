package View;

import Model.Tile;
import Model.TileArray;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//public class SimulationLayoutGUI (TileArray inpTileArray) {
public class SimulationLayoutGUI{

    // Attributes
    JFrame outputMainSimFrame = new JFrame("Simulation Output");
    JPanel simTiles = new JPanel(); // Panel that holds the buttons for the array

    JButton st00 = new JButton();
    JButton st01 = new JButton();
    JButton st02 = new JButton();
    JButton st03 = new JButton();
    JButton st04 = new JButton();
    JButton st05 = new JButton();
    JButton st06 = new JButton();
    JButton st07 = new JButton();
    JButton st08 = new JButton();
    JButton st09 = new JButton();
    JButton st10 = new JButton();
    JButton st11 = new JButton();
    JButton st12 = new JButton();
    JButton st13 = new JButton();
    JButton st14 = new JButton();
    JButton st15 = new JButton();
    JButton st16 = new JButton();
    JButton st17 = new JButton();
    JButton st18 = new JButton();
    JButton st19 = new JButton();
    JButton st20 = new JButton();
    JButton st21 = new JButton();
    JButton st22 = new JButton();
    JButton st23 = new JButton();
    JButton st24 = new JButton();
    JButton st25 = new JButton();
    JButton st26 = new JButton();
    JButton st27 = new JButton();
    JButton st28 = new JButton();
    JButton st29 = new JButton();
    JButton st30 = new JButton();
    JButton st31 = new JButton();
    JButton st32 = new JButton();
    JButton st33 = new JButton();
    JButton st34 = new JButton();
    JButton st35 = new JButton();
    JButton st36 = new JButton();
    JButton st37 = new JButton();
    JButton st38 = new JButton();
    JButton st39 = new JButton();

    Border simTilesWallBorder;
    Border simTilesChairBorder;



    // Default constructor
    public SimulationLayoutGUI(TileArray inpTileArray) {

        simTilesWallBorder = BorderFactory.createLineBorder(Color.BLACK,10);
        simTilesChairBorder = BorderFactory.createLineBorder(Color.BLUE,10);


        GridLayout simTilePanelLayout = new GridLayout(4,10);
        simTilePanelLayout.setHgap(1);
        simTilePanelLayout.setVgap(1);
        simTiles.setLayout(simTilePanelLayout);

        if (3 == inpTileArray.getTile(0,0).getType()) {
            st00.setName("Wall");
            st00.setBorder(simTilesWallBorder);
        }

        if (4 == inpTileArray.getTile(2,2).getType()) {
            st22.setName("Chest");
            st22.setBorder(simTilesChairBorder);
        }


        simTiles.add(st00);
        simTiles.add(st01);
        simTiles.add(st02);
        simTiles.add(st03);
        simTiles.add(st04);
        simTiles.add(st05);
        simTiles.add(st06);
        simTiles.add(st07);
        simTiles.add(st08);
        simTiles.add(st09);
        simTiles.add(st10);
        simTiles.add(st11);
        simTiles.add(st12);
        simTiles.add(st13);
        simTiles.add(st14);
        simTiles.add(st15);
        simTiles.add(st16);
        simTiles.add(st17);
        simTiles.add(st18);
        simTiles.add(st19);
        simTiles.add(st20);
        simTiles.add(st21);
        simTiles.add(st22);
        simTiles.add(st23);
        simTiles.add(st24);
        simTiles.add(st25);
        simTiles.add(st26);
        simTiles.add(st27);
        simTiles.add(st28);
        simTiles.add(st29);
        simTiles.add(st30);
        simTiles.add(st31);
        simTiles.add(st32);
        simTiles.add(st33);
        simTiles.add(st34);
        simTiles.add(st35);
        simTiles.add(st36);
        simTiles.add(st37);
        simTiles.add(st38);
        simTiles.add(st39);


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




