package Startup;

// Authors: Guess Crow, Marie Held, Bryant Terry, Sam Martin
// Class: CS 499 Senior Design
// Project: Vacuum Robot Simulator
// Purpose: NEEDS FILLED

import static Startup.SplashScreen.maxTileArrayColumn;
import static Startup.SplashScreen.maxTileArrayRow;

public class HouseLayout {
    // Attributes
    private final int dim1 = maxTileArrayRow; // These define the maximum size of the array of tiles
    private final int dim2 = maxTileArrayColumn;
    private String LayoutName = ""; // Name of the house layout will be unique
    private int [][] TileArray = new int[dim1][dim2];
    // The actual layout of the house; 0 -- can be cleaned ; 1 -- can't be cleaned
    // type of floor for the house 1 -- Hard (default), 2-- Loop Pile, 3-- Cut Pile, 4 -- Freieze-cut
    private int floorType;
    private String layoutType = "";

    // Methods

    // default constructor
    public HouseLayout(String inpLayoutName){
       this.LayoutName = inpLayoutName;
       // set every tile to be cleanable i.e. value of 0
       for (int index1 =0; index1 < dim1; index1++){
           for (int index2 = 0; index2 < dim2; index2++){
               this.TileArray[index1][index2] =0;
           }
       }

       // default floor type is 1 (hardwood)
       this.floorType = 1;
    }

    // default getters
    public String getLayoutName() {return this.LayoutName;}
    public int[][] getTileArray() {return this.TileArray;}
    public int getFloorType() {return this.floorType;}
    public int getDim1() {return this.dim1;}
    public int getDim2() {return this.dim2;}
    public String getlayoutType() {return  this.layoutType; }

    // Set the values
    public String setLayoutName(String inpLayoutName){
       this.LayoutName = inpLayoutName;
       return this.LayoutName;
    }

    public int[][] setTileArray(int[][] inpTileArray){
        this.TileArray = inpTileArray;
        return this.TileArray;
    }

    public int setFloorType(int inpFloorType){
        // 1 is
        // 2 is
        this.floorType = inpFloorType;
        return this.floorType;
    }

    public String wallClick(){
        this.layoutType = "Wall";
        return layoutType;
    }

    public String doorClick(){
        this.layoutType = "Door";
        return layoutType;
    }

    public String chairClick(){
        this.layoutType = "Chair";
        return layoutType;
    }

    public String tableClick(){
        this.layoutType = "Table";
        return layoutType;
    }

    public String chestClick(){
        this.layoutType = "Chest";
        return layoutType;
    }

    public String shagClick(){
        this.layoutType = "Shag";
        return layoutType;
    }

    public String hardwoodClick(){
        this.layoutType = "Hardwood";
        return layoutType;
    }

    public String loopPileClick(){
        this.layoutType = "LoopPile";
        return layoutType;
    }

    public String cutPileClick(){
        this.layoutType = "CutPile";
        return layoutType;
    }

}
