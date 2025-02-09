package net.bodkasoft.billiards;

import lombok.Getter;
import net.bodkasoft.billiards.ball.Ball;
import net.bodkasoft.billiards.pocket.Pocket;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public class Canvas extends JPanel {

    private final List<Ball> balls = new ArrayList<>();

    private Pocket pocket;
    private final JLabel counterLabel;
    private int pocketedCount = 0;

    public Canvas(JLabel counterLabel) {
        this.counterLabel = counterLabel;
    }

    public void addBall(Ball b){
        this.balls.add(b);
    }

    public void addPocket(Pocket pocket){
        this.pocket = pocket;
    }

    public boolean isPocketed(Ball ball) {
        if (pocket.isInPocket(ball.getX() + ball.getDiameter() / 2, ball.getY() + ball.getDiameter() / 2)) {
            ball.deactivate();
            repaint();
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        pocket.setCanvasWidthHeight(getWidth(), getHeight());

        g2.setColor(Color.red);
        g2.fill(new Ellipse2D.Double(
                pocket.getX() - pocket.getDiameter() / 2,
                pocket.getY() - pocket.getDiameter() / 2,
                pocket.getDiameter(),
                pocket.getDiameter())
        );

        Iterator<Ball> iterator = balls.iterator();
        while(iterator.hasNext()){
            Ball ball = iterator.next();
            if (!ball.isActive()){
                iterator.remove();
                pocketedCount++;
                counterLabel.setText("Pocketed: " + pocketedCount);
            }else {
                g2.setColor(Color.darkGray);
                g2.fill(new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter()));
            }
        }
    }
}
