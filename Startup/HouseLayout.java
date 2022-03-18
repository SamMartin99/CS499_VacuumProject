package Startup;

public class HouseLayout {

    // Attributes
    private int dim1 = 100;
    private int dim2 = 100;
    private String LayoutName = ""; // Name of the house layout will be unique
    private int [][] TileArray = new int[dim1][dim2];       // The actual layout of the house; 0 -- can be cleaned ; 1 -- can't be cleaned
    private int floorType;          // type of floor for the house 1 -- Hard (default), 2-- Loop Pile, 3-- Cut Pile, 4 -- Freieze-cut

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
       this.floorType = inpFloorType;
       return this.floorType;
    }
}
