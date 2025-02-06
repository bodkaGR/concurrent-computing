package net.bodkasoft.billiards.ball;

import net.bodkasoft.billiards.pocket.Pocket;

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

    private boolean isActive = true;

    public Ball(Component canvas){
        this.canvas = canvas;

        if(Math.random() < 0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public void draw (Graphics2D g2){
        if (isActive) {
            g2.setColor(Color.darkGray);
            g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
        }
    }

    public void move(Pocket pocket){
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

        if (pocket.isInPocket(x + XSIZE / 2, y + YSIZE / 2)) {
            isActive = false;
            canvas.repaint();
            return;
        }

        this.canvas.repaint();
    }

    public boolean isActive() {
        return isActive;
    }
}
