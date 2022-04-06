package Model;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

/* Authors:
 * Purpose: Runs simulation, calling DrawGraph to update visuals as necessary.
 * Uses one of the algorithms while running to determine changes to the tile array.
 *
 * NOTE FROM BRYANT: May be best to have a separate class for each algorithm. This class
 * will probably get too "crowded" otherwise.
 */

// Class Definition
public class RunSimulation {
    // Attributes
    private final int time;
    private final int run_speed;
    private int algorithm;
    private int floor_type;
    private Vacuum V;
    private TileArray TA;

    /* Constructs RunSimulation.
     * int l: length
     * int w: width
     * int t: time
     * int rs: run_speed
     * int a: type of algorithm to be used
     * int ft: floor type
     * int b: battery life
     * int rs: vacuum speed
     * TileArray TA: The array of tiles that construct the house layout.
     */

    // Constructor
    public RunSimulation(int t, int rs, int a, int ft, int b, int vs, TileArray TA)
    {
        time = t;
        run_speed = rs;
        algorithm = a;
        floor_type = ft;
        V = new Vacuum (b, vs);
        this.TA = TA;
    }

    /* Name: algorithm_random
     * Parameters: N/A
     * Return: N/A
     * Purpose: Calculates vacuum path using a random algorithm. The vacuum continues to move in
     * a direction until an obstacle is hit, at which point, a new random direction is generated.
     *
     * NOTE FROM BRYANT: Currently I have this in the RunSimulation class, but it might be best to
     * place our algorithms in separate classes. Also, this should go without saying, but this is
     * fairly unoptimized, and missing some features that should be in the final build. If you see any
     * changes that need to be made, don't hesitate to do so.
     */
    public void algorithm_random ()
    {
        // Start by generating a random number.
        Random rand = new Random();
        int direction = rand.nextInt(8);
        double old_clean;

        // For each minute of battery life, track the vacuum's actions for every second.
        for (int i = V.getBattery(); i > 0; i--)
        {
            for (int j = 60; j > 0; j--)
            {
                // If the current direction has an obstacle ahead, generate a new direction,
                // and don't count that as a second.
                switch (direction)
                {
                    // North
                    case 0:
                        if (TA.getTile(V.getX(), (V.getY() + 1)).getPass())
                        {
                            V.setX(V.getX());
                            V.setY(V.getY() + 1);

                            old_clean = TA.getTile(V.getX(), (V.getY() + 1)).getClean();
                            TA.getTile(V.getX(), (V.getY() + 1)).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                    // Northeast
                    case 1:
                        if (TA.getTile((V.getX() + 1), (V.getY() + 1)).getPass())
                        {
                            V.setX(V.getX() + 1);
                            V.setY(V.getY() + 1);

                            old_clean = TA.getTile((V.getX() + 1), (V.getY() + 1)).getClean();
                            TA.getTile((V.getX() + 1), (V.getY() + 1)).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                    // East
                    case 2:
                        if (TA.getTile((V.getX() + 1), V.getY()).getPass())
                        {
                            V.setX(V.getX() + 1);
                            V.setY(V.getY());

                            old_clean = TA.getTile((V.getX() + 1), V.getY()).getClean();
                            TA.getTile((V.getX() + 1), V.getY()).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                    // Southeast
                    case 3:
                        if (TA.getTile((V.getX() + 1), (V.getY() - 1)).getPass())
                        {
                            V.setX(V.getX() + 1);
                            V.setY(V.getY() - 1);

                            old_clean = TA.getTile((V.getX() + 1), (V.getY() - 1)).getClean();
                            TA.getTile((V.getX() + 1), (V.getY() - 1)).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                    // South
                    case 4:
                        if (TA.getTile(V.getX(), (V.getY() - 1)).getPass())
                        {
                            V.setX(V.getX());
                            V.setY(V.getY() - 1);

                            old_clean = TA.getTile(V.getX(), (V.getY() + 1)).getClean();
                            TA.getTile(V.getX(), (V.getY() + 1)).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                    // Southwest
                    case 5:
                        if (TA.getTile((V.getX() - 1), (V.getY() - 1)).getPass())
                        {
                            V.setX(V.getX() - 1);
                            V.setY(V.getY() - 1);

                            old_clean = TA.getTile((V.getX() - 1), (V.getY() - 1)).getClean();
                            TA.getTile((V.getX() - 1), (V.getY() - 1)).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                    // West
                    case 6:
                        if (TA.getTile((V.getX() - 1), V.getY()).getPass())
                        {
                            V.setX(V.getX() - 1);
                            V.setY(V.getY());

                            old_clean = TA.getTile((V.getX() - 1), V.getY()).getClean();
                            TA.getTile((V.getX() - 1), V.getY()).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                    // Northwest
                    case 7:
                        if (TA.getTile((V.getX() - 1), (V.getY() + 1)).getPass())
                        {
                            V.setX(V.getX() - 1);
                            V.setY(V.getY() + 1);

                            old_clean = TA.getTile((V.getX() - 1), (V.getY() + 1)).getClean();
                            TA.getTile((V.getX() - 1), (V.getY() + 1)).setClean(calculate_clean(old_clean));
                        }
                        else
                        {
                            direction = rand.nextInt(8);
                            j++;
                        }
                }
            }

            V.setBattery(V.getBattery() - 1);
        }
    }

    /* Name: calculate_clean
     * Parameters: double clean_value
     * Return: double new_clean
     * Purpose: Calculates how much the tile gets cleaned. Returns the result.
     */
    double calculate_clean (double clean_value)
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

    /* Name: run
     * Parameters: none
     * Return: none
     * Purpose: Output algorithm activity to new window.
     */
    public void run ()
    {
        JFrame outputScreenFrame = new JFrame("Output");
        outputScreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //mh change from EXIT_ON_CLOSE so that the program continues running

        JPanel outputSimTiles = new JPanel();
        JButton simTileButton = new JButton();

        Border outputSimTilesBorder;
        outputSimTilesBorder = BorderFactory.createLineBorder(Color.BLACK);
        outputSimTiles.setBorder(outputSimTilesBorder);
        outputScreenFrame.add(outputSimTiles,BorderLayout.CENTER);  // add the simulation tile panel to the output frame

        // Use Grid for Simulation Tiles
        GridBagLayout gblSimTiles = new GridBagLayout();
        GridBagConstraints gblSimTilesLayoutConstraints = new GridBagConstraints();

        outputSimTiles.setLayout(gblSimTiles);

        // A square grid for the simulation panel
        gblSimTilesLayoutConstraints.weightx = 1;
        gblSimTilesLayoutConstraints.weighty = 1;


   //     for (int i = 0; i < 10; i++)
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                gblSimTilesLayoutConstraints.gridx = i;
                gblSimTilesLayoutConstraints.gridy = j;

                simTileButton = new JButton();

                outputSimTiles.add(simTileButton,gblSimTilesLayoutConstraints);


            }
        }

        //Display the window.
        outputScreenFrame.setSize(960, 540);
        outputScreenFrame.setVisible(true);
    }

}
