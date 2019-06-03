package edu.macalester.comp124.simulator;

import java.util.Random;

/**
 * This class generates new random numbers that are distributed about a given mean
 * with a particular standard deviation.
 *
 * @author Libby Shoop
 */
public class RandomGenerator {

    /**
     * @uml.property name="mean"
     */
    private double mean;

    /**
     * @uml.property name="stddev"
     */
    private double stddev;

    /**
     * @uml.property name="rand"
     */
    private Random rand;

    /**
     * Creates a new Random number generator and strores the mean and standard
     * deviation to be used for the Gaussian distribution of the numbers to be generated.
     */
    public RandomGenerator(double mean, double stddev) {
        this.mean = mean;
        this.stddev = stddev;
        rand = new Random();
    }

    /**
     * Creates a new Random number generator, using the seed as the starting
     * point.
     */
    public RandomGenerator(double mean, double stddev, long seed) {
        this.mean = mean;
        this.stddev = stddev;
        rand = new Random(seed);
    }

    /**
     * Returns a new random value within a Gaussian distribution using the mean and standard deviation
     * given when thisRandomGenerator was constructed.
     *
     * @return a new random number, distributed about the mean and standard deviation
     */
    public double getNextValue() {
        double val = (stddev * rand.nextGaussian()) + mean;
        if (val < 0) val = 0.1;
        return val;
    }


    /**
     * Getter of the property <tt>mean</tt>
     *
     * @return Returns the mean.
     * @uml.property name="mean"
     */
    public double getMean() {
        return mean;
    }


    /**
     * Getter of the property <tt>stddev</tt>
     *
     * @return Returns the stddev.
     * @uml.property name="stddev"
     */
    public double getStddev() {
        return stddev;
    }

}