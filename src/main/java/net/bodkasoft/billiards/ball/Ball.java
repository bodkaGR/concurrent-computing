package net.bodkasoft.billiards.ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {

    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y= 0;
    private int dx = 2;
    private int dy = 2;

    // move to pocket class
    private int pocketX;
    private int pocketY;
    int pocketSize = 40;
    //

    private boolean isActive = true;

    public Ball(Component c){
        this.canvas = c;

        // move to pocket class
        this.pocketX = this.canvas.getWidth() / 2;
        this.pocketY = this.canvas.getHeight() - 30;
        //

        if(Math.random() < 0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

//    public static void f(){
//        int a = 0;
//    }

    public void draw (Graphics2D g2){
        if (isActive) {
            g2.setColor(Color.darkGray);
            g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
        }
    }

    public void move(){
        if (!isActive) return;

        x += dx;
        y += dy;

        if(x < 0){
            x = 0;
            dx = -dx;
        }

        if(x + XSIZE >= this.canvas.getWidth()){
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }

        if(y < 0){
            y = 0;
            dy = -dy;
        }

        if(y + YSIZE >= this.canvas.getHeight()){
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }

        if (Math.hypot(x + XSIZE / 2 - pocketX, y + YSIZE / 2 - pocketY) < pocketSize / 2) {
            isActive = false;
        }

        this.canvas.repaint();
    }

    public boolean isActive() {
        return isActive;
    }
}
