package Model;

/* Authors: Bryant Terry
 * Purpose: Represents the vacuum object.
 */

public class Vacuum {
    private int battery_life;   // In minutes.
    private int robot_speed;    // In inches.
    private int x_location;
    private int y_location;

    /* Constructs Vacuum.
     * battery_life defaults to 150 minutes.
     * robot_speed defaults to 12 inches a second.
     */
    public Vacuum(int battery, int speed)
    {
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
}
