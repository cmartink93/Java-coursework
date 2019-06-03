package edu.macalester.comp124.simulator;

import java.util.ArrayList;

/**
 * This class is the main class for conducting the discrete event simulation
 * of an airport.
 *
 * Created by shoop on 4/13/15.
 */
public class AirportSimulator {
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

    private static double totalRunwayIdle = 0;
    private static int countRemainingIn = 0;
    private static int countRemainingOut = 0;
    private static int totalPlanes;
    private static double totalServiceTime = 0;
    private static double timeOnRunway = 0;
    private static ArrayList<Runway> runways;
    private static int numRunways = (int) simParams[1];
    private static ArrayList<ArrivalEvent> totalArrivals;
    private static ArrayList<DepartureEvent> totalDepartures;
    private static int runTime = (int) simParams[11];
    private static int totalTime = 0;
    private static double avgArrRate;
    private static double avgDepRate;
    private static double avgIdleTime;
    private static double avgTimeOnRunway;
    private static double timeInQueue;
    private static double avgTimeInQueue;
    private static double meanArrService = simParams[4];
    private static double stdevArrService = simParams[5];
    private static RandomGenerator arrServiceRgen;
    private static RandomGenerator depServiceRgen;
    private static double meanDepService = simParams[8];
    private static double stdevDepService = simParams[9];




    public static void main(String[] args) {


        // initialize parameters for the overall Simulation
        AirportSimulator sim = new AirportSimulator();
        SystemParameters sysparams = new SystemParameters(simParams);
        arrServiceRgen = new RandomGenerator(meanArrService, stdevArrService);
        depServiceRgen = new RandomGenerator(meanDepService, stdevDepService);
        runways = new ArrayList<Runway>(numRunways);
        sim.generateRunwayList(numRunways);
        totalArrivals = sim.generateTotalArr(runTime);
        totalDepartures = sim.generateTotalDep(runTime);
        totalPlanes = totalArrivals.size() + totalDepartures.size();

        avgArrRate = sim.calcAvgArrRate(totalArrivals);
        avgDepRate = sim.calcAvgDepRate(totalDepartures);


        //loop thru 1 unit of time
        for(int i = 0; i <= runTime; i++){
            for(ArrivalEvent ae: totalArrivals){
                if(ae.getArrEventTime() == totalTime){
                    Runway r = sim.controlTower(runways);
                    r.getArrivalList().add(ae);

                }
            }

            for(DepartureEvent de: totalDepartures){
                if(de.getDepEventTime() == totalTime){
                    Runway r = sim.controlTower(runways);
                    r.getDepartureList().add(de);

                }
            }

            for(Runway r: runways){
                if(!r.arrIsOnRunway()){
                    r.makeCurrentArr();

                }
                if(!r.depIsOnRunway()){
                    r.makeCurrentDep();

                }

                sim.updateRunway(r);
            }

            totalTime++;
        }

        avgIdleTime = totalRunwayIdle / numRunways;
        avgTimeOnRunway = timeOnRunway / totalPlanes;
        avgTimeInQueue = timeInQueue / totalPlanes;

        sim.printStatements();
    }




    /**
     * this method generates a list of runways containing a specified number of runways (num parameter)
     * @param num
     */
    public void generateRunwayList(int num){
        for(int i=0; i < num; i++){
            Runway r = new Runway();
            runways.add(r);
        }
    }

    /**
     * this method generates all arrival events that will occur throughout a simulation of time totalTime
     * @param totalTime
     * @return
     */
    public ArrayList<ArrivalEvent> generateTotalArr(int totalTime){
        ArrayList<ArrivalEvent> arr = new ArrayList<ArrivalEvent>();
        Runway r = new Runway();
        int time = 0; //this is the time in units of minutes
        int timeLast = 0; //this is the time the last plane arrived
        int aWait = (int) r.getArrSepTime(); //this is the time between plane arrivals
        while(time < runTime){
            if(time == timeLast + aWait) {
                Plane p = new Plane();
                int arrService = (int) arrServiceRgen.getNextValue();
                ArrivalEvent ar = new ArrivalEvent(p, aWait, time, arrService);
                arr.add(ar);
                timeLast = time; //timeLast is the time at which the last plane was added.
                aWait = r.getArrSepTime();
            }
            time++;
        }
        return arr;
    }

    /**
     * this method generates all departure events that will occur throughout a simulation of time totalTime
     * @param totalTime
     * @return
     */
    public ArrayList<DepartureEvent> generateTotalDep(int totalTime){
        ArrayList<DepartureEvent> dep = new ArrayList<DepartureEvent>();
        Runway r = new Runway();
        int time = 0; //this is the time in units of minutes
        int timeLast = 0; //this is the time the last plane departed
        int dWait = (int) r.getDepSepTime(); //this is the wait time for the next plane
        while(time < runTime){
            if(time == timeLast + dWait) {
                Plane p = new Plane();
                int depService = (int) depServiceRgen.getNextValue();
                DepartureEvent de = new DepartureEvent(p, dWait, time, depService);
                dep.add(de);
                timeLast = time;
                dWait = r.getDepSepTime();
            }
            time++;
        }
        return dep;
    }


    /**
     * this method decides which runway will take a new event
     * @param r
     * @return
     */
    public Runway controlTower(ArrayList<Runway> r){

        int index = 0;
        int lowestRunway = 0;
        double lowestWait = r.get(0).calcRunwayWait();
        for(Runway rw:r){
            if(rw.isArrEvent()){
                double wait = rw.calcRunwayWait();
                if(wait < lowestWait){
                    lowestWait = wait;
                    lowestRunway = index;
                }
            }
            if(rw.isDepEvent()){
                double wait = rw.calcRunwayWait();
                if(wait < lowestWait){
                    lowestWait = wait;
                    lowestRunway = index;
                }
            }
            else {

            }
            index++;
        }
        return r.get(lowestRunway);
    }

    /**
     * this method calculates the average arrival rate for the arrival events generated at the start of the simulation
     * @param l
     * @return
     */
    public double calcAvgArrRate(ArrayList<ArrivalEvent> l){
        int s = l.size();
        double sum = 0;
        for(ArrivalEvent a: l){
            double num = a.getArrEventWaitTime();
            sum = sum + num;
        }
        double avg = sum / s;
        return avg;
    }

    /**
     * this method calculates the average departure rate for the arrival events generated at the start of the simulation
     * @param l
     * @return
     */
    public double calcAvgDepRate(ArrayList<DepartureEvent> l){
        int s = l.size();
        double sum = 0;
        for(DepartureEvent d: l){
            double num = d.getDepEventWaitTime();
            sum = sum + num;
        }
        double avg = sum / s;
        return avg;
    }

    public void updateRunway(Runway r){
            for(ArrivalEvent ae: r.getArrivalList()){
                timeInQueue++;
            }
            for(DepartureEvent de: r.getDepartureList()){
                timeInQueue++;
            }
            if(r.arrIsOnRunway()){
                if(countRemainingOut == r.getArrOnRunway().getArrServiceTime()){
                    timeOnRunway = timeOnRunway + r.getArrOnRunway().getArrServiceTime();
                    r.makeArrNull();
                    countRemainingOut = 0;

                }
                else{
                    countRemainingOut++;

                }
            }
            if(r.depIsOnRunway()){
                if(countRemainingOut == r.getDepOnRunway().getDepServiceTime()){
                    timeOnRunway = timeOnRunway + r.getDepOnRunway().getDepServiceTime();
                    r.makeDepNull();
                    countRemainingOut = 0;


                }
                else{
                    countRemainingOut++;

                }
            }
            else{
                totalRunwayIdle++;
            }
    }



    /**
     * this method prints out all arrival events generated at the start of the simulation
     */
    public void printArrivalList(){
        int i = 0;
        for(ArrivalEvent a: totalArrivals){
            System.out.println("Arrival #"+i+": Arrival Time: "+a.getArrEventTime()+".");
            i++;
        }
    }

    /**
     * this method prints out all departure events generated at the start of the simulation
     */
    public void printDepartureList(){
        int i = 0;
        for(DepartureEvent d: totalDepartures){
            System.out.println("Departure #"+i+": Departure Time: "+d.getDepEventTime()+".");
            i++;
        }
    }

    /**
     * this method prints out all the statistics for the simulation
     */
    public void printStatements(){
        System.out.println("Simulation Complete. Statistics for this run:");
        System.out.println("Average arrival rate: "+avgArrRate+".");
        System.out.println("Average departure rate: "+avgDepRate+".");
        System.out.println("Average time on runway: "+avgTimeOnRunway+".");
        System.out.println("Average idle time of all runways: "+avgIdleTime+".");
        System.out.println("Average time in an arrival or departure queue: "+avgTimeInQueue+".");
        System.out.println("List of all arrivals: ");
        printArrivalList();
        System.out.println("List of all departures: ");
        printDepartureList();

    }





}
