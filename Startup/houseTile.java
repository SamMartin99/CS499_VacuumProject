package Startup;

import Model.TileArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.out;


public class houseTile extends JButton {
    // Attributes

    private int tileCleanValue;  // everything robot crosses the tile increment by 1
    private boolean tileAvailable; // indicates if tile can be clean
    private String tileName;
    private final JButton tileButton;
    private String layoutType;
    private Location loc;

    //Methods

    //default constructor

    public houseTile(Location locRef) {
        this.loc = locRef;
        this.tileCleanValue = 0;
        this.tileAvailable = true;
        // this.tileButton = new SquareButton("");
        this.tileButton = new JButton("");
        this.tileButton.setName("tile" + loc.x + loc.y);
        // Guess Crow
        // I dunno why this isn't applying the ImageIcon I added to this JButton, it should be, but idk
        try {
            ImageIcon icon = new ImageIcon("plainTile.png");
            this.setIcon(icon);
            this.setMargin(new Insets(0, 0, 0, 0));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.setBorderPainted(false);
        this.setBorder(null);
        // this.layoutType = "";
        // this.tileButton.is.setPreferredSize(new Dimension(2,2));

        // create a square button
        // @Override
        // public Dimension getPreferredSize () {
        //      Dimension tileSize = super.getPreferredSize();
        //      int sideLength = (int)(tileSize.getWidth()<tileSize.getHeight() ? tileSize.getHeight() : tileSize.getWidth());
        //      //new Dimension(sideLength,sideLength);
        //      //this.tileButton.setPreferredSize(new Dimension(sideLength,sideLength));
        //      return new Dimension(sideLength,sideLength);
        // }
    }

    // Getters
    public String getButtonName() {

        return this.getName();

    }

    public Location GetLocation()
    {
        return this.loc;
    }

    public float getTileCleanValue(){

        return this.tileCleanValue;
    }

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

    // public void setLayoutType(String inpLayoutType){
    //     this.layoutType = inpLayoutType;
    // }
    // Only the tile value can be changed after button creation

    public void setCleanTileValue(int inpTileCleanValue){

        this.tileCleanValue = inpTileCleanValue;

    }

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
        int tileType;
        if (inpHouseLayout.getlayoutType().compareTo("Door") == 0 )
            {tileType = 2; inpTA.setTile (this.loc.x,
                    this.loc.y,
                    tileType); }
        if (inpHouseLayout.getlayoutType().compareTo("Wall") == 0 )
            {
                tileType = 3;
                inpTA.setTile (this.loc.x,this.loc.y, tileType);
            }
        if (inpHouseLayout.getlayoutType().compareTo("Chest") == 0 )
        {tileType = 4; inpTA.setTile (this.loc.x,this.loc.y, tileType); }
        if (inpHouseLayout.getlayoutType().compareTo("Chair") == 0 )
        {tileType = 5;  inpTA.setTile (this.loc.x,this.loc.y, tileType);}
        if (inpHouseLayout.getlayoutType().compareTo("Table") == 0 )
        {tileType = 6;  inpTA.setTile (this.loc.x,this.loc.y, tileType);}

 //       System.out.println();

    //    inpTA.setTile (this.getTileRow(),this.tileColumn, tileType);

        if (this.getTileAvailable()) {
            // Wall
            if(inpHouseLayout.getlayoutType().compareTo("Wall") == 0)
            {
                try {
                    ImageIcon icon = new ImageIcon("wallTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            // Door
            if(inpHouseLayout.getlayoutType().compareTo("Door") == 0)
            {
                try {
                    ImageIcon icon = new ImageIcon("doorTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            // Chair
            if(inpHouseLayout.getlayoutType().compareTo("Chair") == 0)
            {
                try {
                    ImageIcon icon = new ImageIcon("tableTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            // Table
            if(inpHouseLayout.getlayoutType().compareTo("Table") == 0)
            {
                try {
                    ImageIcon icon = new ImageIcon("tableTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            // Chest
            if(inpHouseLayout.getlayoutType().compareTo("Chest") == 0)
            {
                try {
                    ImageIcon icon = new ImageIcon("chestTile.png");
                    this.setIcon(icon);
                    this.setMargin(new Insets(0, 0, 0, 0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } else {
            tileUnavailablePopup(this);
            System.out.println("Tile is unavailable");
            this.printTile(inpHouseLayout);
        }
    }

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


