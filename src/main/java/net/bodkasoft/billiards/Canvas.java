package net.bodkasoft.billiards;

import net.bodkasoft.billiards.ball.Ball;
import net.bodkasoft.billiards.pocket.Pocket;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Canvas extends JPanel {

    private final ArrayList<Ball> balls = new ArrayList<>();

    private final Pocket pocket;
    private final JLabel counterLabel;
    private int pocketedCount = 0;

    public Canvas(JLabel counterLabel) {
        this.counterLabel = counterLabel;
        this.pocket = new Pocket(getWidth(), getHeight());
    }

    public void addBall(Ball b){
        this.balls.add(b);
    }

    public Pocket getPocket() {
        return pocket;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        pocket.setCanvasWidthHeight(getWidth(), getHeight());
        pocket.draw(g2);

        Iterator<Ball> iterator = balls.iterator();
        while(iterator.hasNext()){
            Ball b = iterator.next();
            if (!b.isActive()){
                iterator.remove();
                pocketedCount++;
                counterLabel.setText("Pocketed: " + pocketedCount);
            }else {
                b.draw(g2);
            }
        }
    }
}
