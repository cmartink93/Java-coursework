package edu.macalester.comp124.simulator;

/**
 * This is a helper class that holds all of the values being used in the current
 * simulation. This class is created by the AirportSimulator class and then can
 * be passed to other classes
 * that need to access the original simulation parameters.
 *
 * @author Libby Shoop
 */

public class SystemParameters {
    //----------------- INDEXES INTO THE ARRAY --------------------------------------
    // An array will be passed to the constructor that stores the simulation parameters.
    // The constants below identify
    // the parameter that is stored in the corresponding position in
    // the array

    // amount of time between each clock tick during the simulation
    private static final int SECS_BETWEEN_EVENTS = 0;

    // number of runways at the airport
    private static final int NUMBER_OF_RUNWAYS = 3;
    // mean arrival time between arriving planes
    private static final int ARRIVAL_TIME_MEAN = 3;
    // standard deviation of mean arrival time between planes
    private static final int ARRIVAL_TIME_STDDEV = 1;

    // mean service time for arriving planes
    private static final int ARRIVAL_SERVICE_TIME_MEAN = 4;
    // standard deviation of mean service time for arriving planes
    private static final int ARRIVAL_SERVICE_TIME_STDDEV = 5;

    // mean departure time between departing planes
    private static final int DEPARTURE_TIME_MEAN = 6;
    // standard deviation of mean departure time between planes
    private static final int DEPARTURE_TIME_STDDEV = 7;

    // mean departure time between departing planes
    private static final int DEPARTURE_SERVICE_TIME_MEAN = 10;
    // standard deviation of mean departure time between planes
    private static final int DEPARTURE_SERVICE_TIME_STDDEV = 2;

    // the 'seed' number to be used for the generation of random
    // arrival and departure times
    // we'll create 4 different seeds from this one in other parts of code
    private static final int RANDOM_NUM_SEED = 10;
    // Time to run (mins)
    private static final int SIM_LENGTH = 11;
    //----------------------- END OF ARRAY INDEXES ------------------

    //-------------- INSTANCE VARIABLES THAT ARE THE SYSTEM PARAMETERS
    private double eventtime;   // time between 'tick' events
    private int runways;   // number of runways
    private double arrtime;  // mean arr time
    private double arrstddev;  // std dev of mean arr time
    private double arrservetime;  // mean arr service time
    private double arrservestddev; //std dev of arr service time
    private double deptime;  // mean dep time
    private double depstddev;  // std dev of mean dep time
    private double depservetime;  // mean dep service time
    private double depservestddev; // std dev of dep service time
    private long seed; // Random number seed
    private double length; // Time to run
    //-------------- END OF INSTANCE VARIABLES THAT ARE THE SYSTEM PARAMETERS

    public SystemParameters(double simparams[]) {
        eventtime = simparams[SECS_BETWEEN_EVENTS];
        runways = (int) simparams[NUMBER_OF_RUNWAYS];
        arrtime = simparams[ARRIVAL_TIME_MEAN];
        arrstddev = simparams[ARRIVAL_TIME_STDDEV];
        arrservetime = simparams[ARRIVAL_SERVICE_TIME_MEAN];
        arrservestddev = simparams[ARRIVAL_SERVICE_TIME_STDDEV];
        deptime = simparams[DEPARTURE_TIME_MEAN];
        depstddev = simparams[DEPARTURE_TIME_STDDEV];
        depservetime = simparams[DEPARTURE_SERVICE_TIME_MEAN];
        depservestddev = simparams[DEPARTURE_SERVICE_TIME_STDDEV];
        seed = (long) simparams[RANDOM_NUM_SEED];
        length = simparams[SIM_LENGTH];
    }

    /**
     * returns a multi-line String representation of the system parameters
     * in this class.
     *
     * @return a multi-line String that describes each value of each parameter
     */
    public String toString() {
        String result = "";
        result += "Time between event clock ticks: " + "" + eventtime + " seconds\n";
        result += "Number of Runways: " + "\t\t" + runways + "\n";
        result += "Arrival time mean: " + "\t\t" + arrtime + "\n";
        result += "Arrival time std dev: " + "\t\t" + arrstddev + "\n";
        result += "Arrival service time mean: " + "\t" + arrservetime + "\n";
        result += "Arrival service time std dev: " + "\t" + arrservestddev + "\n";
        result += "Departure time mean: " + "\t\t" + deptime + "\n";
        result += "Departure time std dev: " + "\t" + depstddev + "\n";
        result += "Departure service time mean: " + "\t" + depservetime + "\n";
        result += "Departure service time std dev: " + "" + depservestddev + "\n";
        result += "Value of seed: " + "\t\t\t" + seed + "\n";
        result += "Length of Simulation: " + "\t\t" + length + " minutes\n";
        return result;
    }

    //----------------- Getters (no setters) ------------------------------

    /**
     * @return the eventtime
     */
    public double getEventtime() {
        return eventtime;
    }

    /**
     * @return the runways
     */
    public int getRunways() {
        return runways;
    }

    /**
     * @return the arrtime
     */
    public double getArrtime() {
        return arrtime;
    }

    /**
     * @return the arrstddev
     */
    public double getArrstddev() {
        return arrstddev;
    }

    /**
     * @return the arrservetime
     */
    public double getArrservetime() {
        return arrservetime;
    }

    /**
     * @return the arrservestddev
     */
    public double getArrservestddev() {
        return arrservestddev;
    }

    /**
     * @return the deptime
     */
    public double getDeptime() {
        return deptime;
    }

    /**
     * @return the depstddev
     */
    public double getDepstddev() {
        return depstddev;
    }

    /**
     * @return the depservetime
     */
    public double getDepservetime() {
        return depservetime;
    }

    /**
     * @return the depservestddev
     */
    public double getDepservestddev() {
        return depservestddev;
    }

    /**
     * @return the seed
     */
    public long getSeed() {
        return seed;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

}