package Model;

/* Authors: Bryant Terry
 * Purpose: Represents the vacuum object.
 */

import Startup.Location;

public class Vacuum {
    private int battery_life;   // In minutes.
    private int robot_speed;    // In inches.
    private int x_location;
    private int y_location;
    private Location tile_location;  // use to determine with tile in the simulation gui needs to be updated

    /* Constructs Vacuum.
     * battery_life defaults to 150 minutes.
     * robot_speed defaults to 12 inches a second.
     */
    public Vacuum(int battery, int speed)
    {
        tile_location = new Location(0,0);  // could send in the initial location of the vacuum
        battery_life = battery;
        robot_speed = speed;
    }

    public int getBattery ()
    {
        return battery_life;
    }

    public int getSpeed ()
    {
        return robot_speed;
    }

    public int getX ()
    {
        return x_location;
    }

    public int getY ()
    {
        return y_location;
    }

    // need location code for GUI tiles
    public Location getTileLocation () {return tile_location; }

    public void setBattery (int battery)
    {
        battery_life = battery;
    }

    public void setSpeed (int speed)
    {
        robot_speed = speed;
    }

    public void setX (int x)
    {
        x_location = x;
    }

    public void setY (int y)
    {
        y_location = y;
    }

    // need location code for GUI tiles
    public void setTileLocation () {
        int vacuumLocationX = this.getX();
        int vacuumLocationY = this.getY();
        tile_location.setLocation(vacuumLocationX,vacuumLocationY);
    }

    /**
     * Author Marie Held
     * Purpose To print the current location of the vacuum
     */
    public void printVacumLocation(){
        tile_location.printLocation();
    }
}
