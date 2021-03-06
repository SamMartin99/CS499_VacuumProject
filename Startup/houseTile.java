package Startup;

import Model.Table;
import Model.Tile;
import Model.TileArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    public Table containingTable = null; // This is the table that contains this houseTile. Set to null to begin.
    public HouseLayout hl;
    // ---- Methods ----

    // Constructor, takes in a location (a data type encapsulating x and y coords)
    public houseTile(Location locRef, Tile tileRef, houseTile[][] houseTileArrRef, HouseLayout whatever) {
        this.loc = locRef;
        this.tile = tileRef;
        this.tileCleanValue = 0; // Set the cleanliness to 0 upon initialization
        this.tileAvailable = true; // All tiles are blank to start when initialized
        this.tileButton = new JButton(""); // Create the new JButton
        this.tileButton.setName("tile" + loc.x + loc.y); // This creates the tile's name, which is its location values
        parentHouseTileArray = houseTileArrRef;
        hl = whatever;

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

    /**
     * Author Marie held
     * Purpose is to response when a house tile is click
     * @param inpHouseLayout
     * @param inpTA
     */
    public void clickTileAction(HouseLayout inpHouseLayout, TileArray inpTA) {
        int tileType; // var to hold the tileType for this method

        // Basically check for what layout type you're trying to apply, then apply that to this tile's attributes
        // Also must update the tile's icon to match its type
        // Do this for each type you could be setting this tile to
/***********************************************************************************************
 *      This block of code was intented to not allow user to change the tile type
 *      Group decision was that the user should be able to change any tile on the
 *      house layout
 *
 *       // check to see if tile is available
 *       //if (this.tileAvailable == false) {
 *       //    tileUnavailablePopup(this);
 *      //     return;
 *     //  }
***********************************************************************************************/
        // These seemingly can't be switch statements because strings can't work with those
        // All these blocks follow the same format, simply differing in what type you are setting them to
        // DOOR
        if (inpHouseLayout.getlayoutType().compareTo("Door") == 0 )
        {
            if(containingTable != null) // Check if its within a table
                containingTable.DeleteTable(); // If it is, delete that table
            inpTA.setTile (this.loc.x, this.loc.y, 2); // set the tile type to 2 for door
            this.setImageIcon(); // Sets the image icon for this tile to match its new type
            return;
        }
        // WALL
        else if (inpHouseLayout.getlayoutType().compareTo("Wall") == 0 )
        {
            if(containingTable != null)
                containingTable.DeleteTable();
            if (!inpTA.firstClick)
            {
                inpTA.clickTwoX = this.loc.x;
                inpTA.clickTwoY = this.loc.y;
                if (inpTA.clickOneX == inpTA.clickTwoX)
                {
                    while (inpTA.clickOneY < inpTA.clickTwoY)
                    {
                        inpTA.setTile (inpTA.clickOneX, inpTA.clickOneY, 3);
                        parentHouseTileArray[inpTA.clickOneX][inpTA.clickOneY].setImageIcon();
                        inpTA.clickOneY++;
                    }
                    while (inpTA.clickTwoY <= inpTA.clickOneY)
                    {
                        inpTA.setTile (inpTA.clickOneX, inpTA.clickTwoY, 3);
                        parentHouseTileArray[inpTA.clickOneX][inpTA.clickTwoY].setImageIcon();
                        inpTA.clickTwoY++;
                    }
                }
                else if (inpTA.clickOneY == inpTA.clickTwoY)
                {
                    while (inpTA.clickOneX < inpTA.clickTwoX)
                    {
                        inpTA.setTile (inpTA.clickOneX, inpTA.clickOneY, 3);
                        parentHouseTileArray[inpTA.clickOneX][inpTA.clickOneY].setImageIcon();
                        inpTA.clickOneX++;
                    }
                    while (inpTA.clickTwoX <= inpTA.clickOneX)
                    {
                        inpTA.setTile (inpTA.clickTwoX, inpTA.clickOneY, 3);
                        parentHouseTileArray[inpTA.clickTwoX][inpTA.clickOneY].setImageIcon();
                        inpTA.clickTwoX++;
                    }
                }
                inpTA.firstClick = true;
            }
            else if (inpTA.firstClick)
            {
                inpTA.clickOneX = this.loc.x;
                inpTA.clickOneY = this.loc.y;
                ImageIcon icon = new ImageIcon("halfWallTile.png");
                this.setIcon(icon);
                inpTA.firstClick = false;
            }
            return;
        }
        // CHEST
        else if (inpHouseLayout.getlayoutType().compareTo("Chest") == 0 )
        {
            if(containingTable != null)
                containingTable.DeleteTable();
            inpTA.setTile (this.loc.x, this.loc.y, 4); // set the tile type to 4 for chest
            this.setImageIcon(); // Sets the image icon for this tile to match its new type
            return;
        }
        // CHAIR
        else if (inpHouseLayout.getlayoutType().compareTo("Chair") == 0 )
        {
            if(containingTable != null)
                containingTable.DeleteTable();
            inpTA.setTile (this.loc.x, this.loc.y, 5); // set the tile type to 5 for chair
            this.setImageIcon(); // Sets the image icon for this tile to match its new type
            return;
        }
        // Clear
        else if (inpHouseLayout.getlayoutType().compareTo("Clear") == 0 )
        {
            if(containingTable != null)
                containingTable.DeleteTable();
            inpTA.setTile (this.loc.x, this.loc.y, 1); // set the tile type to 1 for an empty tile
            this.setImageIcon(); // Sets the image icon for this tile to match its new type
            return;
        }



        // Author: Guess Crow
        // VACUUM
        // Handles vacuum icon placement
        else if (inpHouseLayout.getlayoutType().compareTo("Vacuum") == 0 )
        {
            if(containingTable != null) // Check if table, if placing on table, delete the table
                containingTable.DeleteTable();

            // This block won't let you place a vacuum on walls or chests, since those are inaccessible
            if(inpTA.getTile(this.loc.x, this.loc.y).getType() == 3 || inpTA.getTile(this.loc.x, this.loc.y).getType() == 4)
            {
                tileUnavailablePopup(this);
                return;
            }

            inpTA.setVacuum (this.loc); // set the vacuum's start location to this houseTile's location
            ImageIcon icon = new ImageIcon("vacuumTile.png"); // set its imageIcon to vacuum (even though thats not its true type)
            this.setIcon(icon);
            parentHouseTileArray[inpTA.prevVacuumLoc.x][inpTA.prevVacuumLoc.y].setImageIcon(); // Reset the tile that was the previous vaccum back to its true icon
            return; // We return early because we don't want our vacuum tile to be overwritten
        }

        // TABLE
        else if (inpHouseLayout.getlayoutType().compareTo("Table") == 0 )
        {
            Table t = new Table(parentHouseTileArray, this.loc); // Make a new Table, passing it this tile's parent array and its location
        }

       else if (inpHouseLayout.getlayoutType().compareTo("Shag") == 0 || inpHouseLayout.getlayoutType().compareTo("Hardwood") == 0 || inpHouseLayout.getlayoutType().compareTo("LoopPile") == 0 || inpHouseLayout.getlayoutType().compareTo("CutPile") == 0)
        {
            for (int i = 0; i < inpTA.getLength(); i++) {
                for (int j = 0; j < inpTA.getWidth(); j++) {
                    parentHouseTileArray[i][j].setImageIcon();
                }
            }
        }
        /***********************************************************************************************
         *      This block of code was intented to not allow user to change the tile type
         *      Group decision was that the user should be able to change any tile on the
         *      house layout
         *else
         *{
         *      tileUnavailablePopup(this);
         *      System.out.println("Tile is unavailable");
         *       this.printTile(inpHouseLayout);
         *    }
         *************************************************************************************************/
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
    // Sets the houseTile's icon to represent its type
    public void setImageIcon()
    {
        int type = this.tile.getType(); // Get this tile's type
        ImageIcon icon = null; // Make an icon and set it to null for now

        try {
            switch (type) { // Switch statment to detect which type of icon we'll need
                case 1: icon = new ImageIcon("plainTile.png"); // If type is 1, then set it to a blank tile icon
                {
                    if(hl.getFloorType() == 1)
                        icon = new ImageIcon("hardTile.png");
                    else if(hl.getFloorType() == 2)
                        icon = new ImageIcon("loopTile.png");
                    else if(hl.getFloorType() == 3)
                        icon = new ImageIcon("cutTile.png");
                    else
                        icon = new ImageIcon("friezeTile.png");
                }
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

