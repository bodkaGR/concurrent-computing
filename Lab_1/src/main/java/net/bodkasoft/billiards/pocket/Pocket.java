package net.bodkasoft.billiards.pocket;

import lombok.Getter;

@Getter
public class Pocket {

    private final int diameter = 40;
    private int x, y;

    public Pocket(int width, int height) {
        this.x = width / 2;
        this.y = height - 30;
    }

    public void setCanvasWidthHeight(int canvasWidth, int canvasHeight) {
        this.x = canvasWidth / 2;
        this.y = canvasHeight - 30;
    }

    public boolean isInPocket(int xValues, int yValues) {
        return Math.hypot(xValues - x, yValues - y) < diameter / 2;
    }
}
