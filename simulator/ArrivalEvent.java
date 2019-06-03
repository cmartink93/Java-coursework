package edu.macalester.comp124.simulator;



/**
 * This is an arrival event, or the arrival of the plane. It contains information regarding the time the plane arrived.
 */
public class ArrivalEvent {
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

    private static RandomGenerator rgen;
    private static Plane plane;
    private static int timeArrWait; //arrival wait time
    private static int timeArr; //time the plane should arrive
    private static int serviceTime; //service time for each arrival event

    public ArrivalEvent(Plane p, int tWait, int timeArr, int serviceTime){
       this.plane = p;
       this.timeArrWait = tWait;
       this.timeArr = timeArr;
       this.serviceTime = serviceTime;



    }

    /**
     * this gets the plane that's arriving at this event
     * @return
     */
    public Plane getArrEventPlane(){ return plane; }

    /**
     * this gets the time between arrival events for this particular event
     * @return
     */
    public int getArrEventWaitTime(){ return timeArrWait; }

    /**
     * this gets the time at which the arrival event was created
     * @return
     */
    public int getArrEventTime() { return timeArr; }

    /**
     * this method gets the service time for an arrival event on the runway
     *
     * @return
     */
    public int getArrServiceTime() { return serviceTime; }






}
