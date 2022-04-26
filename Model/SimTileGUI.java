package Model;

import javax.swing.*;

import Startup.Location;

import java.awt.*;


public class SimTileGUI extends JButton {
    // protected PropertyChangeSupport vacuumLocationChange;
    // Attributes
    private final Tile tile;
    private float tileCleanValue;  // everything robot crosses the tile increment by 0.1
    private final JButton simTileButton;
    private final Location loc;
    private final Location vacuumLocation;
    private int simTileComponentNumber;

    // ---- Methods ----

    // Constructor, takes in a location (a data type encapsulating x and y coordinates)
    public SimTileGUI(Location locRef, Tile tileRef) {

        Location oldLoc = new Location(0,0);
        this.loc = locRef;
        this.tile = tileRef;
        this.tileCleanValue = 0; // Set the cleanliness to 0 upon initialization
        this.simTileButton = new JButton(""); // Create the new JButton
        this.simTileButton.setName("tile" + loc.x + loc.y); // This creates the tile's name, which is its location values
        this.simTileButton.setName("tile" +  loc.x + loc.y);
        this.vacuumLocation = new Location(0,0);

        // This block will apply a default tile icon to the JButton
        this.setImageIcon();

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

    public Location getVacuumLocation() {return this.vacuumLocation;}

    public String getSimTileName() { return this.getName(); }

    // Setters
    public void setCleanTileValue(int inpTileCleanValue){ this.tileCleanValue = inpTileCleanValue; }

    public void incrementTileCleanValue(){
        this.tileCleanValue ++ ;
    }

    public void setVacuumLocation(Location newVacuumLoc){
        ImageIcon icon;
        icon = new ImageIcon("vacuumTile.png");
        this.setIcon(icon);
    }

    public void setNonVacuumLocation(Location newVacuumLoc, int oldType){
        ImageIcon icon;

        if (oldType == 1)
        {
            icon = new ImageIcon("plainTile.png");
            this.setIcon(icon);
        }
        else if (oldType == 2)
        {
            icon = new ImageIcon("doorTile.png");
            this.setIcon(icon);
        }
        else if (oldType == 5)
        {
            icon = new ImageIcon("chairTile.png");
            this.setIcon(icon);
        }
        else if (oldType == 6)
        {
            icon = new ImageIcon("tableTile.png");
            this.setIcon(icon);
        }
    }

    private void setImageIcon()
    {
        int type = this.tile.getType(); // Get this tile's type
        ImageIcon icon = null; // Make an icon and set it to null for now

        try {
            switch (type) { // Switch statement to detect which type of icon we'll need
                case 1: icon = new ImageIcon("plainTile.png"); // If type is 1, then set it to a blank tile icon
                    break;
                case 2: icon = new ImageIcon("doorTile.png"); // If type is 2, then set it to a door icon
                    break;
                case 3: icon = new ImageIcon("wallTile.png"); // If type is 3, then set it to a wall icon
                    break;
                case 4: icon = new ImageIcon("chestTile.png"); // If type is 4, then set it to a chest icon
                    break;
                case 5: icon = new ImageIcon("chairTile.png"); // If type is 5, then set it to a chair icon
                    break;
                case 6: icon = new ImageIcon("tableTile.png"); // If type is 6, then set it to a table icon
                    break;
            }
        } catch (Exception ex){ System.out.println(ex); }
        this.setIcon(icon);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
        this.setBorder(null);
    }

}
