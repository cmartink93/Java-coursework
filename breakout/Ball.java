package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * this class creates a ball object
 */
public class Ball extends GCompound {
    //constants
    private static final double BALL_RADIUS = 10;


    //instance variable
    private GOval ball;

    /**
     * This is the constructor
     */
    public Ball(){
        ball = makeBall();
        add(ball);
    }

    /**
     * this method generates a ball object
     * @return ball GOval
     */

    public GOval makeBall(){
        GOval obj = new GOval(BALL_RADIUS, BALL_RADIUS);
        obj.setColor(Color.RED);
        obj.setFilled(true);
        obj.setFillColor(Color.RED);
        return obj;
    }

    public double getBallRadius(){ return BALL_RADIUS; }

    @Override
    public String toString(){
        return "the ball is a red circle";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Ball)){
            return false;
        }
        Ball ball = (Ball) o;
        return (ball.getHeight() == BALL_RADIUS && ball.getWidth() == BALL_RADIUS);
    }




}
