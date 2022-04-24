package Startup;

public class staticVariable {

    private int maxRow;
    private int maxColumn;
    private int minRow;
    private int minColumn;
    private int vacuumDirection;
    private int direction; // general purpose -- currently being used in the wall following algorithm
    private int numSimTilesComponent;
    private int simulationStatus ; // indicator use to signal that the simulation should be stopped

    // default constructor Note initial values have already been set
    public staticVariable(){
        this.maxRow = 44;
        this.maxColumn = 44;
        this.minRow = 0;
        this.minColumn = 0;
        this.vacuumDirection =0;
        this.direction = 0;
        this.numSimTilesComponent = maxRow * maxColumn;
        this.simulationStatus = 0;
    }

    //getters
    public int getMaxRow(){
        return this.maxRow;
    }

    public int getMaxColumn() {
        return this.maxColumn;
    }

    public int getMinRow(){
        return this.minRow;
    }

    public int getMinColumn(){ return this.minColumn;}

    public int getVacuumDirection() {return this.vacuumDirection;}

    public int getDirection() { return this.direction;}

    public int getVaccumDirectionNumber(String inpDirection){
        if (inpDirection.compareTo("North")==0) {return 0;}
        if (inpDirection.compareTo("West")==0)  {return 1;}
        if (inpDirection.compareTo("South")==0) {return 2;}
        if (inpDirection.compareTo("East")==0) {return 3;}
        return -1; // unknown direction
    }
    /**
     * Purpose to get the value of simulationStatus
     * Author Marie Held
     * @return the simStatus value
     */
    public int getSimStatus(){
        return this.simulationStatus;
    }



    public int getNumSimTilesComponent () {return numSimTilesComponent;}

    /* *   Tile Array Layout
     *      MinRow, MinColumn, MaxRow, MaxColumn are set in the staticVariable class
     *      North (direction = 0)
     *      West  (direction = 1)
     *      South (direction = 2)
     *      East  (direction = 3)
     *
     *                                      North
     *           | -------------------------------------------------------------------------------- |
     *           |  [MinRow][MinColumn]         [MinRow][n Column]          [MinRow][MaxColumn      |
     *    West   |  [MinRow +1 ][MinColumn]     [MinRow +1 ][n Column]      [MinRow +1 ][MaxColumn] |                                                |     East
     *           |     ...                                   ...                    ...             |
     *           |  [MaxRow -1 ][MinColumn]       [MinRow -1 ][n Column]    [MaxRow -1 ][Maxolumn]  |
     *           |  [MinRow][MinColumn]          [MacRow][n Column]           [MaxRow][MaxColumn]   |
     *           |----------------------------------------------------------------------------------|
     *                                South
     */

 /*
    Developer's please read
    In Mathematics a graph has 4 coordinate planes ([positive x, positive y],[negative x, positive Y],
                                                    [positive x, negative y],[negative x, negative y])
       the origin point (0,0) is at the bottom left corner of the [positive x, positive y] coordinate system
    In Computer Science arrays row 0, column 0 in in the upper left corner

    The arrays in this program uses computer science row, column methodology
    Values in Tile Array are populated with row, columns value.
    So the point of origin is in the top left corner.
    Hence when x increases it is going south.  When y increases it is going east.
    Similarly when x decreases it is going north and when y decreases it is going west.
    Row 0 (minRow) is the top border.
    Column 0  (minColumn) is the west border.
    Row maxRows is the south border
    Column maxColumns is the east border.

  */

    public String getVaccumDirectionName(int inpDirection){
        if (inpDirection == 0) {return "North";}
        if (inpDirection == 1) {return "West";}
        if (inpDirection == 2) {return "South";}
        if (inpDirection == 3) {return "East";}
        return "Unknown";
    }


    // setters

    // Note: there are no setters for minimum and maximum values for the rows and columns
    //       because these should NOT be changed during a running program
    //       Changing these values will cause unknown harm to the program

    /**
     * Purpose: To set the direction for the vacuum to use when cleaning
     * @param inpDirection either North,South,East, or West
     *
     * //    North (direction = 0)
     * //    West  (direction = 1)
     * //    South (direction = 2)
     * //    East  (direction = 3)
     */
    public void setVacuumDirection(String inpDirection){
        if (inpDirection.compareTo("North")==0) {this.vacuumDirection = 0;}
        if (inpDirection.compareTo("West")==0)  {this.vacuumDirection = 1;}
        if (inpDirection.compareTo("South")==0) {this.vacuumDirection = 2;}
        if (inpDirection.compareTo("East")==0) {this.vacuumDirection = 3;}
    }


    /**
     * Purpose to set the value of simulationStatus
     * Author Marie Held
     * @param inpSimStatus (0 is running; 1 is user force stop; 2 is finished)
     */
    public void setSimStatus(int inpSimStatus){
        this.simulationStatus = inpSimStatus;
    }

    public void setSimStatus (String inpStatus){
        if ( inpStatus.compareTo("Finish")==0) {this.simulationStatus = 2;}
    }

    /**
     * Purpose to set the value of direction -- used in wall folloing algorithm
     * Author Marie Held
     */
    public int setDirection(int inpDirection){
        return this.direction = inpDirection;

    }

    public int setDirection(String inpDirection){

        if (inpDirection.compareTo("North")==0) {return this.direction = 0;}
        if (inpDirection.compareTo("West")==0)  {return this.direction = 1;}
        if (inpDirection.compareTo("South")==0) {return this.direction = 2;}
        if (inpDirection.compareTo("East")==0) {return this.direction = 3;}
        return -1; // Unknown direction which is an error condition
    }


}
