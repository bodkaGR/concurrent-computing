package net.bodkasoft.billiards;

import net.bodkasoft.billiards.ball.Ball;
import net.bodkasoft.billiards.ball.BallThread;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {

    private Canvas canvas;
    private JLabel counterLabel;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        setupFrame();

        counterLabel = new JLabel("Pocketed: 0");
        this.canvas = new Canvas(counterLabel);

        addComponents();
        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
    }

    private void addComponents() {
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        content.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void setupFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> startBallThread());
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(counterLabel);
        return buttonPanel;
    }

    private void startBallThread() {
        Ball b = new Ball(canvas);
        canvas.add(b);
        BallThread thread = new BallThread(b);
        thread.start();
        System.out.println("Thread name = " + thread.getName());
    }
}

