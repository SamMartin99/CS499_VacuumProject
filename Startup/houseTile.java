package Startup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.out;


public class houseTile extends JButton {
  // Attributes

    private int tileRow;
    private int tileColumn;
    private int tileCleanValue;  // everything robot crosses the tile increment by 1
    private boolean tileAvailable; // indicates if tile can be clean
    private String tileName;
    private JButton tileButton;
    private String layoutType;

    //Methods

    //default constuctor

    public houseTile(int inpTileRow, int inpTileColumn){
        this.tileRow = inpTileRow;
        this.tileColumn = inpTileColumn;
        this.tileCleanValue = 0;
        this.tileAvailable = true;
       // this.tileButton = new SquareButton("");
        this.tileButton = new JButton("");
        this.tileButton.setName("tile"+inpTileRow+inpTileColumn);
       // this.layoutType = "";
       // this.tileButton.is.setPreferredSize(new Dimension(2,2));


        // create a square button
   //     @Override
   //     public Dimension getPreferredSize () {
   //         Dimension tileSize = super.getPreferredSize();
    //        int sideLength = (int)(tileSize.getWidth()<tileSize.getHeight() ? tileSize.getHeight() : tileSize.getWidth());
   //         //new Dimension(sideLength,sideLength);
   //         //this.tileButton.setPreferredSize(new Dimension(sideLength,sideLength));
  //          return new Dimension(sideLength,sideLength);
       }

    // Getters
    public String getButtonName() {

        return this.getName();

    }

    public int getTileRow(){

        return this.tileRow;
    }

    public int getTileColumn(){

        return this.tileColumn;
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

    public boolean gettileAvailable(){
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
     //   this.setLayoutType(inpHouseLayout);
    //    this.setTileAvailable(false);
        System.out.println("tile " + this.tileRow + ", " + this.tileColumn + " Layout Type is " + inpHouseLayout.getlayoutType() );
    //    tileButton.setBackground(Color.BLUE);
    //    tileButton.setForeground(Color.BLUE);
    //    tileButton.updateUI();

    }

    public void clickTileAction(HouseLayout inpHouseLayout) {
        //   this.setLayoutType(inpLayoutType);
        if (this.gettileAvailable()) {
            this.setLayoutType(inpHouseLayout);
            this.setTileAvailable(false);
            this.printTile(inpHouseLayout);
            this.setBackground(Color.BLUE);
            this.setForeground(Color.BLUE);
            this.updateUI();
            super.updateUI();
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


