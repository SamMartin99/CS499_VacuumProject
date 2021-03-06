package Model;

import Startup.Location;
import Startup.staticVariable;
import View.SimulationLayoutGUI;
import javax.swing.SwingWorker;

/* Authors: Bryant Terry, Marie Held
 * Purpose: Runs simulation, using the appropriate algorithm and updating the visuals
 * of the output as the vacuum moves across the house layout.
 */

// Class Definition
public class RunSimulation<simulationlayout> {
    // Attributes
    private final int run_speed;
    private final int algorithm;
    private final int floor_type;
    private final Vacuum V;
    private final TileArray TA;
    private final int delay_time;
    private int ft;
    private final staticVariable global;
    private Tile wfCurrentTile;

    /* Constructs RunSimulation.
     * int t: time
     * int rs: run_speed
     * int a: type of algorithm to be used
     * int ft: floor type
     * int b: battery life
     * int rs: vacuum speed
     * TileArray TA: The array of tiles that construct the house layout.
     * Location vacuumLoc: The specific location of the vacuum in memory.
     * staticVariable inpGlobal
     */

    // Constructor
    public RunSimulation(int rs, int a, int ft, int b, int vs, TileArray TA, Location vacuumLoc, staticVariable inpGlobal) {
        run_speed = rs;
        algorithm = a;
        floor_type = ft;
        V = new Vacuum(b, vs); // Variable added to create the vacuum at a set location
        vacuumLoc.setLocation(vacuumLoc.x, vacuumLoc.y);
        V.setX(vacuumLoc.x);
        V.setY(vacuumLoc.y);
        this.TA = TA;
        this.global = inpGlobal;

        this.wfCurrentTile = new Tile(); // holds the tile being cleaned for the wall follow algorithm

        /*
         * Calculate how quickly the output is updated.
         * Started by dividing 24 by vs, where 24 represents 24 inches (2 feet for every tile)
         * and vs represents how many inches the vacuum covers in a second (default is 3).
         * Multiply this value by 1000 to convert to milliseconds.
         * Then, divide by rs (run speed) to determine the delay between individual calculations.
         * (rs should be either 1, 5, or 50).
         */
        delay_time = ((24 / vs) * 1000) / rs;
    }

    /**
     * Purpose: To get the current values of the run speed
     */
    public int getRunSpeed() {
        return this.run_speed;
    }

    /**
     * Purpose: To get the current values of the Algorithm
     */
    public int getAlgorithm() {
        return this.algorithm;
    }

    /**
     * Purpose: To get the current values of the Algorithm Name
     */
    public String getAlgorithmName() {
        String algorithmName = "";
        if (getAlgorithm() == 1) {
            algorithmName = "Random";
        }
        if (getAlgorithm() == 2) {
            algorithmName = "Spiral";
        }
        if (getAlgorithm() == 3) {
            algorithmName = "Snaking";
        }
        if (getAlgorithm() == 4) {
            algorithmName = "Wall Follow";
        }

        return algorithmName;
    }

    /**
     * Purpose: To get the current values of the Floor Type
     */
    public int getFloorType() {
        return this.floor_type;
    }

    /**
     * Purpose: To get the name of the Floor Type
     */
    public String getFloorTypeName() {
        String floorTypemName = "";
        if (getFloorType() == 1) {
            floorTypemName = "Shag";
        }
        if (getFloorType() == 2) {
            floorTypemName = "Hardwood";
        }
        if (getFloorType() == 3) {
            floorTypemName = "LoopPile";
        }
        if (getFloorType() == 4) {
            floorTypemName = "CutPile";
        }
        return floorTypemName;
    }

    // /**
    //  * Purpose: To get the current values of Tiles
    //  */
    //  public int getTileValues() {
    //       return this.floor_type;
    //  }

    /**
     * Purpose: To print all the current values of the RunSimuation object
     */
    public void printSimValues() {
        System.out.println("Run Speed is " + this.getRunSpeed());
        System.out.println("Algorithm is " + this.getAlgorithm() + " - " + getAlgorithmName());
        System.out.println("Floor Type is " + this.getFloorType() + " - " + getFloorTypeName());
    }

    /* Name: run()
     * Parameters: none
     * Return: none
     * Purpose: Runs the output. Uses SwingWorker to output the algorithm results intermediately,
     * so the user can see it on screen.
     */
    public void run()
    {
        global.setSimStatus(0);
        // Create output window.
        SimulationLayoutGUI simulationlayout = new SimulationLayoutGUI(TA,this.global);
        simulationlayout.displaySimulationLayout(simulationlayout);

        // Create algorithm classes.
        AlgorithmRandom newAlgRandom;
        newAlgRandom = new AlgorithmRandom();

        AlgorithmSpiral newAlgSpiral;
        newAlgSpiral = new AlgorithmSpiral();

        AlgorithmSnake newAlgSnake;
        newAlgSnake = new AlgorithmSnake();

        AlgorithmWallFollow wallFollow = new AlgorithmWallFollow(simulationlayout, TA, V, global);
        if (algorithm == 4 ) {
            this.wfCurrentTile = wallFollow.findNearestWall();
            // wallFollow.setStartY(V.getX());
            // wallFollow.setStartY(V.getY());
        }

        // Calculate loss in battery life.
        // Should be used in conjunction with a while loop to run program until the vacuum
        // runs out of battery.
        final int[] minute = {0};

        // Initialize variables.
        // direction: Random direction for RandomAlgorithm.
        // h_or_v: For determining if SnakeAlgorithm is travelling vertically or horizontally.
        // f_or_b: For determining if SnakeAlgorithm is travelling forwards or backwards.
        // transition: For determining if SnakeAlgorithm needs to perform a transition.
        final int[] direction = {(int) Math.floor(Math.random() * 8)};
        final int[] spiral_direction = {1};
        final int[] h_or_v = {0};
        final int[] f_or_b = {0};
        final int[] transition = {0};
        final int[] spiral_count = {0};
        final int[] spiral_length = {1};
        final int[] spiral_progress = {0};

        final int[][] return_snake = new int[1][1];
        final int[][] return_spiral = new int[1][1];

        int algorithm = this.algorithm;
        int ft = this.ft;

        global.setSimStatus(0);

        SwingWorker sw = new SwingWorker()
        {
            public Object doInBackground() throws Exception
            {
                while (V.getBattery() > 0)
                {
                    // Pause the thread.
                    try {
                        Thread.sleep(delay_time);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupt occurred.");
                    }

                    // Calculate time.
                    minute[0] = minute[0] + delay_time;
                    if (minute[0] >= 60000)
                    {
                        V.setBattery(V.getBattery() - 1);
                        minute[0] = minute[0] - 60000;
                    }

                    // Check to see if the cancel signal was seng
                    if(global.getSimStatus() == 1) {
                        boolean hasbeenCancel = false;
                        // System.out.println("Stop Simulation");
                        // a wait to make sure thread has been cancel before closing the simulation gui window 
                        while (!hasbeenCancel){ hasbeenCancel = this.cancel(true);}  // a wait to make sure thread has been cancel before
                        simulationlayout.closeSimulationLayoutGUI();
                        // simulationlayout.storeRunStatistics(algorithm, run_speed, ft,TA , minute );
                    } else if (global.getSimStatus() == 2){ // simulation has finished
                         boolean hasbeenCancel = false;
                         while (!hasbeenCancel){ hasbeenCancel = this.cancel(true);}
                    }

                    // Run the algorithms
                    // Random
                    if (algorithm == 1)
                    {
                        // System.out.println(direction[0]);
                        direction[0] = newAlgRandom.algorithm_random(direction[0], TA, V, ft, simulationlayout);
                    }
                    // Spiral
                    else if (algorithm == 2)
                    {
                        return_spiral[0] = newAlgSpiral.algorithm_spiral(spiral_count[0], spiral_length[0], spiral_progress[0], spiral_direction[0], TA, V, ft, simulationlayout);
                        spiral_count[0] = return_spiral[0][0];
                        spiral_length[0] = return_spiral[0][1];
                        spiral_progress[0] = return_spiral[0][2];
                        spiral_direction[0] = return_spiral[0][3];
                    }
                    // Snake
                    else if (algorithm == 3)
                    {
                        return_snake[0] = newAlgSnake.algorithm_snake(h_or_v[0], f_or_b[0], transition[0], TA, V, ft, simulationlayout);
                        h_or_v[0] = return_snake[0][0];
                        f_or_b[0] = return_snake[0][1];
                        transition[0] = return_snake[0][2];
                    }
                    // Wall Follow
                    else if (algorithm == 4)
                    {
                        wfCurrentTile = wallFollow.nextTileToBeClean(wfCurrentTile);

                    // System.out.println("Follow Path Algorithm Code in Run Simulation");
                    }
                    else
                    {
                        System.out.println("Unknown Algorithm");
                    }

                    simulationlayout.displaySimulationLayout(simulationlayout);

                    // publish(minute[0]);
                }

                return 0;
            }

            /*
            // May not be necessary. Keep for now in case removing it breaks anything.
            protected void process(List chunks)
            {
                int val = (int) chunks.get(chunks.size()-1);

                // System.out.println(val);
            }
             */
        }; // end of SwingWorker

        sw.execute();
    } // end of run ()

}  // end of class RunSimulation