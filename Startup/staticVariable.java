package Startup;

public class staticVariable {

    private int maxRow = 10;
    private int maxColumn = 10;

    // default constructor
    public staticVariable(){
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
    }

    //getters
    public int getMaxRow(){
        return this.maxRow;
    }

    public int getMaxColumn() {
        return maxColumn;
    }
}
