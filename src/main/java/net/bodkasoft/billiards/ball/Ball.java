package net.bodkasoft.billiards.ball;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

@Getter
public class Ball {

    private boolean isActive = true;

    private final int diameter = 20;
    private int x, y;
    private int dx = 2;
    private int dy = 2;
    private final Color color;

    public Ball(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void deactivate() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, diameter, diameter));
    }

    public void move(int width, int height){
        if (!isActive) return;

        x += dx;
        y += dy;

        if(x < 0){
            x = 0;
            dx = -dx;
        }

        if(x + diameter >= width){
            x = width - diameter;
            dx = -dx;
        }

        if(y < 0){
            y = 0;
            dy = -dy;
        }

        if(y + diameter >= height){
            y = height - diameter;
            dy = -dy;
        }
    }
}
