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
    private Location priorStart;
    private int startX;
    private int startY;
    private int circuit;

    // Default Constuctor
    public AlgorithmWallFollow(SimulationLayoutGUI inpsimulationlayout, TileArray inpTA, Vacuum inpVacuum,
                               staticVariable inpGlobal) {
        this.wfSimulationLayout = inpsimulationlayout;
        this.wfTileArray = inpTA;
        this.wfVacuum = inpVacuum;
        this.wfCleanValue = 1.0;
        this.wfMaxRows=inpTA.getLength();
        this.wfMaxColumns = inpTA.getWidth();
        int vacX = this.wfVacuum.getX();
        int vacY = this.wfVacuum.getY();
        this.global = inpGlobal;
        this.direction = global.getVacuumDirection();
        this.priorStart = new Location(0,0);
        this.startX = 0;
        this.startY = 0;
        this.circuit = 0;


    }  // end of default constructor for AlgorithmWallFollow

    // setters
    public void setPriorStart(Location inpPriorStart) {this.priorStart = inpPriorStart;}
    public void setStartX(int inpX) {this.startX = inpX;}
    public void setStartY(int inpY) {this.startY = inpY;}

    // getters
    public Location getPriorStart() {return this.priorStart;}
    public int getStartX() {return this.startX;}
    public int getStartY() {return this.startY;}



    /**
     * Purpose: To find the nearest wall
     * @return: the nearest wall tile
     */
    public Tile findNearestWall(){
        Tile retTile = new Tile();
        Tile currentTile = new Tile();
     //   Location vacLoc = new Location(0,0);
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
        if (tileType == 1){ // empty tile can now start the vacuum
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            currentTile = emptyTile(vacX, vacY, currentTile);
         //   wfVacuum.setX(vacX);
         //   wfVacuum.setY(vacY);
          //  wfVacuum.setTileLocation();
            //setStartX(vacX);
        //    setStartY(vacY);
            return currentTile;
        }
        else if (tileType == 3) {
            // Check for if the wall is a border wall
            if (vacX == 0) {  // wall is on the top
                if (vacY == 0) {  // top left corner
                    currentTile = topLeftCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    /*
                    wfVacuum.setX(vacX);
                    wfVacuum.setY(vacY);
                    wfVacuum.setTileLocation();
                    setStartX(vacX);
                    setStartY(vacY);

                     */
                    return currentTile;
                } // end of top left y
            } // end of top left

            if (vacY == wfMaxColumns - 1) { // top right corner
                if (vacX == 0) {
                    currentTile = topRightCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    /*
                    wfVacuum.setX(vacX);
                    wfVacuum.setY(vacY);
                    wfVacuum.setTileLocation();
                    setStartX(vacX);
                    setStartY(vacY);

                     */
                    return currentTile;
                }
            }
            if (vacX == wfMaxRows - 1) {  // wall is on the bottom
                if (vacY == wfMinColumns) {  // bottom right corner
                    currentTile = bottomLeftCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    /*
                    wfVacuum.setX(vacX);
                    wfVacuum.setY(vacY);
                    wfVacuum.setTileLocation();
                    setStartX(vacX);
                    setStartY(vacY);

                     */
                    return currentTile;

                }
            }
            if (vacX == wfMaxRows - 1) {  // wall is on the bottom
                if (vacY == wfMaxColumns - 1) {  // bottom right corner
                    currentTile = bottomRightCorner(vacX, vacY, currentTile); // find the tile nearest to a wall that is cleanable
                    /*
                    wfVacuum.setX(vacX);
                    wfVacuum.setY(vacY);
                    wfVacuum.setTileLocation();
                    setStartX(vacX);
                    setStartY(vacY);
*/
                    return currentTile;
                }
            }
        else { // incoming tile is not a wall

            System.out.println("in the else statement of incoming tile is not a wall");

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
            wfVacuum.setX(inpVacX);
            wfVacuum.setY(inpVacY);
            wfVacuum.setTileLocation();
            setStartX(inpVacX);
            setStartY(inpVacY);
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
            wfVacuum.setX(inpVacX);
            wfVacuum.setY(inpVacY);
            wfVacuum.setTileLocation();
            this.startX = inpVacX;
            this.startY = inpVacY;
            return inpCurrentTile;
        } else {
            inpVacX = westTileLoc.getLocX();
            inpVacY = westTileLoc.getLocY();
            inpCurrentTile = wfTileArray.getTile(inpVacX, inpVacY);
            wfTileArray.setTileClean(inpVacX, inpVacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("East");
            direction = global.getVacuumDirection();
            wfVacuum.setX(inpVacX);
            wfVacuum.setY(inpVacY);
            wfVacuum.setTileLocation();
            setStartX(inpVacX);
            setStartY(inpVacY);
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
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        } else { // go down and over to get see if get a tile that is available and determine which has a closer wall
            tempTileX = vacX;
            tempTileY = vacY;
            for (int i = vacX + 1; i < wfMaxRows; i++) {
                southTilesCount++;
                currentTile = wfTileArray.getTile(i, vacY);
                if (currentTile.isCleanable()) {
                    southTileLoc.setLocation(i, vacY);
                    setStartX(vacX);
                    setStartY(vacY);
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
                    setStartX(vacX);
                    setStartY(vacY);
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
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        } else {
            vacX = westTileLoc.getLocX();
            vacY = westTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("West");
            direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
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
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        } else {
            tempTileX = vacX;
            tempTileY = vacY;
            for (int i = vacY + 1; i < wfMaxColumns; i++) {
                eastTilesCount++;
                currentTile = wfTileArray.getTile(vacX, i);
                if (currentTile.isCleanable()) {
                    eastTileLoc.setLocation(vacX, i);
                    setStartX(vacX);
                    setStartY(vacY);
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
                    setStartX(vacX);
                    setStartY(vacY);
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
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        } else {
            vacX = eastTileLoc.getLocX();
            vacY = eastTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("West");
            direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
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
            setStartX(vacX);
            setStartY(vacY);
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
                    setStartX(vacX);
                    setStartY(vacY);
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
                    setStartX(vacX);
                    setStartY(vacY);
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
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        } else {
            vacX = westTileLoc.getLocX();
            vacY = westTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("West");
            direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        }

    }  // end of bottom RightCorner method

    public Tile emptyTile (int vacX, int vacY, Tile currentTile){
        int tempTileX = vacX;
        int tempTileY = vacY;
        int northTilesCount = 0;
        int southTilesCount = 0;
        int eastTilesCount = 0;
        int westTilesCount = 0;

        Location northTileLoc = new Location(0, 0);
        Location southTileLoc = new Location(0, 0);
        Location eastTileLoc = new Location(0, 0);
        Location westTileLoc = new Location(0, 0);

        // find the north wall vacX = wfMinColumns
        if (vacX == wfMinRows + 1){ // next to top border wall
            northTilesCount = 0;
        }
        else {
            int i = vacX;
            do {
                northTilesCount++;
                currentTile = wfTileArray.getTile(i,vacY);
                if (currentTile.getType() == 3) {
                    i++;        // need to move down a row because at the border
                    northTileLoc.setLocation(i,vacY);
                    setStartX(vacX);
                    setStartY(vacY);
             //       System.out.print("North tile location is: ");
              //      northTileLoc.printLocation();
                //    System.out.println();
                    break;
                }
                i--;
            } while (i != wfMinRows -1 ); // if equal ot minRows then the do loop exits prematurely
        }
        // find the south wall vacX = wfMaxRows - 1
        vacX = tempTileX;
        vacY = tempTileY;
        if (vacX == wfMaxRows - 1){ // next to top border wall
            southTilesCount = 0;
        }
        else {
            int i = vacX - 1;
            do {
                southTilesCount++;
                currentTile = wfTileArray.getTile(i,vacY);
                if (currentTile.getType() == 3) {
                   southTileLoc.setLocation(i-1,vacY); // vaccum can't go on the wall set to tile one up i.e. off wall
           //         System.out.print("South tile location is: ");
           //         southTileLoc.printLocation();
           //         System.out.println();
                    setStartX(vacX);
                    setStartY(vacY);
                    break;
                }
                i++;
            } while (i != wfMaxRows);
        }

        // find the east wall wfMaxColumns - 1
        vacX = tempTileX;
        vacY = tempTileY;
        if (vacY == wfMaxRows - 1){ // next to top border wall
            eastTilesCount = 0;
        }
        else {
            int i = vacY + 1;
            do {
                eastTilesCount++;
                currentTile = wfTileArray.getTile(vacX,i);
                if (currentTile.getType() == 3) {
                    eastTileLoc.setLocation(vacX,i);
         //           System.out.print("East tile location is: ");
         //           eastTileLoc.printLocation();
         //           System.out.println();
                    setStartX(vacX);
                    setStartY(vacY);
                    break;
                }
                i++;
            } while (i != wfMaxColumns);
         //   System.out.println("East Tile Count is: " +eastTilesCount);
        }
        // find the west wall vacY = wfMinColumns
        vacX = tempTileX;
        vacY = tempTileY;
        if (vacY == 1){ // next to top border wall
            westTilesCount = 0;
        }
        else {
            int i = vacY - 1;
            do {
                westTilesCount++;
                currentTile = wfTileArray.getTile(vacX,i);
                if (currentTile.getType() == 3) {
                    westTileLoc.setLocation(vacX,i);
       //             System.out.print("West tile location is: ");
        //            westTileLoc.printLocation();
         //           System.out.println();
                    setStartX(vacX);
                    setStartY(vacY);
                    break;
                }
                i++;
            } while (i != wfMinColumns);
        }

     //System.out.println("North Tiles Count = " + northTilesCount);
     //System.out.println("South Tiles Count = " + southTilesCount);
     //System.out.println("East Tiles Count = " + eastTilesCount);
     //System.out.println("West Tiles Count = " + westTilesCount);

        // find which wall (North, South, East, West is closest
        if (northTilesCount <= southTilesCount && northTilesCount <= eastTilesCount && northTilesCount <= westTilesCount){
            System.out.println("North Wall is closer");
            vacX = northTileLoc.getLocX();
            vacY = northTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("North");
            direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        }
        else if(southTilesCount <= northTilesCount && southTilesCount <= eastTilesCount && southTilesCount <= westTilesCount){
            System.out.println("South Wall is closer");
            vacX = southTileLoc.getLocX();
            vacY = southTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("South");
            direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        }
        else if((eastTilesCount <= northTilesCount) && (eastTilesCount <= southTilesCount) && (eastTilesCount <= westTilesCount)){
            System.out.println("East Wall is closer");
            vacX = eastTileLoc.getLocX();
            vacY = eastTileLoc.getLocY() - 1; // come in one tile from east wall
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("East");
            direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        }
        else if(westTilesCount <= northTilesCount && westTilesCount <= southTilesCount && westTilesCount <= eastTilesCount){
            System.out.println("West Wall is closer");
            vacX = westTileLoc.getLocX();
            vacY = westTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("West");
            direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            return currentTile;
        }
        return currentTile;
    }

    /**
     * @Author: Marie Held
     * @Purpose: To clean the incoming tile and determine what the next tile to be cleaned is
     * @param inpTile The tile that will be cleaned
     * @return nextTile The next tile that needs to be cleaned
     */

    public Tile nextTileToBeClean(Tile inpTile){
        Tile nextTile;
        nextTile = inpTile;
        int x = wfVacuum.getX();
        int y = wfVacuum.getY();
        wfVacuum.setTileLocation();
        Location nextTileLoc;
//        int maxRows = wfMaxRows;
//        int minRows = wfMinRows;
/*
        if (x == this.startX){
            if (y == this.startY){
                this.circuit++;
                if (circuit > 1) {
                    System.out.println("At the prior start position " + circuit);
                   // maxRows--;
                   // minRows++;
                    if (global.getVacuumDirection() == 0) {
                        y--;
                        wfVacuum.setY(y);
                    }
                    if (global.getVacuumDirection() == 1) {
                        y--;
                        wfVacuum.setY(y);
                    }
                    if (global.getVacuumDirection() == 2) {
                        y++;
                        wfVacuum.setY(y);
                    }
                    if (global.getVacuumDirection() == 3) {
                        y++;
                        wfVacuum.setY(y);
                    }
                }
            }
        }
*/
        if (global.getVacuumDirection() == 0){  // Going North
            x--; // move one tile north
            wfVacuum.setX(x);
            /*
            if (x < minRows) {
                wfVacuum.setX(x);
            }
            else{
                x = minRows;
                wfVacuum.setX(x);
            }
            */
            nextTile = wfTileArray.getTile(x,y);
            if (nextTile.getType() != 3) { // nextTile is a wall
                wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                //System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                //    wfTileArray.printTileArray();
                return nextTile;
            }else {
                global.setVacuumDirection("West");
                x++;
                y--;
                wfVacuum.setX(x);
                wfVacuum.setY(y);
                nextTile = wfTileArray.getTile(x,y);
                //System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                wfTileArray.setTileClean(x, y++, wfCleanValue - 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x,y--);
                return nextTile;
            }
        }
        if (global.getVacuumDirection() == 1) {  // Going West
            //x++; // move one tile north
            //wfVacuum.setX(x);
            y--;
            wfVacuum.setY(y);
            nextTile = wfTileArray.getTile(x,y);
            if (nextTile.getType() != 3) { // nextTile is a wall
                wfTileArray.setTileClean(x, y++, wfCleanValue- 0.1, wfSimulationLayout);
                //System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection() );
                //    wfTileArray.printTileArray();
                return nextTile;
            }else {
               global.setVacuumDirection("South");
               x++;
               y++;
                wfVacuum.setX(x);
                wfVacuum.setX(y);
                nextTile = wfTileArray.getTile(x,y);
                //System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                // change direction to east
                wfTileArray.setTileClean(x, y++, wfCleanValue- 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x,y);
                return nextTile;
            }
        }
        else if (global.getVacuumDirection() == 2){  // Going South
            x++; // move one tile north
            wfVacuum.setX(x);
            if(y == 0 ) {
                y = 1;
                wfVacuum.setY(y);
            }
            nextTile = wfTileArray.getTile(x,y);
            if (nextTile.getType() != 3) { // nextTile is a wall
                wfTileArray.setTileClean(x++, y, wfCleanValue- 0.1, wfSimulationLayout);
                //System.out.println("X is: "+ x + " Y is: " + y +  "Direction is " + global.getVacuumDirection());
            //    wfTileArray.printTileArray();
                return nextTile;
            }else {
                global.setVacuumDirection("East");
                x--; // reset x to it's prior value
                wfVacuum.setX(x);
                nextTile = wfTileArray.getTile(x,y);
                //System.out.println("X is: "+ x + " Y is: " + y);
               // change direction to east
                wfTileArray.setTileClean(x, y++, wfCleanValue- 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x,y);
                return nextTile;
                }
            }
            else if (global.getVacuumDirection() == 3) {  // Going East
                 y++;
                 wfVacuum.setY(y);
                 nextTile = wfTileArray.getTile(x, y);
                //System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                if (nextTile.getType() != 3) { // nextTile is a wall
                    wfTileArray.setTileClean(x, y, wfCleanValue- 0.1, wfSimulationLayout);
                   // System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                    nextTile = wfTileArray.getTile(x,y++);
                    return nextTile;
                } else {
                    global.setVacuumDirection("North");
                    x--; // reset x to it's prior value
                    y--;
                    wfVacuum.setX(x);
                    wfVacuum.setY(y);
                    nextTile = wfTileArray.getTile(x, y);
                    // change direction to east
                    wfTileArray.setTileClean(x, y, wfCleanValue- 0.1, wfSimulationLayout);
                    //System.out.println("X is: "+ x + " Y is: " + y);
                    return nextTile;
                }
            }if (global.getVacuumDirection() == 2){  // Going South
            x++; // move one tile north
            wfVacuum.setX(x);
            nextTile = wfTileArray.getTile(x,y);
            if (nextTile.getType() != 3) { // nextTile is a wall
                wfTileArray.setTileClean(x++, y, wfCleanValue- 0.1, wfSimulationLayout);
                //System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                //    wfTileArray.printTileArray();
                return nextTile;
            }else {
                global.setVacuumDirection("East");
                x--; // reset x to it's prior value
                wfVacuum.setX(x);
                nextTile = wfTileArray.getTile(x,y);
                //System.out.println("X is: "+ x + " Y is: " + y);
                // change direction to east
                wfTileArray.setTileClean(x, y++, wfCleanValue- 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x,y);
                return nextTile;
            }
        }

        return nextTile;
    }
} // end of class AlgorithmWallFollow
