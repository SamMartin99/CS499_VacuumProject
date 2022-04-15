package Model;

import Startup.Location;
import Startup.houseTile;

// Author: Guess Crow
// This class is for representing a grouping of 4 tiles in a 2x2 box, so that any tiles within a table group can be recognized accordingly as one object or set of related tiles
public class Table {
    houseTile topLeft;
    houseTile topRight;
    houseTile bottomLeft;
    houseTile bottomRight;

    // Constructor takes in an array of houseTile's to access, and the location of the houseTile that was clicked
    public Table(houseTile[][] arr, Location loc) {
        // Set the appropriate houseTiles to be this table's fields
        topLeft = arr[loc.x][loc.y];
        topRight = arr[loc.x+1][loc.y];
        bottomLeft = arr[loc.x][loc.y+1];
        bottomRight = arr[loc.x+1][loc.y+1];

        // set the appropriate type for the 2x2 tiles in the table
        topLeft.getTile().setType(6);
        topRight.getTile().setType(6);
        bottomLeft.getTile().setType(6);
        bottomRight.getTile().setType(6);

        // Update the containing table for each houseTile in this table
        topLeft.containingTable = this;
        topRight.containingTable = this;
        bottomLeft.containingTable = this;
        bottomRight.containingTable = this;

        // Have them update their imageIcons appropriately
        topLeft.setImageIcon();
        topRight.setImageIcon();
        bottomLeft.setImageIcon();
        bottomRight.setImageIcon();
    }

    // Checks if the given location is within this table. Can't use a switch due to type limitations
    public boolean IsInTable(Location loc)
    {
        if (loc == topLeft.GetLocation())
            return true;
        else if (loc == topRight.GetLocation())
            return true;
        else if (loc == bottomLeft.GetLocation())
            return true;
        else if (loc ==  bottomRight.GetLocation())
            return true;

        return false;
    }
    // We don't need any setter methods since all tiles should only be set at one time when table is created

    // This method deletes the table by settings all its members to plain, should be invoked whenever any part of the table is overwritten
    // You pass this method whatever tile was changed/clicked on/overwritten
    public void DeleteTable()
    {
        // Set all tiles within this table to plain
        topLeft.getTile().setType(1);
        topRight.getTile().setType(1);
        bottomLeft.getTile().setType(1);
        bottomRight.getTile().setType(1);

        // Set all these houseTile's "containingTable" fields to null, since they are no longer part of a table
        topLeft.containingTable = null;
        topRight.containingTable = null;
        bottomLeft.containingTable = null;
        bottomRight.containingTable = null;

        // Have them all update their image icon
        topLeft.setImageIcon();
        topRight.setImageIcon();
        bottomLeft.setImageIcon();
        bottomRight.setImageIcon();
    }
}
