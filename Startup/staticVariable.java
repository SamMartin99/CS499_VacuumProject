package Startup;

public class staticVariable {

    private int maxRow;
    private int maxColumn;
    private int minRow;
    private int minColumn;
    private int vacuumDirection;

    // default constructor Note initial values have already been set
    public staticVariable(){
        this.maxRow = 44;
        this.maxColumn = 44;
        this.minRow = 0;
        this.minColumn = 0;
        this.vacuumDirection =0;
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

    public int getVaccumDirectionNumber(String inpDirection){
        if (inpDirection.compareTo("North")==0) {return 0;}
        if (inpDirection.compareTo("West")==0)  {return 1;}
        if (inpDirection.compareTo("South")==0) {return 2;}
        if (inpDirection.compareTo("East")==0) {return 3;}
        return -1; // unknown direction
    }

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
     * Purpose: Get the next North value
     * @param x Current x value
     * @return x New x value -1 indicates that new x would either less than minRows
     */
    public int moveNorth(int x) {
        int newX;
        newX = x--;
        if (newX <= minRow) {
            return newX;
        } else {
          newX = -1;
          return newX;
        }
    }

    /**
     * Purpose: Get the next North for moving n tiles value
     * @param x Current x value
     * @param n The number of tiles to move North
     * @return x New x value; -1 indicates that new x would either less than minRows
     */
    public int multipleMovesNorth(int x, int n){
        int newX = x;
        for (int i = x; i == n; i++)
            newX = x--;
        if (newX <= minRow) {
            return newX;
        } else {
            newX = -1;
            return newX;
        }
    }
    /**
     * Purpose: Get the next South value
     * @param x Current x value
     * @return x New x value -1 indicates that new x would either less than maxRows
     */
    public int moveSouth(int x) {
        int newX;
        newX = x++;
        if (newX < maxRow) {
            return newX;
        } else {
            newX = -1;
            return newX;
        }
    }

    /**
     * Purpose: Get the next South for moving n tiles value
     * @param x Current x value
     * @param n The number of tiles to move North
     * @return x New x value; -1 indicates that new x would either less than minRows
     */
    public int multipleMovesSouth(int x, int n){
        int newX = x;
        for (int i = x; i == n; i++)
            newX = x++;
        if (newX < maxRow) {
            return newX;
        } else {
            newX = -1;
            return newX;
        }
    }

    /**
     * Purpose: Get the next East value
     * @param y Current y value
     * @return y New y value -1 indicates that new x would either greater than or equal to maxColumns
     */
    public int moveEast(int y) {
        int newY;
        newY = y++;
        if (newY >= maxColumn) {
            return newY;
        } else {
            newY = -1;
            return newY;
        }
    }

    /**
     * Purpose: Get the next East for moving n tiles
     * @param y Current y value
     * @param n The number of tiles to move East
     * @return y New y value; -1 indicates that new y would equal to or greater than maxColumns
     */
    public int multipleMovesEast0(int y, int n){
        int newY = y;
        for (int i = y; i == n; i++)
            newY = y++;
        if (newY  >= maxColumn) {
            return newY;
        } else {
            newY = -1;
            return newY;
        }
    }
    /**
     * Purpose: Get the next West value
     * @param y Current y value
     * @return y New y value -1 indicates that new y would be less than minColumns
     */
    public int moveWest(int y) {
        int newY;
        newY = y--;
        if (newY >= minColumn) {
            return newY;
        } else {
            newY = -1;
            return newY;
        }
    }

    /**
     * Purpose: Get the next South for moving n tiles value
     * @param x Current x value
     * @param n The number of tiles to move North
     * @return x New x value; -1 indicates that new x would either less than minRows
     */
    public int multipleMovesWest(int x, int n){
        int newX = x;
        for (int i = x; i == n; i++)
            newX = x++;
        if (newX < maxRow) {
            return newX;
        } else {
            newX = -1;
            return newX;
        }
    }

}
