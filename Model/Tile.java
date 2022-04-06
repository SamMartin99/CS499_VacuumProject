package Model;

/* Authors: Bryant Terry
 * Purpose: Represents a single tile on the house layout.
 */

// Class definition
public class Tile {
    /* A value between 0.0 and 1.0, which represents the
     * cleanliness of the floor.
     */
    private double clean;
    /* A number that corresponds to the tile's type.
     * Each type of tile (every wall tile, door tile, etc.)
     * has a different number:
     * Empty: 1
     * Door: 2
     * Wall: 3
     * Chest: 4
     * Chair: 5
     * Table: 6
     */
    private int type;

    /* Constructs Tile object.
     * Initially, cleanliness is set to 1 (fully dirty),
     * and type is set to 0 (empty floor).
     */
    private boolean pass;

    public Tile ()
    {
        clean = 1.0;
        type = 0;
        pass = true;
    }

    public double getClean ()
    {
        return clean;
    }

    public int getType ()
    {
        return type;
    }

    public boolean getPass()
    {
        return pass;
    }

    public void setClean (double value)
    {
        clean = value;
    }

    public void setType (int value)
    {
        type = value;

        if ((value == 1) || (value == 2) || (value == 5))
        {
            pass = false;
        }
        else
        {
            pass = true;
        }
    }
}
