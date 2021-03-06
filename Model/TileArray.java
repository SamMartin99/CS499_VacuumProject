package Model;

/* Authors: Bryant Terry, Marie Held
 * Purpose: Represents entire house layout via collection of tiles.
 */

import Startup.Location;
import Startup.staticVariable;
import View.SimulationLayoutGUI;

public class TileArray {
    /*
     * length and width are used for determining how many Tiles need to be constructed.
     */
    private int length;
    private int width;
    private Tile[][] TA;
    public Location prevVacuumLoc = new Location (0, 0); // This is just used for the GUI, to remove old vacuum icons
    public Location vacuumStartLoc = null; // This holds the vacuum's starting position, defaults to null
    public boolean firstClick = true;
    public int clickOneX = 0;
    public int clickOneY = 0;
    public int clickTwoX = 0;
    public int clickTwoY = 0;

    /* Constructs TileArray.
     * Uses length and width to create a tile for every respective part of the array.
     */
    public TileArray(int length, int width) {

        this.length = length;
        this.width = width;
        TA = new Tile[this.length][this.width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                TA[i][j] = new Tile();
            }
        }

        setTile(0, 0, 3);  // set to a wall
    }

    public Tile getTile(int i, int j) {
        // if (j == -1) return TA[0][0]; // mh need to determine what is setting the -1 in y
        return TA[i][j];
    }

    public void setVacuum(Location loc) {
        // Sets the previous vacuum location, so its icon can be reverted to its true type
        if(vacuumStartLoc != null)
            prevVacuumLoc = vacuumStartLoc;
        // Sets the vacuum loc for other classes to see (since tiles don't keep their own locations as fields)
        vacuumStartLoc = loc;
    }

    public void setTile(int i, int j, int type) {
        TA[i][j].setType(type);
    }

    public void setTileClean(int i, int j, double clean_value, SimulationLayoutGUI inpsimulationLayout) {
        TA[i][j].setClean(clean_value);
        // update the GUI

        Location loc = new Location(i,j);
        String simTileName;
        String simOldTileName;
        int simTileNumber;
        int simOldTileNumber;
        int oldType = TA[prevVacuumLoc.getLocX()][prevVacuumLoc.getLocY()].getType();

        SimTileGUI simTileButton;
        Tile tileRef;

        simTileName = inpsimulationLayout.getSimTileName(i,j);
        simOldTileName = inpsimulationLayout.getSimTileName(prevVacuumLoc.getLocX(), prevVacuumLoc.getLocY());

        simTileNumber = inpsimulationLayout.getComponentNumber(simTileName);
        simOldTileNumber = inpsimulationLayout.getComponentNumber(simOldTileName);

        inpsimulationLayout.setVacuumTile(i,j,simTileNumber, simOldTileNumber, prevVacuumLoc, oldType);
    }

    public int getLength() {return this.length;}
    public int getWidth() {return  this.width;}

    public void printTileArray() {
        int numRows = this.length;
        int numColumns = this.width;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(TA[i][j].getType() + " ");
            }
            System.out.println();
        }
    }
}
