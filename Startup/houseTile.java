package Startup;

import Model.Table;
import Model.Tile;
import Model.TileArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.out;

// Authors: Guess Crow, Marie Held, Bryant Terry, Sam Martin
// Class: CS 499 Senior Design
// Project: Vacuum Robot Simulator
// Purpose: A class for each GUI tile in our grid that represents the house
// These are individual tiles


// Class definition
public class houseTile extends JButton {
    // Attributes
    private Tile tile;
    private int tileCleanValue;  // everything robot crosses the tile increment by 1
    private boolean tileAvailable; // indicates if tile can be clean
    private String tileName;
    private final JButton tileButton;
    private String layoutType;
    private Location loc;
    public houseTile[][] parentHouseTileArray; // This is the array of houseTiles to which this houseTile belongs, kinda inefficient and weird, but it was the only way I saw to get Tables working

    // ---- Methods ----

    // Constructor, takes in a location (a data type encapsulating x and y coords)
    public houseTile(Location locRef, Tile tileRef, houseTile[][] temp) {
        this.loc = locRef;
        this.tile = tileRef;
        this.tileCleanValue = 0; // Set the cleanliness to 0 upon initialization
        this.tileAvailable = true; // All tiles are blank to start when initialized
        this.tileButton = new JButton(""); // Create the new JButton
        this.tileButton.setName("tile" + loc.x + loc.y); // This creates the tile's name, which is its location values
        parentHouseTileArray = temp;

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

    public Tile getTile(){ return tile; }

    public Location GetLocation()
    {
        return this.loc;
    }

    public float getTileCleanValue(){ return this.tileCleanValue; }

    public JButton getTileButton(){
        return this.tileButton;
    }

    public String getTileName(){
        return this.tileButton.getName();
    }

    public boolean getTileAvailable(){
        return this.tileAvailable;
    }

    // Setters
    public void setCleanTileValue(int inpTileCleanValue){ this.tileCleanValue = inpTileCleanValue; }

    public void incrementTileCleanValue(){
        this.tileCleanValue ++ ;
    }

    public void setTileAvailable(boolean inpTileAvailable){
        this.tileAvailable = inpTileAvailable;
    }

    public void setLayoutType(String inpLayoutType){}

    public void printTile(HouseLayout inpHouseLayout){
        System.out.println("tile " + this.loc.x + ", " + this.loc.y + " Layout Type is " + inpHouseLayout.getlayoutType() );
    }

    /* Name: clickTileAction
     * Parameters: HouseLayout inpHouseLayout
     * Return: N/A
     * Purpose: Sets color of tile to its respective property.
     * Color of floor tiles should be calculated automatically (based off of the chosen floor)
     * in a separate function.
     */

    public void clickTileAction(HouseLayout inpHouseLayout, TileArray inpTA) {
        int tileType; // var to hold the tileType for this method

        // Basically check for what layout type you're trying to apply, then apply that to this tile's attributes
        // Also must update the tile's icon to match its type
        // Do this for each type you could be setting this tile to

        // check to see if tile is available
        if (this.tileAvailable == false) {
            tileUnavailablePopup(this);
            return;
        }

        // These seemingly can't be switch statements because strings can't work with those
        // DOOR
        if (inpHouseLayout.getlayoutType().compareTo("Door") == 0 )
        {
            tileType = 2;
            inpTA.setTile (this.loc.x, this.loc.y, tileType);
        }
        // WALL
        else if (inpHouseLayout.getlayoutType().compareTo("Wall") == 0 )
        {
            tileType = 3;
            inpTA.setTile (this.loc.x,this.loc.y, tileType);
        }
        // CHEST
        else if (inpHouseLayout.getlayoutType().compareTo("Chest") == 0 )
        {
            tileType = 4;
            inpTA.setTile (this.loc.x,this.loc.y, tileType);
        }
        // CHAIR
        else if (inpHouseLayout.getlayoutType().compareTo("Chair") == 0 )
        {
            tileType = 5;
            inpTA.setTile (this.loc.x,this.loc.y, tileType);
        }
        // TABLE
        else if (inpHouseLayout.getlayoutType().compareTo("Table") == 0 )
        {
            Table t = new Table(parentHouseTileArray, this.loc); // Make a new table, pass it the houseTile[][] arr that contains this houseTile, and this houseTile's location
            tileType = 6;
            inpTA.setTile (this.loc.x,this.loc.y, tileType);
        }
        else
        {
            tileUnavailablePopup(this);
            System.out.println("Tile is unavailable");
            this.printTile(inpHouseLayout);
        }
        this.setImageIcon(); // Sets the image icon for this tile to match its new type
    }

    // This method is a bunch of JSwing code that creates a popup window that alerts the user the tile they clicked is unavailable
    private void tileUnavailablePopup(houseTile inptileButton)  {
        Popup p;
        JFrame popupFrame = new JFrame("Tile Unavailable");
        JLabel lTileUnavailable = new JLabel(inptileButton.getTileName() +" is unavailable");
        popupFrame.setSize(400,400);
        PopupFactory pf = new PopupFactory();
        JPanel popupPanel = new JPanel() ;
        popupPanel.add(lTileUnavailable);
        p = pf.getPopup(popupFrame,popupPanel, 180,100);
        JButton b = new JButton("Click to return");
        popupPanel.add(b);
        popupFrame.add(popupPanel);
        popupFrame.setVisible(true);
        p.show();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("In Tile Unavailable popup");
                p.hide();
                popupFrame.setVisible(false);
            }
        });
    }

    // Author: Guess Crow
    // This function does all the imageIcon handling/setting for whatever houseTile you're trying to use with it
    public void setImageIcon()
    {
        int type = this.tile.getType(); // Get this tile's type
        ImageIcon icon = null; // Make an icon and set it to null for now

        try {
            switch (type) { // Switch statment to detect which type of icon we'll need
                case 1: icon = new ImageIcon("plainTile.png"); // If type is 1, then set it to a blank tile icon
                    break;
                case 2: icon = new ImageIcon("doorTile.png");// If type is 2, then set it to a door icon
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
    }
}