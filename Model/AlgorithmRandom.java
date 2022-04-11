package Model;
import java.util.Random;

/* Authors: Bryant Terry
 * Purpose: Handles code for the random path algorithm.
 *
 * NOTE FROM BRYANT: Still needs to be tested. I currently have no idea if this will work or not,
 * but hopefully you can see what I'm trying to do. If you see any changes that need to be made,
 * feel free to do so.
 */

public class AlgorithmRandom {

    /* Name: algorithm_random
     * Parameters: int direction, TileArray TA, Vacuum V, int ft (floor type)
     * Return: int direction
     * Purpose: Calculates vacuum path using a random algorithm. The vacuum continues to move in
     * a direction until an obstacle is hit, at which point, a new random direction is generated.
     */
    public int algorithm_random(int direction, TileArray TA, Vacuum V, int ft)
    {
        Random rand = new Random();
        double old_clean;

        // If the current direction has an obstacle ahead, generate a new direction,
        // and don't count that as a second.
        switch (direction)
        {
            // North
            case 0:
                if (calculate_obstacle(TA.getTile(V.getX(), (V.getY() - 1)), 0))
                {
                    V.setX(V.getX());
                    V.setY(V.getY() - 1);

                    old_clean = TA.getTile(V.getX(), (V.getY() - 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() - 1), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
                // Northeast
            case 1:
                if (!calculate_obstacle(TA.getTile((V.getX() + 1), (V.getY() - 1)), 1))
                {
                    V.setX(V.getX() + 1);
                    V.setY(V.getY() - 1);

                    old_clean = TA.getTile((V.getX() + 1), (V.getY() - 1)).getClean();
                    TA.setTileClean((V.getX() + 1), (V.getY() - 1), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
                // East
            case 2:
                if (!calculate_obstacle(TA.getTile((V.getX() + 1), V.getY()), 2))
                {
                    V.setX(V.getX() + 1);
                    V.setY(V.getY());

                    old_clean = TA.getTile((V.getX() + 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() + 1), V.getY(), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
                // Southeast
            case 3:
                if (!calculate_obstacle(TA.getTile((V.getX() + 1), (V.getY() + 1)), 3))
                {
                    V.setX(V.getX() + 1);
                    V.setY(V.getY() + 1);

                    old_clean = TA.getTile((V.getX() + 1), (V.getY() + 1)).getClean();
                    TA.setTileClean((V.getX() + 1), (V.getY() + 1), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
                // South
            case 4:
                if (!calculate_obstacle(TA.getTile(V.getX(), (V.getY() + 1)), 4))
                {
                    V.setX(V.getX());
                    V.setY(V.getY() + 1);

                    old_clean = TA.getTile(V.getX(), (V.getY() + 1)).getClean();
                    TA.setTileClean(V.getX(), (V.getY() + 1), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
                // Southwest
            case 5:
                if (!calculate_obstacle(TA.getTile((V.getX() - 1), (V.getY() + 1)), 5))
                {
                    V.setX(V.getX() - 1);
                    V.setY(V.getY() + 1);

                    old_clean = TA.getTile((V.getX() - 1), (V.getY() + 1)).getClean();
                    TA.setTileClean((V.getX() - 1), (V.getY() + 1), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
                // West
            case 6:
                if (!calculate_obstacle(TA.getTile((V.getX() - 1), V.getY()), 6))
                {
                    V.setX(V.getX() - 1);
                    V.setY(V.getY());

                    old_clean = TA.getTile((V.getX() - 1), V.getY()).getClean();
                    TA.setTileClean((V.getX() - 1), V.getY(), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
                // Northwest
            case 7:
                if (!calculate_obstacle(TA.getTile((V.getX() - 1), (V.getY() - 1)), 7))
                {
                    V.setX(V.getX() - 1);
                    V.setY(V.getY() - 1);

                    old_clean = TA.getTile((V.getX() - 1), (V.getY() - 1)).getClean();
                    TA.setTileClean((V.getX() - 1), (V.getY() - 1), calculate_clean(old_clean, ft));
                }
                else
                {
                    direction = rand.nextInt(8);
                }
        }

        return direction;
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