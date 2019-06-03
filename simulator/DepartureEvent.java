package edu.macalester.comp124.simulator;

/**
 * This is a departure event, or the arrival of the plane. It contains information regarding the time the plane departed.
 */
public class DepartureEvent {
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


    private static Plane plane;
    private static int timeDepWait; //departure wait time
    private static int timeDep; //time the plane should depart
    private static int serviceTime; //service time for each departure event



    public DepartureEvent(Plane p, int t, int td, int serviceTime){
        this.plane = p;
        this.timeDepWait = t;
        this.timeDep = td;
        this.serviceTime = serviceTime;

    }


    /**
     * this gets the plane that's departing at this event
     * @return
     */
    public Plane getDepEventPlane(){ return plane; }

    /**
     * this gets the time between departure events for this particular event
     * @return
     */
    public int getDepEventWaitTime(){ return timeDepWait; }

    /**
     * this gets the time at which the departure event was created
     * @return
     */
    public int getDepEventTime() { return timeDep; }


    /**
     * this method gets the service time for a departure event on the runway
     *
     * @return
     */
    public int getDepServiceTime() { return serviceTime; }






}
