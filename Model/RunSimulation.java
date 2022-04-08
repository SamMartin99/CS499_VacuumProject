package Model;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

/* Authors:
 * Purpose: Runs simulation, using the appropriate algorithm and updating visuals as
 * necessary.
 *
 * NOTE FROM BRYANT: May be best to have a separate class for each algorithm. This class
 * will probably get too "crowded" otherwise.
 */

// Class Definition
public class RunSimulation {
    // Attributes
    private final int run_speed;
    private int algorithm;
    private int floor_type;
    private Vacuum V;
    private TileArray TA;
    private int delay_time;

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
    public RunSimulation(int rs, int a, int ft, int b, int vs, TileArray TA)
    {
        run_speed = rs;
        algorithm = a;
        floor_type = ft;
        V = new Vacuum (b, vs);
        this.TA = TA;

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
        if (getAlgorithm() == 1 ) {algorithmName = "Random" ;}
        if (getAlgorithm() == 2 ) {algorithmName = "Spiral" ;}
        if (getAlgorithm() == 3 ) {algorithmName = "Snaking" ;}
        if (getAlgorithm() == 4 ) {algorithmName = "Wall Follow" ;}

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
        if (getFloorType() == 1 ) {floorTypemName  = "Shag" ;}
        if (getFloorType() == 2 ) {floorTypemName  = "Hardwood" ;}
        if (getFloorType() == 3 ) {floorTypemName = "LoopPile" ;}
        if (getFloorType() == 4 ) {floorTypemName = "CutPile" ;}
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
    public void run () {
        // Should be some code here that uses delay_time to delay calculations by appropriate amount.
        // Maybe use Thread.sleep()?

        // Code here to actually run tile array through chosen algorithm.
        // Algorithm, ideally, should be run once every iteration.

        // Calculate loss in battery life.
        // Should be used in conjunction with a while loop to run program until the vacuum
        // runs out of battery.

        /*
        int minute = 0;

        minute = minute + delay_time;
        if (minute >= 60000)
        {
            V.setBattery(V.getBattery() - 1);
            System.out.println("A minute has passed.");
            minute = 0;
        }
        */

        JFrame outputScreenFrame = new JFrame("Output");
        outputScreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //mh change from EXIT_ON_CLOSE so that the program continues running

        JPanel outputSimTiles = new JPanel();
        JButton simTileButton = new JButton();

        Border outputSimTilesBorder;
        outputSimTilesBorder = BorderFactory.createLineBorder(Color.BLACK);
        outputSimTiles.setBorder(outputSimTilesBorder);
        outputScreenFrame.add(outputSimTiles, BorderLayout.CENTER);  // add the simulation tile panel to the output frame

        // Use Grid for Simulation Tiles
        GridBagLayout gblSimTiles = new GridBagLayout();
        GridBagConstraints gblSimTilesLayoutConstraints = new GridBagConstraints();

        outputSimTiles.setLayout(gblSimTiles);

        // A square grid for the simulation panel
        gblSimTilesLayoutConstraints.weightx = 1;
        gblSimTilesLayoutConstraints.weighty = 1;

        // for (int i = 0; i < 10; i++)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gblSimTilesLayoutConstraints.gridx = i;
                gblSimTilesLayoutConstraints.gridy = j;

                simTileButton = new JButton();

                outputSimTiles.add(simTileButton, gblSimTilesLayoutConstraints);
            }
        }

        //Display the window.
        outputScreenFrame.setSize(960, 540);
        outputScreenFrame.setVisible(true);
    }


}