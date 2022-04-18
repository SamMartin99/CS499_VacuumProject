package Model;

/* Authors: Bryant Terry
 * Purpose: Handles code for the snake algorithm.
 */

import Startup.Location;
import View.SimulationLayoutGUI;

import java.util.Random;

public class AlgorithmSnake {

    /* Name: algorithm_snake
     * Parameters: int h_or_v, int f_or_b, int transition, TileArray TA, Vacuum V, int ft (floor type)
     * Return: int direction
     * Purpose: Calculates vacuum path using a snaking algorithm. The vacuum will head in a straight direction,
     * until it hits a wall, at which point it will move to the side and begin to move in the opposite direction.
     */
    public int algorithm_snake(int h_or_v, int f_or_b, int transition, TileArray TA, Vacuum V, int ft, SimulationLayoutGUI inpsimulationLayout)
    {
        Random rand = new Random();
        double old_clean;

        // First perform transition if necessary, and determine if the direction needs to be changed.
        if (transition == 1)
        {
            System.out.println("Transition hit.");
            if (h_or_v == 0)
            {
                if (!calculate_obstacle(TA.getTile((V.getX() + 1), V.getY()), 2))
                {
                    Location vacuumLocation = new Location((V.getX() + 1), V.getY());
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile((V.getX() + 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() + 1), V.getY(), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX() + 1);
                    V.setY(V.getY());
                }
                else
                {
                    h_or_v = 1;
                }
            }
            else
            {
                if (!calculate_obstacle(TA.getTile(V.getX(), (V.getY() - 1)), 0))
                {
                    Location vacuumLocation = new Location(V.getX(), (V.getY() - 1));
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile(V.getX(), (V.getY() - 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() - 1), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX());
                    V.setY(V.getY() - 1);
                }
                else
                {
                    h_or_v = 0;
                }
            }

            return 0;
        }

        // Horizontal
        if (h_or_v == 0)
        {
            // Right
            if (f_or_b == 0)
            {
                if (!calculate_obstacle(TA.getTile(V.getX(), (V.getY() + 1)), 4))
                {
                    Location vacuumLocation = new Location(V.getX(), (V.getY() + 1));
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile(V.getX(), (V.getY() + 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() + 1), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX());
                    V.setY(V.getY() + 1);
                }
                else
                {
                    return 1;
                }
            }
            // Left
            else
            {
                if (!calculate_obstacle(TA.getTile(V.getX(), (V.getY() - 1)), 0))
                {
                    Location vacuumLocation = new Location(V.getX(), (V.getY() - 1));
                    TA.setVacuum(vacuumLocation);
                    old_clean = TA.getTile(V.getX(), (V.getY() - 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() - 1), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX());
                    V.setY(V.getY() - 1);
                }
                else
                {
                    return 1;
                }
            }
        }
        // Vertical
        else
        {
            // Upwards
            if (f_or_b == 0)
            {
                if (!calculate_obstacle(TA.getTile((V.getX() - 1), V.getY()), 6))
                {
                    Location vacuumLocation = new Location((V.getX() - 1), V.getY());
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile((V.getX() - 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() - 1), V.getY(), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX() - 1);
                    V.setY(V.getY());
                }
                else
                {
                    return 1;
                }
            }
            // Downwards
            else
            {
                if (!calculate_obstacle(TA.getTile((V.getX() + 1), V.getY()), 2))
                {
                    Location vacuumLocation = new Location((V.getX() + 1), V.getY());
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile((V.getX() + 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() + 1), V.getY(), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX() + 1);
                    V.setY(V.getY());
                }
                else
                {
                    return 1;
                }
            }
        }

        return 0;
    }

    /* Name: calculate_clean
     * Parameters: double clean_value, int floor_type
     * Return: N/A
     * Purpose: Determines how much a tile is cleaned based off of the floor type
     * and the current cleanliness value of the tile.
     */
    private double calculate_clean (double clean_value, int floor_type)
    {
        double new_clean;

        // Hard
        if (floor_type == 1)
        {
            new_clean = clean_value - (clean_value * 0.90);
        }
        // Loop Pile
        else if (floor_type == 2)
        {
            new_clean = clean_value - (clean_value * 0.75);
        }
        // Cut Pile
        else if (floor_type == 3)
        {
            new_clean = clean_value - (clean_value * 0.70);
        }
        // Frieze-Cut Pile
        else
        {
            new_clean = clean_value - (clean_value * 0.65);
        }

        return new_clean;
    }

    /* Name: calculate_obstacle
     * Parameters: int x, int y, Tile T, int direction
     * Return: bool
     * Purpose: Determines if there is an obstacle ahead.
     */
    private boolean calculate_obstacle (Tile T, int direction)
    {
        boolean obstacle = false;

        // If approaching vertically or horizontally, don't account for table/chair legs.
        // If approaching diagonally, table/chair legs should be considered obstacles.
        if ((direction != 1) && (direction != 3) && (direction != 5) && (direction != 7))
        {
            if ((T.getType() == 3) || (T.getType() == 4))
            {
                obstacle = true;
            }
        }
        else
        {
            if ((T.getType() == 3) || (T.getType() == 4) || (T.getType() == 5) || (T.getType() == 6))
            {
                obstacle = true;
            }
        }

        return obstacle;
    }
}
