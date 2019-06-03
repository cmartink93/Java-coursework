package edu.macalester.comp124.simulator;

/**
 * This is a plane object.
 */
public class Plane {
    //instance variables
    private static double simParams[] = {
            60,    // Sec between events
            3,        // Number of runways

            3,        // Mean arrival Time between planes
            0.5,        // STDEV arrival time between planes

            4,        // Mean service time (arrival)
            1,    // STDEV service time (arrival)

            3,        // mean departure time between planes
            0.5,    // STDEV departure time between planes

            10,        // mean service time (departure)
            1,    // STDEV service time (departure)

            10,    // random seed
            3600    // time to run (minutes)
    };



    public Plane(){
        SystemParameters sysparams = new SystemParameters(simParams);

    }


}
