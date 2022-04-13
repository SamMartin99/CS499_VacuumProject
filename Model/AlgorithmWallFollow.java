package Model;

import Startup.Location;
import View.SimulationLayoutGUI;

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

    // Default Constuctor
    public AlgorithmWallFollow(SimulationLayoutGUI inpsimulationlayout, TileArray inpTA, Vacuum inpVacuum) {
        this.wfSimulationLayout = inpsimulationlayout;
        this.wfTileArray = inpTA;
        this.wfVacuum = inpVacuum;
        this.wfCleanValue = 0.0;
        this.wfMaxRows=inpTA.getLength();
        this.wfMaxColumns = inpTA.getWidth();
        int vacX = this.wfVacuum.getX();
        int vacY = this.wfVacuum.getY();
  //      wfTileArray.setTileClean(vacX,vacY,wfCleanValue,wfSimulationLayout); // shows where the vacuum starts
    }

    /**
     * Purpose: To find the nearest wall
     * @return: the nearest wall tile
     */
    public Tile findNearestWall(){
        Tile retTile = new Tile();
        Tile currentTile = new Tile();
        Location vacLoc = new Location(0,0);
        int vacX;
        int vacY;
        int tempTileX;
        int tempTileY;
        int tileType;
        int northTilesCount, southTilesCount, eastTilesCount, westTilesCount = 0;

        // find the location of the vacuum
        vacX = this.wfVacuum.getX();
        vacY = this.wfVacuum.getY();

     //   vacLoc = wfVacuum.getTileLocation();
        vacX = wfVacuum.getX();
        vacY=  wfVacuum.getY();
        currentTile = wfTileArray.getTile(vacX,vacY);
        tileType = currentTile.getType();
        if (tileType == 3) {
            // not a boarder wall
        //    if (vacX != 0 && vacX != wfMaxColumns && vacY != 0 && vacY != wfMaxRows){
        //
        //    }
            if (vacX == 0){  // wall is on the top
                //vacX++;
                if (vacY == 0){  // top right corner
                    vacY++;
                    vacX++;
                    currentTile = wfTileArray.getTile(vacX,vacY);
                    wfTileArray.setTileClean(vacX,vacY,wfCleanValue,wfSimulationLayout);
                    return currentTile;
                }
                if (vacY == 9) { // top left corner
                    vacY--;
                    vacX++;
                    currentTile = wfTileArray.getTile(vacX,vacY);
                    wfTileArray.setTileClean(vacX,vacY,wfCleanValue,wfSimulationLayout);
                    return currentTile;
                }
                if (vacX == 10) {  // wall is on the bottom
                    //vacX++;
                    if (vacY == 0) {  // bottom right corner
                        vacY++;
                        vacX--;
                        currentTile = wfTileArray.getTile(vacX, vacY);
                        wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
                        return currentTile;
                    }
                    if (vacY == 9) { // top left corner
                        vacY--;
                        vacX--;
                        currentTile = wfTileArray.getTile(vacX, vacY);
                        wfTileArray.setTileClean(vacX, vacY, wfCleanValue, wfSimulationLayout);
                        return currentTile;
                    }
                }

      //              if (vacX == wfMaxColumns){
    //                if (vacX == 9){
    //                    vacX--;
    //                }
                }
   //             currentTile = wfTileArray.getTile(vacX,vacY);
   //             wfTileArray.setTileClean(vacX,vacY,wfCleanValue,wfSimulationLayout);
    //            return currentTile;
            }
      //      wfTileArray.setTileClean(vacX,vacY,wfCleanValue,wfSimulationLayout);
     //       return currentTile;
     //   }
 /*
        // check all the north tiles (increasing y) to find a wall
        for (int i = vacY + 1; i < wfMaxRows; i++) {
            currentTile = wfTileArray.getTile(vacX, i);
            northTilesCount ++;
        }
*/
//        System.out.println(wfTileArray[vacX][vacY].getType());

    //            this.wfTileArray[vacX][vacY].);

        // Find the wall directly north -- increasing y




        return retTile;

    }
}
