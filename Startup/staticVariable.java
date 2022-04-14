package Startup;

public class staticVariable {

    private int maxRow = 10;
    private int maxColumn = 10;
    private int minRow = 0;
    private int minColumn = 0;
    private int vacuumDirection =0;

    // default constructor
    public staticVariable(){
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
        this.minRow = 0;
        this.minColumn = 0;
        this.vacuumDirection = 0;

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

    // setters

    // Note: there are no setters for minimum and maximum values for the rows and columns
    //       because these should NOT be changed during a running program
    //       Changing these values will cause unknown harm to the program

    /**
     * Purpose: To set the direction for the vacuum to use when cleaning
     * @param inpDirection either North,South,East, or West
     * @return
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
}
