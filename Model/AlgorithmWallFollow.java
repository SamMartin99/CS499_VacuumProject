package Model;

import Startup.Location;
import Startup.staticVariable;
import View.SimulationLayoutGUI;

/**
 * Author Marie Held
 * Purpose: Vacuum cleaner cleans the floor by following the wall
 *
 */
public class AlgorithmWallFollow {
    // Attributes
    private SimulationLayoutGUI wfSimulationLayout;
    private TileArray wfTileArray;
    private Vacuum wfVacuum;
    private double wfCleanValue;
    private int wfMaxRows ;
    private int wfMaxColumns;
    private int wfMinRows;
    private int wfMinColumns;
     private staticVariable global;
    private int startX;
    private int startY;
    private int startDirection;
    private int circuit;
    private Tile currentTile;
    private int maxNumCircuits;
    private int circuitCheck;
    private int numWall; // the wall number in the circuit; 4 walls per circuit; set when corner occurs
    private int nearestWallDirction;


    // Default Constructor
    public AlgorithmWallFollow(SimulationLayoutGUI inpsimulationlayout, TileArray inpTA, Vacuum inpVacuum,
                               staticVariable inpGlobal) {
        this.wfSimulationLayout = inpsimulationlayout;
        this.wfTileArray = inpTA;
        this.wfVacuum = inpVacuum;
        this.wfCleanValue = 1.0;
        this.wfMaxRows = inpTA.getLength();
        this.wfMaxColumns = inpTA.getWidth();
        this.global = inpGlobal;
        this.wfMinRows=global.getMinRow();
        this.wfMinColumns=global.getMinColumn();
        this.startX = 0;
        this.startY = 0;
        this.circuit = 0;
         this.currentTile = new Tile();
        //    inpTA.printTileArray();
        this.maxNumCircuits = (wfMaxColumns ) / 2;
        this.circuitCheck = 0;
        this.numWall = 0;
        this.nearestWallDirction = 0;

    }  // end of default constructor for AlgorithmWallFollow


    public void setStartX(int inpX) {
        this.startX = inpX;
    }

    public void setStartY(int inpY) {
        this.startY = inpY;
    }

    /**
     * Purpose: To find the nearest wall
     * Author: Marie Held
     *
     * @return the nearest wall tile
     */
    public Tile findNearestWall() {
    //    Tile retTile = new Tile();
        int vacX, initialVacX;
        int vacY, initialVaxY;
        int tileType;

        // find the location of the vacuum
        vacX = this.wfVacuum.getX();
        vacY = this.wfVacuum.getY();
        initialVacX = vacX;
        initialVaxY = vacY;

        Location southTileLoc = new Location(initialVacX, initialVaxY);
        Location eastTileLoc = new Location(initialVacX, initialVaxY);

        currentTile = wfTileArray.getTile(vacX, vacY);
        tileType = currentTile.getType();
        if (tileType == 1) { // empty tile can now start the vacuum
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout); // show initial vacuum location
            currentTile = emptyTile(vacX, vacY, currentTile); // find the empty tile closest to a wall
            return currentTile;
            // now check for outside corners
        } else if (tileType == 3) {  // Wall
            // Check for if the wall is a border wall
            // corner wall is in the top left corner (0,0)
            if (vacX == wfMinRows) {
                if (vacY == wfMinColumns) {
                    //currentTile = topLeftCorner(vacX, vacY, currentTile); // find the tile nearest to the corner that is cleanable
                    currentTile = wfTileArray.getTile(wfMinRows + 1, wfMinColumns + 1);
                    return currentTile;
                } // end of top left y
            } // end of top left

            // Corner wall is top right (0, wfMaxColumns - 1)
            if (vacX == wfMinRows) {  // top right corner
                if (vacY == wfMaxColumns - 1) {
                    //currentTile = topRightCorner(vacX, vacY, currentTile); // find the tile nearest to the corner  that is cleanable
                    currentTile = wfTileArray.getTile(wfMinRows + 1, wfMaxColumns - 2);
                    return currentTile;
                }
            }

            // Corner wall is bottom right (wfMaxRows - 1, wfMaxColumns - 1)
            if (vacX == wfMaxRows - 1) {  // wall is on the bottom
                if (vacY == wfMinColumns) {  // bottom right corner
                    //currentTile = bottomLeftCorner(vacX, vacY, currentTile); // find the tile nearest to the corner  that is cleanable
                    currentTile = wfTileArray.getTile(wfMaxRows - 2, wfMaxColumns - 2); // -1 for array bounds ; another -1 to go up
                    return currentTile;
                }
            }

            // Corner wall is bottom left (wfMaxRows - 1, wfMinColumns)
            if (vacX == wfMaxRows - 1) {  // wall is on the bottom
                if (vacY == wfMinColumns) {  // bottom right corner
                    // currentTile = bottomRightCorner(vacX, vacY, currentTile); // find the tile nearest to the corner that is cleanable
                    currentTile = wfTileArray.getTile(wfMaxRows - 2, wfMinColumns + 1);
                    return currentTile;
                }
            }// else { // incoming tile is not a wall

            // System.out.println("in the else statement of incoming tile is not a wall");

            //}
        }
        currentTile = wfTileArray.getTile(initialVacX, initialVaxY);
        return currentTile;

    } // end of method findNearestWall

    /**
     * Purpose: To find an empty tile that is closest to a  wall
     * Author: Marie Held
     * @param vacX Where the vacuum has been placed in the x axis
     * @param vacY Where the vacuum has been placed in the y axis
     * @param currentTile The tile object that the vacuum was placed on
     * @return the tile that is closest to a wall
     */

    public Tile emptyTile(int vacX, int vacY, Tile currentTile) {
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
        // commented out the initial if because of warnings that variable had already been set to 0; and that if was an empty body
        //if (vacX == wfMinRows + 1) { // next to top border wall
           // northTilesCount = 0;
        //} else {
        if (vacX != wfMinRows + 1){
            int i = vacX;
            do {
                northTilesCount++;
                currentTile = wfTileArray.getTile(i, vacY);
                if (currentTile.getType() == 3) {
                    i++;        // need to move down a row because at the border
                    northTileLoc.setLocation(i, vacY);
                    setStartX(vacX);
                    setStartY(vacY);
                    //    this.startDirection = global.setDirection("West"); // closed to the north wall will be going west (counter clockwise)
                    //       System.out.print("North tile location is: ");
                    //      northTileLoc.printLocation();
                    //    System.out.println();
                    break;
                }
                i--;
            } while (i != wfMinRows - 1); // if equal ot minRows then the do loop exits prematurely
        }
        // find the south wall vacX = wfMaxRows - 1
        // commented out the initial if because of warnings that variable had already been set; and that if was an empty body
        //vacX = tempTileX;
        //vacY = tempTileY;
       // if (vacX == wfMaxRows - 1) { // next to top border wall
       //     southTilesCount = 0;
       // } else {
        if  (vacX != wfMaxRows - 1){
            int i = vacX - 1;
            do {
                southTilesCount++;
                currentTile = wfTileArray.getTile(i, vacY);
                if (currentTile.getType() == 3) {
                    southTileLoc.setLocation(i - 1, vacY); // vacuum can't go on the wall set to tile one up i.e. off wall
                    //         System.out.print("South tile location is: ");
                    //         southTileLoc.printLocation();
                    //         System.out.println();
                    setStartX(vacX);
                    setStartY(vacY);
                    // this.startDirection = global.setDirection("East"); // closed to the South wall will be going east (counterclockwise)
                    break;
                }
                i++;
            } while (i != wfMaxRows);
        }

        // find the east wall wfMaxColumns - 1
     //   vacX = tempTileX;
      //  vacY = tempTileY;
     //   if (vacY == wfMaxRows - 1) { // next to top border wall
     //       eastTilesCount = 0;
       // } else {
        if (vacY != wfMaxRows - 1) { // next to top border wall
            int i = vacY + 1;
            do {
                eastTilesCount++;
                currentTile = wfTileArray.getTile(vacX, i);
                if (currentTile.getType() == 3) {
                    eastTileLoc.setLocation(vacX, i);
                    //           System.out.print("East tile location is: ");
                    //           eastTileLoc.printLocation();
                    //           System.out.println();
                    setStartX(vacX);
                    setStartY(vacY);
                    // this.startDirection = global.setDirection("West"); // closed to the east wall will be going west (counterclockwise)
                    break;
                }
                i++;
            } while (i != wfMaxColumns);
            //   System.out.println("East Tile Count is: " +eastTilesCount);
        }
        // find the west wall vacY = wfMinColumns
        //vacX = tempTileX;
        //vacY = tempTileY;
        // System.out.println(vacX +" "+vacY);
        //if (vacY == 1) { // next to west border wall
         //   westTilesCount = 0;
        //} else {
        if (vacY != 1) { // next to west border wall
            int i = vacX - 1;
            int j = vacY - 1;
            //     System.out.println(i);
            do {
                //    System.out.println(i);
                westTilesCount++;
                currentTile = wfTileArray.getTile(vacX, i);
                //       System.out.println(currentTile.getType());
                if (currentTile.getType() == 3) {
                    j = 1;
                    westTileLoc.setLocation(vacX, j);
                    //         System.out.print("West tile location is: ");
                    //       westTileLoc.printLocation();
                    //      System.out.println();
                    setStartX(vacX);
                    setStartY(vacY);
                    //    this.startDirection = global.setDirection("South"); // closed to the West wall will be going South (counterclockwise)
                    break;
                }
                i--;
            } while (i != wfMinColumns - 1);
            //  } while (i == wfMinColumns+ 1 );
        }

        //System.out.println("North Tiles Count = " + northTilesCount);
        // System.out.println("South Tiles Count = " + southTilesCount);
        // System.out.println("East Tiles Count = " + eastTilesCount);
        //  System.out.println("West Tiles Count = " + westTilesCount);

        // find which wall (North, South, East, West is closest
        if (northTilesCount <= southTilesCount && northTilesCount <= eastTilesCount && northTilesCount <= westTilesCount) {
            System.out.println("North Wall is closer");
            this.nearestWallDirction = 1; // on North wall so will be going West (1)
            vacX = northTileLoc.getLocX();
            vacY = northTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("North");
 //           direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            this.startDirection = global.setDirection("West"); // closed to the North wall will be going West (counterclockwise)
            return currentTile;
        } else if (southTilesCount <= northTilesCount && southTilesCount <= eastTilesCount && southTilesCount <= westTilesCount) {
            System.out.println("South Wall is closer");
            this.nearestWallDirction = 3; // on South wall so will be going East (3)
            vacX = southTileLoc.getLocX();
            vacY = southTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("East");  // since the next tile will move east
        //    direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            this.startDirection = global.setDirection("East"); // closed to the South wall will be going East (counterclockwise)
            return currentTile;
        } else if ((eastTilesCount <= northTilesCount) && (eastTilesCount <= southTilesCount) && (eastTilesCount <= westTilesCount)) {
            System.out.println("East Wall is closer");
            this.nearestWallDirction = 0; // on east wall so will be going North (0)
            vacX = eastTileLoc.getLocX();
            vacY = eastTileLoc.getLocY() - 1; // come in one tile from east wall
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("East");
            //direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            this.startDirection = global.setDirection("North"); // closed to the East wall will be going North (counterclockwise)
            return currentTile;
        } else if (westTilesCount <= northTilesCount && westTilesCount <= southTilesCount && westTilesCount <= eastTilesCount) {
            System.out.println("West Wall is closer");
            this.nearestWallDirction = 2; // on west wall so will be going South (2)
            vacX = westTileLoc.getLocX();
            vacY = westTileLoc.getLocY();
            currentTile = wfTileArray.getTile(vacX, vacY);
            wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
            global.setVacuumDirection("South"); // will be going south for the next tile direction since staying against the west wall
            //direction = global.getVacuumDirection();
            wfVacuum.setX(vacX);
            wfVacuum.setY(vacY);
            wfVacuum.setTileLocation();
            setStartX(vacX);
            setStartY(vacY);
            this.startDirection = global.setDirection("South"); // closed to the West wall will be going South (counterclockwise)
            return currentTile;
        }
        return currentTile;
    }

    /**
     * @param inpTile The tile that will be cleaned. This method is called by the run Simulation thread loop
     * @return nextTile The next tile that needs to be cleaned
     * Author: Marie Held
     * Purpose: To clean the incoming tile and determine what the next tile to be cleaned is
     */

    public Tile nextTileToBeClean(Tile inpTile) {
        Tile nextTile = inpTile;
        int x = wfVacuum.getX();
        int y = wfVacuum.getY();
        wfVacuum.setTileLocation();

        // circuits are the inner loops
        // have reached the initial starting point for the circuit
        //System.out.println("Circuit is " + circuit + ". X is "+ x + ". startX is "+ startX +". Num Wall is " + numWall);
        if (circuit  == 0 && numWall == 5 && x==startX){
            //System.out.println("Circuit is " + circuit + ". X is "+ x + ". startX is "+ startX +". Num Wall is " + numWall);
            circuit = 1;
            numWall = 0;
        }
        if (circuit >= 1 && circuit <=maxNumCircuits) { //ignore the first circuit
            //System.out.println("Circuit is greater than 0 and less than max circuits");
            //System.out.println("Circuit is " + circuit + ". X is "+ x + ". startX is "+ startX +". Num Wall is " + numWall + ". Vacuum direction is " + global.getVacuumDirection() + ". Nearest Wall Direction is " + nearestWallDirction);
            if (global.getVacuumDirection() == nearestWallDirction) { // since x will equal Start X in both the north and south direction ; only change on the nearest wall
                if(x==startX) { // Move one tile to the west (y--)
                    circuit++; // Since back at the staring point increase the circuit count
                 //   System.out.println("Circuit is " + circuit + ". X is "+ x + "startX is "+ startX +". Num Wall is " + numWall + ". Vacuum direction is " + global.getVacuumDirection() + ". Nearest Wall Direction is " + nearestWallDirction);
                    circuitCheck = circuit;
                    numWall = 0;
                    y--; // moving over one column
                   // x++; // just resetting the x value
                   // wfVacuum.setX(x);
                    wfVacuum.setY(y);
                    wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                    //System.out.println("In going North of circuit logic. X is: " + x + ". Y is: " + y + ". Direction is " + global.getVacuumDirectionName(0));
                    this.startX = x;
                    this.startY = y;
                    nextTile = wfTileArray.getTile(x--, y);
                   // return nextTile;  //  Do not return the next tile to run simulation, let rest of code run or will get stuck in a loop
                }
            }

            //}

        } else if (this.circuit >= this.maxNumCircuits) {
            System.out.println("Code to stop cleaning");
            //global.setSimStatus("Finish"); // all circuits have been complete
            global.setSimStatus(2);
        }

        //}

        //***************** End of circuit logic *********************************/

        //************** Going North **********************************************/
        if (global.getVacuumDirection() == 0) {  // Going North
            //System.out.println(" Direction is " + global.getVacuumDirection()+" X is: "+ x + " Y is: " + y + "Circuit is " + circuit);
            x--; // move one tile north
            if (circuit > 1 && circuitCheck == circuit && nearestWallDirction ==0 ) {
                x++; // reset x because of circuit logic only need to do once per circuit
                circuitCheck = 0;
            }
           //System.out.println("Circuit is : "+ circuit);
           if (circuit > 0 && numWall == 0 && x == wfMinRows)  {
             //System.out.println("X is: " + x + ". Circuit is : " + circuit + ".  Wall number is : " + numWall);
             global.setVacuumDirection("West");
             x = circuit; // move x to the current circuit loop
             wfVacuum.setX(x);
             wfTileArray.setTileClean(x, y--, wfCleanValue - 0.1, wfSimulationLayout);
             nextTile = wfTileArray.getTile(x, y--);
             //System.out.println("Moving from North to West. " + "x is " + x + " y is " + y + " Walls " + numWall);
             numWall++;
             return nextTile;
           }

            wfVacuum.setX(x);
            //System.out.println(" Direction is " + global.getVacuumDirection()+" X is: "+ x + " Y is: " + y + " Next tile type is " + nextTile.getType());
            nextTile = wfTileArray.getTile(x, y);
            //System.out.println(" Direction is " + global.getVacuumDirection()+" X is: "+ x + " Y is: " + y + " Next tile type is " + nextTile.getType());
            if (nextTile.getType() == 4 || nextTile.getType() == 5) { // a chest or chair
                nextTile = wfTileArray.getTile(x--, y); // skip the tile since it is occupied
            }
            else if (nextTile.getType() != 3) { // nextTile is a wall
                //    if (nextTile.getType() != 3 || nextTile.getType() != 4 || nextTile.getType() != 5 ) { // nextTile is a wall
                wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                //System.out.println("X is: " + x + " Y is: " + y + " Direction is " + global.getVaccumDirectionName(0));
                nextTile = wfTileArray.getTile(x--, y);
                //  return nextTile;
                //   } else if (tileNorth == x++ && circuit > 0) {
            } else if (nextTile.getType() == 3) {
                global.setVacuumDirection("West");
                x++; // move off north border wall
                wfVacuum.setX(x);
                // System.out.println("X is: " + x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                wfTileArray.setTileClean(x, y--, wfCleanValue - 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x, y--);
                //System.out.println("Moving from North to West. " + "x is " + x + " y is " + y + " Walls "+ numWall);
                numWall++;
                //return nextTile;
            }
            return nextTile;

            ///************** Going West **********************************************/
        }else if(global.getVacuumDirection()==1) {
            // Going West
            //System.out.println("X is: " + x + " Y is: " + y + " Direction is " + global.getVacuumDirection() + "Wall type is " + nextTile.getType());
            y--; // move one tile West
            wfVacuum.setY(y);
            if (circuit > 1 && circuitCheck == circuit && nearestWallDirction == 1) {
                y--; // reset y
                circuitCheck = 0;
            }

            if (circuit > 0 && y == wfMinColumns)  {
            //          if (circuit > 0 )  {
                //System.out.println("X is: " + x + ". Y is " + y + ". Circuit is : " + circuit + ".  Wall number is : " + numWall);
                if (nearestWallDirction == 0 && numWall == 1) {  // first wall if count starts at the east wall
                    global.setVacuumDirection("South");
                    x++;
                    wfVacuum.setX(x);
                    y = circuit; // move x to the current circuit loop
                    wfVacuum.setX(y);
                //    System.out.println("X is: " + x + ". Y is " + y + ". Circuit is : " + circuit + ".  Wall number is : " + numWall + ". Vacuum Direction is " + global.getVacuumDirection());
                    wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                    nextTile = wfTileArray.getTile(x, y);
                    //System.out.println("Moving from North to West. " + "x is " + x + " y is " + y + " Walls " + numWall);
                    numWall++;
                    return nextTile;
                }
            }

            if (y < wfMinColumns) {
                y = 1;
            }
            wfVacuum.setY(y);
            nextTile = wfTileArray.getTile(x, y);

            if (nextTile.getType() == 4 || nextTile.getType() == 5) { // a chest or chair
                nextTile = wfTileArray.getTile(x, y--); // skip the tile since it is occupied
                //    return nextTile;
            } else if (nextTile.getType() != 3) { // || nextTile.getType() != 4 || nextTile.getType() != 5) { // nextTile is a wall
                //    if (nextTile.getType() != 3 || nextTile.getType() != 4 || nextTile.getType() != 5) { // nextTile is a wall
                wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                //    System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection() + "Wall type is " + nextTile.getType() );
                //   wfTileArray.printTileArray();
                nextTile = wfTileArray.getTile(x, y--);
                //  return nextTile;
            } else if (nextTile.getType() == 3 ) {
                global.setVacuumDirection("South");
                x++;
                y++;
                wfVacuum.setX(x);
                wfVacuum.setY(y);
                if (y > wfMaxColumns - 1) {
                    y = wfMaxColumns - 1;
                    wfVacuum.setY(y);
                }  //y++;

                nextTile = wfTileArray.getTile(x, y);
                //   System.out.println("X is: " + x + " Y is: " + y + " Direction is " + global.getVacuumDirection() + "Wall type is " + nextTile.getType() );
                wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                //nextTile = wfTileArray.getTile(x++, y);
                nextTile = wfTileArray.getTile(x, y);
                //System.out.println("Moving from West to South. " + "x is " + x + " y is " + y + " Walls "+ numWall);
                numWall++;
            }
            return nextTile;

            //************  End of Going West ***************************************/

            //************** Going South **********************************************/
        }else if (global.getVacuumDirection() == 2) {

            //System.out.println("X is: " + x + ". Y is " + y + ". Circuit is : " + circuit + ".  Wall number is : " + numWall);
            x++; // move one tile south
            wfVacuum.setX(x);

            if (circuit > 1 && circuitCheck == circuit && nearestWallDirction == 2) {
                x--; // reset x
               circuitCheck = 0;
            }

            if (circuit > 1 ){
                y = circuit;
                wfVacuum.setY(y);
            }

            if (circuit > 1 && x == wfMaxColumns -1 - circuit){ // a corner of circuit
                global.setVacuumDirection("East"); // change direction to east
                nextTile = wfTileArray.getTile(x, y);
                //System.out.println("South to East circuit change. X is: "+ x + " Y is: " + y+ " Direction is " + global.getVacuumDirection());
                wfTileArray.setTileClean(x, y++, wfCleanValue - 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x, y++);
                //System.out.println("Moving from South to East. " + "x is " + x + " y is " + y + " Walls "+ numWall);
                numWall++;
            }

            if (y <= wfMinColumns && circuit == 0) {
                y = wfMinColumns + 1;
                wfVacuum.setY(y);
            }
            nextTile = wfTileArray.getTile(x, y);
            if (nextTile.getType() == 4 || nextTile.getType() == 5) { // a chest or chair
                nextTile = wfTileArray.getTile(x++, y); // skip the tile since it is occupied
            } else if (nextTile.getType() != 3) { // nextTile is a not a wall
                wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                //  System.out.println("X is: "+ x + " Y is: " + y +  " Direction is " + global.getVaccumDirectionName(2));
                nextTile = wfTileArray.getTile(x++, y);
            } else if (nextTile.getType() == 3) {
                global.setVacuumDirection("East"); // change direction to east
                x--; // reset x to it's prior value
                wfVacuum.setX(x);
                nextTile = wfTileArray.getTile(x, y);
                // System.out.println("X is: "+ x + " Y is: " + y+ " Direction is " + global.getVacuumDirection());
                wfTileArray.setTileClean(x, y++, wfCleanValue - 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x, y++);
                //System.out.println("Moving from South to East. " + "x is " + x + " y is " + y + " Walls "+ numWall);
                numWall++;
            } // end of check for wall in the South direction

            // }//  return nextTile;   /************  End of Going South ***************************************/

            //************** Going East **********************************************/
        }else if (global.getVacuumDirection() == 3) {  // Going East
            //System.out.println("X is: " + x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
            y++;
            wfVacuum.setY(y);

            if (circuit > 1 && y == wfMaxColumns - circuit) { // a corner of circuit
                //System.out.println("East to Nort circuit change. X is: " + x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                global.setVacuumDirection("North");
                //System.out.println(" Direction is " + global.getVacuumDirection()+" X is: "+ x + " Y is: " + y + " Next tile type is " + nextTile.getType());
                //x = wfMaxRows - circuit;
               // y = wfMaxColumns - circuit;
                x--;
                wfVacuum.setX(x);
                y--;
                wfVacuum.setY(y);
                wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                nextTile = wfTileArray.getTile(x--, y);
                //System.out.println("Moving from East to North. " + "x is " + x + " y is " + y + " Walls "+ numWall);
                numWall++;
                return nextTile;
            }

            nextTile = wfTileArray.getTile(x, y);
            // System.out.println(" Direction is " + global.getVacuumDirection() + " X is: " + x + " Y is: " + y + " Next tile type is " + nextTile.getType());
            if (nextTile.getType() == 4 || nextTile.getType() == 5) { // a chest or chair
                nextTile = wfTileArray.getTile(x, y++); // skip the tile since it is occupied
            } else if (nextTile.getType() != 3) { // nextTile is not a wall
                wfTileArray.setTileClean(x, y, wfCleanValue - 0.1, wfSimulationLayout);
                //  System.out.println("X is: "+ x + " Y is: " + y + " Direction is " + global.getVacuumDirection());
                nextTile = wfTileArray.getTile(x, y++);
            } else if (nextTile.getType() == 3) {
                global.setVacuumDirection("North");
                //System.out.println(" Direction is " + global.getVacuumDirection()+" X is: "+ x + " Y is: " + y + " Next tile type is " + nextTile.getType());
                if (x <= wfMinRows) { x = wfMinRows;}
                y--; // move off east boarder wall

                wfVacuum.setX(x);
                wfVacuum.setY(y);

                nextTile = wfTileArray.getTile(x, y);
                //System.out.println("Moving from East to North. " + "x is " + x + " y is " + y + " Walls "+ numWall);
                numWall++;
            }

        } //************  End of Going East ***************************************/

        return nextTile;
    }    // end of nextTileToBeClean


} // end of class AlgorithmWallFollow
