package Model;

import javax.swing.*;
import Startup.Location;

import java.awt.*;

public class SimTileGUI extends JButton {
    // Attributes
    private Tile tile;
    private float tileCleanValue;  // everything robot crosses the tile increment by 0.1
    private JButton simTileButton;
    private String tileName;
    private Location loc;

    // ---- Methods ----

    // Constructor, takes in a location (a data type encapsulating x and y coords)
    public SimTileGUI(Location locRef, Tile tileRef) {

        this.loc = locRef;
        this.tile = tileRef;
        this.tileCleanValue = 0; // Set the cleanliness to 0 upon initialization
        this.simTileButton = new JButton(""); // Create the new JButton
        this.simTileButton.setName("tile" + loc.x + loc.y); // This creates the tile's name, which is its location values
        this.simTileButton.setName("tile" +  loc.x + loc.y);

        // This block will apply a default tile icon to the JButton
        try {
            ImageIcon icon = new ImageIcon("plainTile.png");
            this.setIcon(icon);
            this.setMargin(new Insets(0, 0, 0, 0));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // Some attribute setting for making the button look nice
        this.setBorderPainted(false);
        this.setBorder(null);
    }

    // Getters
    public String getButtonName() { return this.getName(); }

    public Location GetLocation()
    {
        return this.loc;
    }

    public float getTileCleanValue(){ return this.tileCleanValue; }

    public JButton getTileButton(){
        return this.simTileButton;
    }

    public String getTileName(){
        return this.simTileButton.getName();
    }

    // Setters
    public void setCleanTileValue(int inpTileCleanValue){ this.tileCleanValue = inpTileCleanValue; }

    public void incrementTileCleanValue(){
        this.tileCleanValue ++ ;
    }


}
