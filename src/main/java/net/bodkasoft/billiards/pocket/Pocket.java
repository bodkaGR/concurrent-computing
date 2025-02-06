package net.bodkasoft.billiards.pocket;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pocket {

    private final int SIZE = 40;
    private int x;
    private int y;

    public Pocket(int width, int height) {
        this.x = width / 2;
        this.y = height - 30;
    }

    public void setCanvasWidthHeight(int canvasWidth, int canvasHeight) {
        this.x = canvasWidth / 2;
        this.y = canvasHeight - 30;
    }

    public boolean isInPocket(int xValues, int yValues) {
        return Math.hypot(xValues - x, yValues - y) < SIZE / 2;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fill(new Ellipse2D.Double(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE));
    }
}
