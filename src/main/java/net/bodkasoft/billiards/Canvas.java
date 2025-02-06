package net.bodkasoft.billiards;

import net.bodkasoft.billiards.ball.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Canvas extends JPanel {

    private ArrayList<Ball> balls = new ArrayList<>();
    private JLabel counterLabel;
    private int pocketedCount = 0;

    public Canvas(JLabel counterLabel) {
        this.counterLabel = counterLabel;
    }

    public void add(Ball b){
        this.balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // move to pocket class
        int pocketX = getWidth() / 2;
        int pocketY = getHeight() - 30;
        int pocketSize = 40;

        g2.setColor(Color.red);
        g2.fill(new Ellipse2D.Double(pocketX - pocketSize / 2, pocketY - pocketSize / 2, pocketSize, pocketSize));
        //

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

//        for(int i = 0; i < balls.size(); i++){
//            Ball b = balls.get(i);
//            b.draw(g2);
//        }
    }
}
