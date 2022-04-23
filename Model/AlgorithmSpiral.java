package Model;

/* Authors: Bryant Terry
 * Purpose: Handles code for the spiral algorithm.
 */

import Startup.Location;
import View.SimulationLayoutGUI;

public class AlgorithmSpiral {

    /* Name: algorithm_spiral
     * Parameters: int direction, TileArray TA, Vacuum V, int ft (floor type)
     * Return: int direction
     * Purpose: Calculates vacuum path using a spiral algorithm. The vacuum will perform a spiral
     * if there is enough space to begin one, and will continue the spiral until it hits an obstacle.
     */
    public int[] algorithm_spiral(int spiral_count, int spiral_length, int spiral_progress, int direction, TileArray TA, Vacuum V, int ft, SimulationLayoutGUI inpsimulationLayout)
    {
        double old_clean;

        switch (direction)
        {
            // East
            case 1:
                if (calculate_obstacle(TA.getTile(V.getX(), (V.getY() - 1)), 1))
                {
                    Location vacuumLocation = new Location(V.getX(), (V.getY() - 1));
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile(V.getX(), (V.getY() - 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() - 1), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX());
                    V.setY(V.getY() - 1);

                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_count = 0;
                    spiral_length = 1;
                    spiral_progress = 0;
                }
                // System.out.println("East hit");
                break;
            // South
            case 3:
                if (calculate_obstacle(TA.getTile((V.getX() + 1), V.getY()), 3))
                {
                    Location vacuumLocation = new Location((V.getX() + 1), V.getY());
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile((V.getX() + 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() + 1), V.getY(), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX() + 1);
                    V.setY(V.getY());

                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_count = 0;
                    spiral_length = 1;
                    spiral_progress = 0;
                }
                // System.out.println("South hit");
                break;
            // West
            case 5:
                if (calculate_obstacle(TA.getTile(V.getX(), (V.getY() + 1)), 5))
                {
                    Location vacuumLocation = new Location(V.getX(), (V.getY() + 1));
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile(V.getX(), (V.getY() + 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() + 1), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX());
                    V.setY(V.getY() + 1);

                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_count = 0;
                    spiral_length = 1;
                    spiral_progress = 0;
                }
                // System.out.println("West hit");
                break;
            // North
            case 7:
                if (calculate_obstacle(TA.getTile((V.getX() - 1), V.getY()), 7))
                {
                    Location vacuumLocation = new Location((V.getX() - 1), V.getY());
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile((V.getX() - 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() - 1), V.getY(), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX() - 1);
                    V.setY(V.getY());

                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_count = 0;
                    spiral_length = 1;
                    spiral_progress = 0;
                }
                // System.out.println("North hit");
                break;
        }

        if (spiral_progress >= spiral_length)
        {
            direction = cycle_direction(direction);
            spiral_count++;
            spiral_progress = 0;
        }

        if (spiral_count >= 2)
        {
            spiral_length++;
            spiral_count = 0;
        }

        int return_array[] = {spiral_count, spiral_length, spiral_progress, direction};

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
        boolean obstacle = true;

        // If approaching vertically or horizontally, don't account for table/chair legs.
        // If approaching diagonally, table/chair legs should be considered obstacles.
        if ((direction != 2) && (direction != 4) && (direction != 6) && (direction != 8))
        {
            if ((T.getType() == 3) || (T.getType() == 4))
            {
                obstacle = false;
            }
        }
        else
        {
            if ((T.getType() == 3) || (T.getType() == 4) || (T.getType() == 5) || (T.getType() == 6))
            {
                obstacle = false;
            }
        }

        return obstacle;
    }


    /* Name: cycle_direction
     * Parameters: int direction
     * Return: new direction
     * Purpose: Cycles the direction for the spiral.
     */
    private int cycle_direction (int direction)
    {
        if (direction == 1)
        {
            return 3;
        }
        else if (direction == 3)
        {
            return 5;
        }
        else if (direction == 5)
        {
            return 7;
        }
        else if (direction == 7)
        {
            return 1;
        }

        return 1;
    }

    /*
    switch (direction)
        {
            // East
            case 0:
                if (!calculate_obstacle(TA.getTile(V.getX(), (V.getY() - 1)), 0))
                {
                    Location vacuumLocation = new Location(V.getX(), (V.getY() - 1));
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile(V.getX(), (V.getY() - 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() - 1), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX());
                    V.setY(V.getY() - 1);

                    spiral_confirm = 1;
                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_length = 1;
                    spiral_progress = 0;
                    spiral_count = 1;
                    spiral_confirm = 0;
                }
                // System.out.println("East hit");
                break;
            // South
            case 2:
                if (!calculate_obstacle(TA.getTile((V.getX() + 1), V.getY()), 2))
                {
                    Location vacuumLocation = new Location((V.getX() + 1), V.getY());
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile((V.getX() + 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() + 1), V.getY(), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX() + 1);
                    V.setY(V.getY());

                    spiral_confirm = 1;
                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_length = 1;
                    spiral_progress = 0;
                    spiral_count = 1;
                    spiral_confirm = 0;
                }
                // System.out.println("South");
                break;
            // West
            case 4:
                if (!calculate_obstacle(TA.getTile(V.getX(), (V.getY() + 1)), 4))
                {
                    Location vacuumLocation = new Location(V.getX(), (V.getY() + 1));
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile(V.getX(), (V.getY() + 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() + 1), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX());
                    V.setY(V.getY() + 1);

                    spiral_confirm = 1;
                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_length = 1;
                    spiral_progress = 0;
                    spiral_count = 1;
                    spiral_confirm = 0;
                }
                // System.out.println("West hit");
                break;
            // North
            case 6:
                if (!calculate_obstacle(TA.getTile((V.getX() - 1), V.getY()), 6))
                {
                    Location vacuumLocation = new Location((V.getX() - 1), V.getY());
                    TA.setVacuum(vacuumLocation);

                    old_clean = TA.getTile((V.getX() - 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() - 1), V.getY(), calculate_clean(old_clean, ft), inpsimulationLayout);

                    V.setX(V.getX() - 1);
                    V.setY(V.getY());

                    spiral_confirm = 1;
                    spiral_progress++;
                }
                else
                {
                    direction = cycle_direction(direction);
                    spiral_length = 1;
                    spiral_progress = 0;
                    spiral_count = 0;
                    spiral_confirm = 0;
                }
                // System.out.println("North hit");
                break;
        }

        if (spiral_confirm == 1)
        {
            if (spiral_count <= 2)
            {
                if (spiral_progress > spiral_length)
                {
                    direction = cycle_direction(direction);
                    spiral_progress = 0;
                    spiral_count++;
                }
            }
            else
            {
                spiral_count = 0;
                spiral_length++;
            }
        }
     */
}
