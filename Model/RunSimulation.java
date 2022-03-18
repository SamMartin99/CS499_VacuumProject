package Model;
import java.util.Random;

/* Authors: Bryant Terry
 * Purpose: Runs simulation, calling DrawGraph to update visuals as necessary.
 * Uses one of the algorithms while running to determine changes to the tile array.
 *
 * NOTE FROM BRYANT: I'm putting each algorithm in its own separate Java class. Some algorithms seem
 * to make use of each other, and there was too much code to put under one class. Also, it will (hopefully)
 * make it easier to incorporate the speed of the simulation while it is being run (run_speed).
 */

public class RunSimulation {
    private final int length;
    private final int width;
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
     */
    public RunSimulation(int l, int w, int t, int rs, int a, int ft, int b, int vs, TileArray TA)
    {
        length = l;
        width = w;
        time = t;
        run_speed = rs;
        algorithm = a;
        floor_type = ft;
        V = new Vacuum (b, vs);
        this.TA = TA;
    }

    /* Name: run_algorithm
     * Parameters: N/A
     * Return: N/A
     * Purpose: Runs an algorithm to determine new position of vacuum and what tiles are cleaned in
     * the process. Algorithm is run for every second for every minute of battery life.
     * If vacuum does not move (if it is still calculating a new direction) do not count that as a
     * second spent. Decrement the battery by 1 for every "minute" that passes.
     *
     * NOTE FROM BRYANT: May have to alter this to account for the other algorithms.
     */
    public void run_algorithm () {
        // Start by generating a random number.
        Random rand = new Random();
        int direction = rand.nextInt(8);
        int old_direction;

        AlgorithmRandom RA = new AlgorithmRandom();

        // For each minute of battery life, track the vacuum's actions for every second.
        for (int i = V.getBattery(); i > 0; i--)
        {
            // If the direction has to be changed, do not count that as a second spent.
            for (int j = 60; j > 0; j--)
            {
                old_direction = direction;
                direction = RA.algorithm_random(direction, TA, V, floor_type);

                // Ignore direction calculations as time spent.
                if (old_direction != direction)
                {
                    j++;
                }
            }

            // Decrement battery life.
            V.setBattery(V.getBattery() - 1);
        }
    }

}
