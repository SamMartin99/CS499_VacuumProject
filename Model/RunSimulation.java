package Model;

import Startup.staticVariable;
import View.SimulationLayoutGUI;
import javax.swing.SwingWorker;
import java.util.List;

/* Authors:
 * Purpose: Runs simulation, using the appropriate algorithm and updating visuals as
 * necessary.
 *
 * NOTE FROM BRYANT: May be best to have a separate class for each algorithm. This class
 * will probably get too "crowded" otherwise.
 */

// Class Definition
public class RunSimulation<simulationlayout> {
    // Attributes
    private final int run_speed;
    private int algorithm;
    private int floor_type;
    private Vacuum V;
    private TileArray TA;
    private int delay_time;
    private int direction;
    private int ft;
    private staticVariable global;

    /* Constructs RunSimulation.
     * int t: time
     * int rs: run_speed
     * int a: type of algorithm to be used
     * int ft: floor type
     * int b: battery life
     * int rs: vacuum speed
     * TileArray TA: The array of tiles that construct the house layout.
     */

    // Constructor
    public RunSimulation(int rs, int a, int ft, int b, int vs, TileArray TA, staticVariable inpGlobal) {
        run_speed = rs;
        algorithm = a;
        floor_type = ft;
        V = new Vacuum(b, vs);
        this.TA = TA;
        this.global = inpGlobal;

        int direction = 0; // North

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

    /**
     * Purpose: To get the current values of Tiles
     */
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


    /* Name: run
     * Parameters: none
     * Return: none
     * Purpose: Output algorithm activity to new window.
     */
    public void run() {
        SimulationLayoutGUI simulationlayout = new SimulationLayoutGUI(TA,this.global);  // mh create a window to view the simulation
        simulationlayout.displaySimulationLayout(simulationlayout);          // mh display the window

        int direction = (int)Math.floor(Math.random()*8);
        process_output();

        System.out.println(V.getBattery());
        // while (V.getBattery() > 0) {

            // SimulationLayoutGUI simulationlayout = new SimulationLayoutGUI(TA);  // mh create a window to view the simulation
            simulationlayout.displaySimulationLayout(simulationlayout);           // mh display the window
            // simulationlayout.printSimTilesName();

            int outDirection;

            // Run the algorithms
            if (this.algorithm == 1) {
                AlgorithmRandom newAlgRandom;
                newAlgRandom = new AlgorithmRandom();
                System.out.println(direction);
                // Hardcode vacuum location until we can incorporate it into GUI.
                V.setX(3);
                V.setY(3);
                direction = newAlgRandom.algorithm_random(this.direction, TA, V, this.ft, simulationlayout);
            } else if (this.algorithm == 2) {
                System.out.println("Second Path Algorithm Code");
            } else if (this.algorithm == 3) {
                System.out.println("Third Path Algorithm Code");
            } else if (this.algorithm == 4) {
                Tile currentTile = new Tile();
                // TA.printTileArray();
                // V.setX(0);
                // V.setY(global.getMaxColumn() - 1);
                // V.setX(global.getMaxRow() -1 );
                // V.setY(global.getMaxColumn() - 1);
                //V.setY(0);
                V.setX(23);
                V.setY(33);
                AlgorithmWallFollow wallFollow = new AlgorithmWallFollow(simulationlayout, TA, V, global);
                currentTile = wallFollow.findNearestWall();
                // wallFollow.vacuum();
            } else {
                System.out.println("Unknown Algorithm");
            }
       // } // end of while loop
    } // end of run()

    /* Name: process_output()
     * Parameters: none
     * Return: none
     * Purpose: Use SwingWorker to output algorithm work intermediately so the user can see it.
     * on screen.
     */
    private void process_output()
    {
        SimulationLayoutGUI simulationlayout = new SimulationLayoutGUI(TA,this.global);  // mh create a window to view the simulation
        simulationlayout.displaySimulationLayout(simulationlayout);          // mh display the window

        // Calculate loss in battery life.
        // Should be used in conjunction with a while loop to run program until the vacuum
        // runs out of battery.
        final int[] minute = {0};

        // Get a random starting direction.
        final int[] direction = {(int) Math.floor(Math.random() * 8)};

        int algorithm = this.algorithm;
        int ft = this.ft;

        // Temporary until user can choose vacuum location.
        V.setX(3);
        V.setY(3);

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
                        System.out.println("A minute has passed.");
                        minute[0] = minute[0] - 60000;
                    }

                    int outDirection;

                    // Run the algorithms
                    if (algorithm == 1) {
                        AlgorithmRandom newAlgRandom;
                        newAlgRandom = new AlgorithmRandom();
                        System.out.println(direction[0]);
                        // Hardcode vacuum location until we can incorporate it into GUI.
                        direction[0] = newAlgRandom.algorithm_random(direction[0], TA, V, ft, simulationlayout);
                    } else if (algorithm == 2) {
                        System.out.println("Second Path Algorithm Code");
                    } else if (algorithm == 3) {
                        System.out.println("Third Path Algorithm Code");
                    } else if (algorithm == 4) {
                        Tile currentTile = new Tile();
                        // TA.printTileArray();
                        // V.setX(0);
                        // V.setY(global.getMaxColumn() - 1);
                        // V.setX(global.getMaxRow() -1 );
                        // V.setY(global.getMaxColumn() - 1);
                        //V.setY(0);
                        AlgorithmWallFollow wallFollow = new AlgorithmWallFollow(simulationlayout, TA, V, global);
                        currentTile = wallFollow.findNearestWall();
                        // wallFollow.vacuum();
                    } else {
                        System.out.println("Unknown Algorithm");
                    }

                    simulationlayout.displaySimulationLayout(simulationlayout);

                    // publish(minute[0]);
                }

                return 0;
            }

            protected void process(List chunks)
            {
                int val = (int) chunks.get(chunks.size()-1);

                System.out.println(val);
            }
        };

        sw.execute();
    } // end of process_output

}  // end of class RunSimulation