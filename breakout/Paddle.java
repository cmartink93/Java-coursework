package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * this class creates a paddle object
 */
public class Paddle extends GCompound {
    //constants
    private static final double PADDLE_HEIGHT = 10;
    private static final double PADDLE_WIDTH = 50;

    //instance variables
    private GRect paddle;

    /**
     * paddle constructor
     */
    public Paddle(){
        paddle = makePaddle();
        add(paddle);

    }

    /**
     * this method generates a paddle object
     * @return paddle GRect
     */
    public GRect makePaddle(){
        GRect obj = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
        obj.setColor(Color.BLACK);
        obj.setFilled(true);
        obj.setFillColor(Color.BLACK);
        return obj;

    }





    @Override
    public String toString(){
        return "the paddle is a black rectangle";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Paddle)){
            return false;
        }
        Paddle paddle = (Paddle) o;
        return (paddle.getHeight() == PADDLE_HEIGHT && paddle.getWidth() == PADDLE_WIDTH);
    }


}
