package Model;

import Startup.Location;
import Startup.staticVariable;
import View.SimulationLayoutGUI;

import javax.lang.model.element.Name;
import java.util.Locale;

/**
 * Purpose: Vaccum cleaner cleans the floor by following the wall
 *
 */
public class AlgorithmWallFollow {
    // Attributes
    private SimulationLayoutGUI wfSimulationLayout;
    private TileArray wfTileArray;
    private Vacuum wfVacuum;
    private double wfCleanValue;
    private int wfMaxRows;
    private int wfMaxColumns;
    private int wfMinRows;
    private int wfMinColumns;
    private int direction;
    private staticVariable global;

    // Default Constuctor
    public AlgorithmWallFollow(SimulationLayoutGUI inpsimulationlayout, TileArray inpTA, Vacuum inpVacuum,
                               staticVariable inpGlobal) {
        this.wfSimulationLayout = inpsimulationlayout;
        this.wfTileArray = inpTA;
        this.wfVacuum = inpVacuum;
        this.wfCleanValue = 0.0;
        this.wfMaxRows=inpTA.getLength();
        this.wfMaxColumns = inpTA.getWidth();
        int vacX = this.wfVacuum.getX();
        int vacY = this.wfVacuum.getY();
        this.global = inpGlobal;
        this.direction = global.getVacuumDirection();

    }  // end of default constructor for AlgorithmWallFollow

    /**
     * Purpose: To find the nearest wall
     * @return: the nearest wall tile
     */
    public Tile findNearestWall(){
        Tile retTile = new Tile();
        Tile currentTile = new Tile();
        Location vacLoc = new Location(0,0);
        int vacX, initialVacX;
        int vacY, initialVaxY;

        int tileType;

        // find the location of the vacuum
        vacX = this.wfVacuum.getX();
        vacY = this.wfVacuum.getY();
        initialVacX = vacX;
        initialVaxY = vacY;

        Location southTileLoc = new Location(initialVacX,initialVaxY);
        Location eastTileLoc = new Location(initialVacX,initialVaxY);

/*   Tile Array Layout
 *      MinRow, MinColumn, MaxRow, MaxColumn are set in the staticVariable class
 *      North (direction = 0)
 *      West  (direction = 1)
 *      South (direction = 2)
 *      East  (direction = 3)
 *
 *                                      North
 *           | -----------------------------------------------------------|
 *           |                 MinColumn             Max Column           |
 *           |     MinRow  [MinRow][MinRow]         [MinRow][MaxColumn    |
 *    West   |     1                                                      |     East
 *           |     2                                                      |
 *           |     3                                                      |
 *           |  MaxRow  [MinRow][MaxRow]            [MaxRow][MaxColumn]   |
 *           |------------------------------------------------------------|
 *                                South
 */
        vacX = wfVacuum.getX();
        vacY=  wfVacuum.getY();
        currentTile = wfTileArray.getTile(vacX,vacY);
        tileType = currentTile.getType();
        if (tileType == 3) {
            // Check for if the wall is a border wall
            if (vacX == 0) {  // wall is on the top
                if (vacY == 0) {  // top left corner
                    currentTile = topLeftCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    return currentTile;
                } // end of top left y
            } // end of top left

            if (vacY == wfMaxColumns - 1) { // top right corner
                if (vacX == 0) {
                    currentTile = topRightCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    return currentTile;
                }
            }
            if (vacX == wfMaxRows - 1) {  // wall is on the bottom
                if (vacY == wfMinColumns) {  // bottom right corner
                    currentTile = bottomLeftCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    return currentTile;
                }
            }
            if (vacX == wfMaxRows - 1) {  // wall is on the bottom
                if (vacY == wfMaxColumns - 1) {  // bottom right corner
                    currentTile = bottomRightCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    return currentTile;
                }
            }
        }

      return retTile;

    } // end of method findNearestWall

    /**
     * Purpose is to isolate the code for handling the nearest wall when vacuum is placed in upper left corner (0,0)
     * @param inpVacX integer represting the vacuum's current x value
     * @param inpVacY integer represting the vacuum's current y value
     * @param inpCurrentTile a Tile object reperesting the current tile that the vaccum is on
     */
    public Tile topLeftCorner(int inpVacX, int inpVacY, Tile inpCurrentTile){

        int initialTileX ;
        int southTilesCount = 0;
        int westTilesCount = 0;
        Location southTileLoc = new Location(0,0);
        Location westTileLoc = new Location(0,0);

       // System.out.println("in the topLeftCorner method");
        inpVacX++;
        inpVacY++;
        inpCurrentTile = wfTileArray.getTile(inpVacX, inpVacY);
        if (inpCurrentTile.isCleanable()) {  // diagonal corner is available
            wfTileArray.setTileClean(inpVacX, inpVacX, wfCleanValue, wfSimulationLayout);
            return inpCurrentTile;
        } else { // go down and over to get see if get a tile that is available and determine which has a closer wall
            initialTileX = inpVacX;  // will need to use the original value passed into the method
            for (int i = inpVacX + 1; i < wfMaxRows; i++) {
                southTilesCount++;
                inpCurrentTile = wfTileArray.getTile(i, inpVacY);
                if (inpCurrentTile.isCleanable()) {
                    southTileLoc.setLocation(i, inpVacY);
                    break;
                }
            }

            inpVacX = initialTileX; // need to reset vacX back to value after the first tile check
            for (int i = inpVacY + 1; i < wfMaxColumns; i++) {
                westTilesCount++;
                inpCurrentTile = wfTileArray.getTile(inpVacX, i);
                if (inpCurrentTile.isCleanable()) {
                    westTileLoc.setLocation(inpVacX, i);
                    break;
                }
            }
        }

        // move to tile with the shortest number of tiles to transverse
        //System.out.println("SouthTileCount is " + southTilesCount);
        //System.out.println("EastTileCount is " + eastTilesCount);
        if (southTilesCount >= westTilesCount) {
            inpVacX = southTileLoc.getLocX();
            inpVacY = southTileLoc.getLocY();
            inpCurrentTile = wfTileArray.getTile(inpVacX, inpVacX);
            wfTileArray.setTileClean(inpVacX, inpVacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("South");
            direction = global.getVacuumDirection();
            return inpCurrentTile;
        } else {
            inpVacX = westTileLoc.getLocX();
            inpVacY = westTileLoc.getLocY();
            inpCurrentTile = wfTileArray.getTile(inpVacX, inpVacY);
            wfTileArray.setTileClean(inpVacX, inpVacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("East");
            direction = global.getVacuumDirection();
            return inpCurrentTile;
        }  // finish checking for nearest wall for top left corner

        } // end of method topLeftCorner

    public Tile topRightCorner(int vacX, int vacY, Tile currentTile) {
        int tempTileX;
        int tempTileY;
        int southTilesCount = 0;
        int westTilesCount = 0;
        Location southTileLoc = new Location(0, 0);
        Location westTileLoc = new Location(0, 0);


        //   System.out.println("in the "+ getClass().getName() );
        vacY--;
        vacX++;
        currentTile = wfTileArray.getTile(vacX, vacY);
        if (currentTile.isCleanable()) {  // diagonal corner is available
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            return currentTile;
        } else { // go down and over to get see if get a tile that is available and determine which has a closer wall
            tempTileX = vacX;
            tempTileY = vacY;
            for (int i = vacX + 1; i < wfMaxRows; i++) {
                southTilesCount++;
                currentTile = wfTileArray.getTile(i, vacY);
                if (currentTile.isCleanable()) {
                    southTileLoc.setLocation(i, vacY);
                    break;
                }
            }
            vacX = tempTileX;
            vacY = tempTileY; // need to reset vacX back to value after the first tile check
            int i = vacY;
            do {
               // System.out.println(i);
                westTilesCount++;
                currentTile = wfTileArray.getTile(vacX, i);
                if (currentTile.isCleanable()) {
                    westTileLoc.setLocation(vacX, i);
                    break;
                }
                i --;
            } while (i != wfMinColumns + 1);
        }
        // now determine if the south wall or west wall is closer
        if (southTilesCount >= westTilesCount) {
            vacX = southTileLoc.getLocX();
            vacY = southTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("South");
            direction = global.getVacuumDirection();
            return currentTile;
        } else {
            vacX = westTileLoc.getLocX();
            vacY = westTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("West");
            direction = global.getVacuumDirection();
            return currentTile;
        }

    } // end of topRightCorner method

    public Tile bottomLeftCorner(int vacX, int vacY, Tile currentTile) {
        int tempTileX;
        int tempTileY;
        int northTilesCount = 0;
        int eastTilesCount = 0;
        Location northTileLoc = new Location(0, 0);
        Location eastTileLoc = new Location(0, 0);
        vacY++;
        vacX--;
        //   System.out.println("vac x is " + vacX + "vac y is " + vacY);
        currentTile = wfTileArray.getTile(vacX, vacY);
        if (currentTile.isCleanable()) {  // diagonal corner is available
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            return currentTile;
        } else {
            tempTileX = vacX;
            tempTileY = vacY;
            for (int i = vacY + 1; i < wfMaxColumns; i++) {
                eastTilesCount++;
                currentTile = wfTileArray.getTile(vacX, i);
                if (currentTile.isCleanable()) {
                    eastTileLoc.setLocation(vacX, i);
                    break;
                }
            }
            // Just restoring to inputted values
            vacX = tempTileX;
            vacY = tempTileY;
            int i = vacX +1;
            do {
                //   System.out.println(i);
                northTilesCount++;
                currentTile = wfTileArray.getTile(i, vacY);
                if (currentTile.isCleanable()) {
                    northTileLoc.setLocation(i, vacY);
                    break;
                }
                i--;
            } while (i != wfMinRows + 1);
        }
        // now determine if the south wall or west wall is closer
        if (northTilesCount >= eastTilesCount) {
            vacX = northTileLoc.getLocX();
            vacY = northTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("North");
            direction = global.getVacuumDirection();
            return currentTile;
        } else {
            vacX = eastTileLoc.getLocX();
            vacY = eastTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("West");
            direction = global.getVacuumDirection();
            return currentTile;
        }

    }  // end of bottom Left Corner method

    public Tile bottomRightCorner(int vacX, int vacY, Tile currentTile) {
        int tempTileX;
        int tempTileY;
        int northTilesCount = 0;
        int westTilesCount = 0;
        Location northTileLoc = new Location(0, 0);
        Location westTileLoc = new Location(0, 0);
        int i; // loop control variable

        vacY--;
        vacX--;
        //   System.out.println("vac x is " + vacX + "vac y is " + vacY);
        currentTile = wfTileArray.getTile(vacX, vacY);
        if (currentTile.isCleanable()) {  // diagonal corner is available
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            return currentTile;
        } else {
            tempTileX = vacX;
            tempTileY = vacY;
            i = vacX - 1;
            do {
                //   System.out.println(i);
                northTilesCount++;
                currentTile = wfTileArray.getTile(i,vacY);
                if (currentTile.isCleanable()) {
                    northTileLoc.setLocation(i,vacY);
                    break;
                }
                i--;
            } while (i != wfMinRows+ 1);

            // Just restoring to inputted values
            vacX = tempTileX;
            vacY = tempTileY;
            i = vacY - 1;
            do {
                //   System.out.println(i);
                westTilesCount++;
                currentTile = wfTileArray.getTile(vacX, i);
                if (currentTile.isCleanable()) {
                    westTileLoc.setLocation(vacX,i);
                    break;
                }
                i--;
            } while (i != wfMinColumns + 1);
        }
        // now determine if the south wall or west wall is closer
        if (northTilesCount >= westTilesCount) {
            vacX = northTileLoc.getLocX();
            vacY = northTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("North");
            direction = global.getVacuumDirection();
            return currentTile;
        } else {
            vacX = westTileLoc.getLocX();
            vacY = westTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("West");
            direction = global.getVacuumDirection();
            return currentTile;
        }

    }  // end of bottom RightCorner method


} // end of class AlgorithmWallFollow
