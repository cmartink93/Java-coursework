package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.*;

/**
 * this class creates a brick object
 */
public class Brick extends GCompound {
    //constants
    private static final double BRICK_HEIGHT = 25;
    private static final double BRICK_WIDTH = 40;

    //instance variables
    private GRect brick;


    /**
     * brick constructor
     */
    public Brick(){
        brick = makeBrick();
        add(brick);
    }

    /**
     * this method generates a brick object
     * @return brick GRect
     */
    public GRect makeBrick(){
        GRect obj = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        obj.setColor(Color.BLACK);
        obj.setFilled(true);
        obj.setFillColor(Color.BLACK);
        return obj;
    }

    @Override
    public String toString(){
        return "the brick is a black rectangle";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Brick)){
            return false;
        }
        Brick brick = (Brick) o;
        return (brick.getHeight() == BRICK_HEIGHT && brick.getWidth() == BRICK_WIDTH);
    }


}
