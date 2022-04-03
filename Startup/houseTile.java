package Startup;

import javax.swing.*;


public class houseTile extends JButton{
  // Attributes

    private int tileRow;
    private int tileColumn;
    private int tileCleanValue;  // everything robot crosses the tile increment by 1
    private boolean tileAvailable; // indicates if tile can be clean
    private String tileName;
    private JButton tileButton;

    //Methods

    //default constuctor

    public houseTile(int inpTileRow, int inpTileColumn){
        this.tileRow = inpTileRow;
        this.tileColumn = inpTileColumn;
        this.tileCleanValue = 0;
        this.tileAvailable = true;
        this.tileButton = new JButton("");
        this.tileButton.setName("tile"+inpTileRow+inpTileColumn);
 //       this.tileButton.addActionListener(e -> System.out.println("Tile was clicked"));

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

    public void printTile(){
        System.out.println("tile " + this.tileRow + ", " + this.tileColumn + " clicked");
    }

    }


