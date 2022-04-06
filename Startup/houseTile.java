package Startup;

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


// Class definition
public class houseTile extends JButton {
    // Attributes
    private int tileCleanValue;  // everything robot crosses the tile increment by 1
    private boolean tileAvailable; // indicates if tile can be clean
    private String tileName;
    private final JButton tileButton;
    private String layoutType;
    private Location loc;

    // ---- Methods ----

    // Constructor, takes in a location (a data type encapsulating x and y coords)
    public houseTile(Location locRef) {
        this.loc = locRef;
        this.tileCleanValue = 0; // Set the cleanliness to 0 upon initialization
        this.tileAvailable = true; // All tiles are blank to start when initialized
        this.tileButton = new JButton(""); // Create the new JButton
        this.tileButton.setName("tile" + loc.x + loc.y); // This creates the tile's name, which is its location values

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

    public void setLayoutType (HouseLayout inpHouseLayout){
        this.layoutType = inpHouseLayout.getlayoutType();
    }

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

        // DOOR
        if (inpHouseLayout.getlayoutType().compareTo("Door") == 0 )
            {
                tileType = 2;
                inpTA.setTile (this.loc.x, this.loc.y, tileType);
                try {
                    ImageIcon icon = new ImageIcon("doorTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        // WALL
        else if (inpHouseLayout.getlayoutType().compareTo("Wall") == 0 )
            {
                tileType = 3;
                inpTA.setTile (this.loc.x,this.loc.y, tileType);
                try {
                    ImageIcon icon = new ImageIcon("wallTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        // CHEST
        else if (inpHouseLayout.getlayoutType().compareTo("Chest") == 0 )
            {
                tileType = 4;
                inpTA.setTile (this.loc.x,this.loc.y, tileType);
                try {
                    ImageIcon icon = new ImageIcon("chestTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        // CHAIR
        else if (inpHouseLayout.getlayoutType().compareTo("Chair") == 0 )
            {
                tileType = 5;
                inpTA.setTile (this.loc.x,this.loc.y, tileType);
                try {
                    ImageIcon icon = new ImageIcon("tableTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        // TABLE
        else if (inpHouseLayout.getlayoutType().compareTo("Table") == 0 )
            {
                tileType = 6;
                inpTA.setTile (this.loc.x,this.loc.y, tileType);
                try {
                    ImageIcon icon = new ImageIcon("tableTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        else
        {
            tileUnavailablePopup(this);
            System.out.println("Tile is unavailable");
            this.printTile(inpHouseLayout);
        }
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
    }


