package Startup;

public class HouseLayout {

    // Attributes
    private int dim1 = 50;
    private int dim2 = 50;
    private String LayoutName = ""; // Name of the house layout will be unique
    private int [][] TileArray = new int[dim1][dim2];
    //private houseTile actualTiles[] = new houseTile();       // The actual layout of the house; 0 -- can be cleaned ; 1 -- can't be cleaned
    private int floorType;          // type of floor for the house 1 -- Hard (default), 2-- Loop Pile, 3-- Cut Pile, 4 -- Freieze-cut
    private String layoutType = "";

    // Methods

    // default constuctor
   public HouseLayout(String inpLayoutName){
       this.LayoutName = inpLayoutName;

       // set every tile to be cleanable i.e. value of 0
       for (int index1 =0; index1 < dim1; index1++){
           for (int index2 = 0; index2 < dim2; index2++){
               this.TileArray[index1][index2] =0;
           }
       }

       // default floor type is 1
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

    public int[][] setTileArray(int inpTileArray[][]){
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
     //  System.out.println("The Wall button was clicked");
       this.layoutType = "Wall";
       return layoutType;
    }

    public String doorClick(){
      //  System.out.println("The Door button was clicked");
        this.layoutType = "Door";
        return layoutType;
    }

    public String chairClick(){
     //   System.out.println("The Chair button was clicked");
        this.layoutType = "Chair";
        return layoutType;
    }

    public String tableClick(){
      //  System.out.println("The Table button was clicked");
        this.layoutType = "Table";
        return layoutType;
    }

    public String chestClick(){
      //  System.out.println("The Chest button was clicked");
        this.layoutType = "Chest";
        return layoutType;
    }

    public String shagClick(){
       // System.out.println("The Shag button was clicked");
        this.layoutType = "Shag";
        return layoutType;
    }

    public String hardwoodClick(){
      //  System.out.println("The Hardwood button was clicked");
        this.layoutType = "Hardwood";
        return layoutType;
    }

    public String loopPileClick(){
       // System.out.println("The LoopPile button was clicked");
        this.layoutType = "LoopPile";
        return layoutType;
    }

    public String cutPileClick(){
       // System.out.println("The CutPile button was clicked");
        this.layoutType = "CutPile";
        return layoutType;
    }



}
