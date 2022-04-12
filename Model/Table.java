package Model;

import Startup.Location;
import Startup.houseTile;

// Author: Guess Crow
// This class is for representing a grouping of 4 tiles in a 2x2 box, so that any tiles within a table group can be recognized accordingly as one object or set of related tiles
public class Table {
    // Has no fields for now, not needed

    // Constructor takes in an array of houseTile's to access, and the location of the houseTile that was clicked
    public Table(houseTile[][] arr, Location loc) {

        // set the appropriate type for the 2x2 tiles in the table
        arr[loc.x][loc.y].getTile().setType(6);
        arr[loc.x+1][loc.y].getTile().setType(6);
        arr[loc.x][loc.y+1].getTile().setType(6);
        arr[loc.x+1][loc.y+1].getTile().setType(6);

        // Have them update their imageIcons appropriately
        arr[loc.x][loc.y].setImageIcon();
        arr[loc.x+1][loc.y].setImageIcon();
        arr[loc.x][loc.y+1].setImageIcon();
        arr[loc.x+1][loc.y+1].setImageIcon();
    }

    // We don't need any setter methods since all tiles should only be set at one time when table is created

    // This method deletes the table by settings all its members to plain, should be invoked whenever any part of the table is overwritten
    // You pass this method whatever tile was changed/clicked on/overwritten
    public void DeleteTable(Tile changed)
    {
        // TODO: write code here to delete the table and set the tiles within it back to plain. This table object should be set to null elsewhere after calling this method
    }
}
