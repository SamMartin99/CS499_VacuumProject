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
    public int[] algorithm_snake(int h_or_v, int f_or_b, int transition, TileArray TA, Vacuum V, int ft, SimulationLayoutGUI inpsimulationLayout)
    {
        double old_clean;
        // h_or_v, f_or_b, transition
        int return_array[] = {h_or_v, f_or_b, transition};

        // First perform transition if necessary, and determine if the direction needs to be changed.
        if (transition == 1)
        {
            // transition = 0;
            return_array[2] = 0;

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
                    // h_or_v = 1;
                    return_array[0] = 1;
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
                    // h_or_v = 0;
                    return_array[0] = 0;
                }

            }

            if (f_or_b == 0)
            {
                // f_or_b = 1;
                return_array[1] = 1;
            }
            else
            {
                // f_or_b = 0;
                return_array[1] = 0;
            }

            return return_array;
        }

        // Horizontal
        if (h_or_v == 0)
        {
            // Right
            if (f_or_b == 0)
            {
                // System.out.println("Right hit");
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
                    return_array[2] = 1;
                    return return_array;
                }
            }
            // Left
            else
            {
                // System.out.println("Left hit");
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
                    return_array[2] = 1;
                    return return_array;
                }
            }
        }
        // Vertical
        else
        {
            // Upwards
            if (f_or_b == 0)
            {
                // System.out.println("Up hit");
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
                    return_array[2] = 1;
                    return return_array;
                }
            }
            // Downwards
            else
            {
                // System.out.println("Down hit");
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
                    return_array[2] = 1;
                    return return_array;
                }
            }
        }

        return return_array;
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
