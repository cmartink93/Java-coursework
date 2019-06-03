//package edu.macalester.comp124.breakout;
//
//import acm.graphics.GCompound;
//import acm.program.GraphicsProgram;
//
///**
// * this class creates a string object
// */
//public class Screen extends GCompound {
//    //instance variables
//    private GCompound screen;
//    private Brick brick;
//    private GCompound row;
//
//    /**
//     * screen constructor
//     */
//    public Screen(){
//        screen = createWall();
//        add(screen);
//    }

//    /**
//     * this method generates a row of bricks
//     * @return GCompound row of bricks
//     */
//
//    public GCompound createRow(){
//        GCompound row = new GCompound();
//        for(int i=0; i <= 10; i++){
//            brick = new Brick();
//            double disp = 50 * i;
//            row.add(brick,disp, 10);
//        }
//        return row;
//    }
//
//    /**
//     * this method generates a wall object
//     * @return wall GCompound
//     */
//    public GCompound createWall(){
//        GCompound wall = new GCompound();
//
//        for(int i=0; i <= 5; i++){
//            row = createRow();
//            double disp = 30 * i;
//            wall.add(row, 10, disp);
//        }
//        return wall;
//    }
//
//    public double getScreenHeight() {return screen.getHeight(); }
//    public double getScreenWidth() {return screen.getWidth(); }
//
//    @Override
//    public String toString(){
//        return "the screen contains a wall of blocks";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || !(o instanceof Screen)){
//            return false;
//        }
//        Screen screen = (Screen) o;
//        return (screen.getHeight() == screen.getScreenHeight() && screen.getWidth() == screen.getScreenWidth());
//    }
//
//
//}
