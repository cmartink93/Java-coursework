package edu.macalester.comp124.breakout;

import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*I couldn't get the program to actually remove any bricks, but everything else, including the win/lose logic, should work fine.
It's weird because the program recognizes that the element it hits is a brick and it can correctly identify which corner
of the ball hits the brick, but the remove command does not actually remove the brick. I'm not sure why or what else to try
to see what the issue is.
 */








/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram implements KeyListener{
    //constants



    //instance variable

    private Ball ball;
    private Paddle paddle;
    private double winHeight;
    private double winWidth;
    private double xDisp;
    private double yDisp;
    private Brick brick;
    private int bricksRem;

    public void init(){
        ball = new Ball();
        paddle = new Paddle();
        winHeight = getHeight();
        winWidth = getWidth();
        xDisp = 5;
        yDisp = 5;
        bricksRem = 0; //a counter that represents the number of bricks removed, or, if the ball goes below the paddle, it represents a losing situation
        addKeyListeners();
    }

    public void run() {

        createWall();
        add(ball,1,winWidth / 2);
        add(paddle,winWidth - 100, winHeight - 5);

        while(bricksRem != 75 || bricksRem != 300) {
            if(bricksRem == 75){
                println("You Win!");
                break;
            }
            if(bricksRem == 300){
                println("Game Over");
                break;
            }
            while (bricksRem < 75) {
                if (bricksRem >= 75) {
                    break;
                }

                bricksRem = bricksRem + moveBall();
                //println(bricksRem);
            }
        }

    }

    /**
     * this method generates a wall of bricks
     *
     */

    public void createWall(){

        for(int j=0; j<= 4; j++) {
            double yDisp = 50 * j + 10;
            for (int i = 0; i <= 15; i++) {
                brick = new Brick();
                double xDisp = 50 * i + 10;
                add(brick, xDisp, yDisp);
                }
        }

    }




    /**
     * this method moves the paddle. The left arrow moves the paddle left and the right arrow
     * moves the paddle to the right.
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:    paddle.move(-10,0); break;
            case KeyEvent.VK_RIGHT:   paddle.move(10, 0); break;
        }
    }



    /**
     * This method moves the ball. it returns a zero or a 1. 0 means there was no brick collision
     * and 1 means there was a brick collision
     * @return integer
     */

    public int moveBall(){
            int i = 0;
            double xPos = ball.getX();
            double yPos = ball.getY();
            if(totalCollisions(xPos,yPos) == 0){
                if (((0 <= xPos) && (xPos <= winWidth)) && (0 <= yPos) && (yPos <= winHeight)) {
                    ball.move(xDisp, yDisp);
                    pause(100);
                    i = 0;
                }
                else if((xPos <= 0)) {
                    pause(100);
                    ball.setLocation(5,yPos);
                    xDisp = -xDisp;
                    ball.move(xDisp, yDisp);
                    pause(100);
                    i = 0;
                }
                else if((yPos <= 0)) {
                    pause(100);
                    ball.setLocation(xPos,5);
                    yDisp = -yDisp;
                    ball.move(xDisp, yDisp);
                    pause(100);
                    i = 0;
                }
                else if(xPos >= winWidth) {
                    pause(100);
                    ball.setLocation(winWidth - 5, yPos);
                    xDisp = -xDisp;
                    ball.move(xDisp, yDisp);
                    pause(100);
                    i = 0;
                }
                else if(yPos >= winHeight) {
                    pause(100);
                    ball.setLocation(xPos, winHeight - 5);
                    yDisp = -yDisp;
                    ball.move(xDisp, yDisp);
                    pause(100);
                    //i = 100;
                }
            }
            else if(totalCollisions(xPos,yPos) == 1) {
                pause(100);
                yDisp = -yDisp;
                ball.move(xDisp, yDisp);
                pause(100);
                i = 0;
            }
            else if(totalCollisions(xPos,yPos) == 2) {
                println("brick");
                GObject brick1 = getElementAt(xPos, yPos);
                GObject brick2 = getElementAt(xPos + 2*ball.getBallRadius(), yPos);
                GObject brick3 = getElementAt(xPos, yPos + 2*ball.getBallRadius());
                GObject brick4 = getElementAt(xPos + 2*ball.getBallRadius(), yPos + 2*ball.getBallRadius());
                pause(100);
                removeBrick(brick1, brick2, brick3, brick4);
                yDisp = -yDisp;
                ball.move(xDisp, yDisp);
                pause(100);
                i = 1;
            }
            else {
                ball.setLocation(1, 1);
                i = 0;
            }

            return i;
    }

    /**
     * This program determines whether the ball collided with an element. It returns
     * 0 if the ball didn't collide, 1 if the ball collided with the paddle, and
     * 2 if the ball collided with a brick
     *
     * @param x
     * @param y
     * @return an integer
     */
    public int findCollision(double x, double y){

        if(getElementAt(x, y) == null){
            return 0;
        }
        else {
            if(getElementAt(x, y) instanceof Paddle){
                return 1;
            }
            else {
                return 2;
            }

        }

    }


    /**
     * This program determines whether the ball collided with an element, looking at
     * all 4 corners of the ball.
     * It returns 0 if the ball didn't collide, 1 if the ball collided with the paddle, and
     * 2 if the ball collided with a brick
     * @return integer
     */
    public int totalCollisions(double x, double y){

        double r = ball.getBallRadius();
        int upperLeft = findCollision(x, y);
        int upperRight = findCollision(x + 2*r, y);
        int lowerLeft = findCollision(x, y + 2*r);
        int lowerRight = findCollision(x + 2*r, y + 2*r);
        if(upperLeft == 0 && upperRight == 0 && lowerLeft == 0 && lowerRight == 0){
            return 0;
        }
        else if(upperLeft == 1 || upperRight == 1 || lowerLeft == 1 || lowerRight == 1){
            return 1;
        }
        else if(upperLeft == 2 || upperRight == 2 || lowerLeft == 2 || lowerRight == 2){
            return 2;
        }
        else {
            return -1;
        }

    }

    /**
     * this method removes the brick element at the corner of the ball it touches
     * @param b1
     * @param b2
     * @param b3
     * @param b4
     */
    public void removeBrick(GObject b1, GObject b2, GObject b3, GObject b4){
        if(b1 != null){
            println("1");
            remove(b1);
        }
        else if(b2 != null){
            println("2");
            remove(b2);
        }
        else if(b3 != null){
            println("3");
            remove(b3);
        }
        else {
            println("4");
            remove(b4);
        }
    }



    @Override
    public String toString(){
        return "The red ball moves around the screen and hits bricks";
    }

}
