package Model;

/* Authors: Bryant Terry
 * Purpose: Represents entire house layout via collection of tiles.
 */

public class TileArray {
    /* Sizes passed to TA are placeholders.
     * Sizes should either be replaced with maximum sizes given in project description,
     * or TA should be replaced with a dynamic array.
     */
    private Tile[][] TA = new Tile[1000][1000];
    /*
     * length and width are used for determining how many Tiles need to be constructed.
     */
    private int length;
    private int width;

    /* Constructs TileArray.
     * Uses length and width to create a tile for every respective part of the array.
     */
    public TileArray(int length, int width)
    {
        this.length = length;
        this.width = width;

        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {
                TA[i][j]= new Tile();
            }
        }
    }

    public Tile getTile (int i, int j)
    {
        return TA[i][j];
    }
}
