package edu.macalester.comp124.simulator;



import java.util.LinkedList;
import java.util.Queue;

/**
 * Runway object. Keeps track of waiting times.
 */
public class Runway {
    //instance variables
    private static double simParams[] = {
            60,    // Sec between events
            3,        // Number of runways

            10,        // Mean arrival Time between planes
            0.5,        // STDEV arrival time between planes

            4,        // Mean service time (arrival)
            1,    // STDEV service time (arrival)

            10,        // mean departure time between planes
            0.5,    // STDEV departure time between planes

            4,        // mean service time (departure)
            1,    // STDEV service time (departure)

            10,    // random seed
            3600    // time to run (minutes)
    };
    private static Queue<ArrivalEvent> arrivalList;
    private static Queue<DepartureEvent> departureList;

    private static RandomGenerator arrRgen;
    private static RandomGenerator depRgen;
    private static double meanArrival = simParams[2];
    private static double stdevArrival = simParams[3];
    private static double meanDeparture = simParams[6];
    private static double stdevDeparture = simParams[7];
    private static ArrivalEvent arrOnRunway;
    private static DepartureEvent depOnRunway;






    public Runway() {
        arrivalList = new LinkedList<ArrivalEvent>();
        departureList = new LinkedList<DepartureEvent>();
        arrRgen = new RandomGenerator(meanArrival, stdevArrival);
        depRgen = new RandomGenerator(meanDeparture, stdevDeparture);
        arrOnRunway = null;
        depOnRunway = null;


    }

    /**
     * this checks the wait time for an arrival event on the runway
     *
     * @return
     */
    public int checkArrWaitTime() {
        return calcArrQueueWait() + calcArrServiceTime();
    }

    /**
     * this checks the wait time for a departure event on the runway
     *
     * @return
     */
    public int checkDepWaitTime() {
        return calcArrQueueWait() + calcDepQueueWait() + calcDepServiceTime();
    }

    /**
     * this calculates the wait time for an arrival event in a runway's arrival queue
     *
     * @return
     */
    public int calcArrQueueWait() {
        int queueTime = 0; //amount of time for each plane in queue
        for (ArrivalEvent ae : arrivalList) {
            queueTime = queueTime + ae.getArrEventWaitTime();
        }
        return queueTime;
    }

    /**
     * this calculates the wait time for a departure event in a runway's departure queue
     *
     * @return
     */
    public int calcDepQueueWait() {
        int queueTime = 0; //amount of time for each plane in queue
        for (DepartureEvent de : departureList) {
            queueTime = queueTime + de.getDepEventWaitTime();
        }
        return queueTime;
    }

    /**
     * this calculates the runway wait time for an event on the runway if there is one
     * the runway wait time is calculated to be the service time of the event
     *
     * @return
     */
    public int calcRunwayWait() {
        if (isArrEvent()) {
            return checkArrWaitTime();
        } else if (isDepEvent()) {
            return checkDepWaitTime();
        } else {
            return 0;
        }
    }

    /**
     * This method gets the amount of time between plane arrivals
     *
     * @return
     */
    public int getArrSepTime() {
        int time = (int) arrRgen.getNextValue();
        return time;
    }

    /**
     * this method gets the amount of time between plane departures
     *
     * @return
     */
    public int getDepSepTime() {
        int time = (int) depRgen.getNextValue();
        return time;
    }

    /**
     * this method gets the service time for all arrival events in the arrival queue
     *
     * @return
     */
    public int calcArrServiceTime() {
        int serviceTime = 0; //amount of service time for each plane in queue
        for (ArrivalEvent ae : arrivalList) {
            serviceTime = serviceTime + ae.getArrServiceTime();
        }
        return serviceTime;
    }

    /**
     * this method calculates the service time for all departure events in the departure queue
     *
     * @return
     */
    public int calcDepServiceTime() {
        int serviceTime = 0; //amount of service time for each plane in queue
        for (DepartureEvent de : departureList) {
            serviceTime = serviceTime + de.getDepServiceTime();
        }
        return serviceTime;
    }

    /**
     * this method determines if there is an arrival event in the arrival runway queue
     *
     * @return
     */
    public boolean isArrEvent() {
        if (arrivalList.peek() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * this method determines if there is a departure event in the departure runway queue
     *
     * @return
     */
    public boolean isDepEvent() {
        if (departureList.peek() == null) {
            return false;
        } else {
            return true;
        }
    }





    /**
     * this method gets the next plane in the runway's arrival queue
     *
     * @return
     */
    public ArrivalEvent getNextArr() {
        ArrivalEvent ae = arrivalList.poll();
        return ae;
    }

    /**
     * this method gets the next plane in the runway's departure queue
     *
     * @return
     */
    public DepartureEvent getNextDep() {
        DepartureEvent de = departureList.poll();
        return de;
    }

    /**
     * this method determines if there is an arrival event on the runway
     * @return
     */
    public boolean arrIsOnRunway(){
        if(arrOnRunway != null){ return true; }
        else { return false; }
    }

    /**
     * this method determines if there is a departure event on the runway
     * @return
     */
    public boolean depIsOnRunway(){
        if(depOnRunway != null){ return true; }
        else { return false; }
    }

    /**
     * this method removes an arrival event from the runway
     */
    public void makeArrNull(){
        arrOnRunway = null;
    }


    /**
     * this method removes the departure event from the runway
     */
    public void makeDepNull(){ depOnRunway = null; }


    /**
     * this method puts an arrival plane on the runway
     *
     * @return
     */
    public void makeCurrentArr(){

        if(isArrEvent()){
            arrOnRunway = getNextArr();

        }

    }

    /**
     * this method puts a departure plane on the runway
     *
     * @return
     */
    public void makeCurrentDep(){

        if(isDepEvent()){
            depOnRunway = getNextDep();

        }


    }





    /**
     * this method accesses the arrival list for the runway (i.e. the planes waiting to arrive)
     *
     * @return
     */

    public Queue<ArrivalEvent> getArrivalList() {
        return arrivalList;
    }


    /**
     * this method accesses the departure list for the runway (i.e. the planes waiting to depart)
     *
     * @return
     */
    public Queue<DepartureEvent> getDepartureList() {
        return departureList;
    }

    /**
     * this method gets the arrival event on the runway
     * @return
     */
    public ArrivalEvent getArrOnRunway(){ return arrOnRunway; }

    /**
     * this method gets the departure event on the runway
     * @return
     */
    public DepartureEvent getDepOnRunway(){ return depOnRunway; }


}
