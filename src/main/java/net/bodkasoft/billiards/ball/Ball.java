package net.bodkasoft.billiards.ball;

import lombok.Getter;

import java.awt.*;
import java.util.Random;

@Getter
public class Ball {

    private boolean isActive = true;

    private final int diameter = 20;
    private int x, y;
    private int dx = 2;
    private int dy = 2;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void deactivate() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
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
