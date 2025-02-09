package net.bodkasoft.billiards;

import net.bodkasoft.billiards.ball.Ball;
import net.bodkasoft.billiards.ball.BallThread;
import net.bodkasoft.billiards.pocket.Pocket;
import net.bodkasoft.billiards.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Random;

public class BounceFrame extends JFrame {

    private final Canvas canvas;
    private final JLabel counterLabel;
    private final Random random = new Random();
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");

        counterLabel = new JLabel("Pocketed: 0");

        this.canvas = new Canvas(counterLabel);
        Pocket pocket = new Pocket(canvas.getWidth(), canvas.getHeight());
        canvas.addPocket(pocket);

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        content.add(createButtonPanel(), BorderLayout.SOUTH);

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");
        JButton buttonRedB = new JButton("Red ball");
        JButton buttonBlueB = new JButton("Blue ball");

        Map<JButton, Pair<Color, Integer>> buttonConfig = Map.of(
                buttonStart, new Pair<>(Color.black, Thread.NORM_PRIORITY),
                buttonRedB, new Pair<>(Color.red, Thread.MAX_PRIORITY),
                buttonBlueB, new Pair<>(Color.blue, Thread.MIN_PRIORITY)
        );

        buttonConfig.forEach((button, config) -> button.addActionListener(e -> {
            int x = random.nextInt(canvas.getWidth());
            int y = random.nextInt(canvas.getHeight());

            Ball ball = new Ball(x, y, config.getKey());
            canvas.addBall(ball);
            BallThread thread = new BallThread(ball, canvas);
            thread.setPriority(config.getValue());
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        }));

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonRedB);
        buttonPanel.add(buttonBlueB);
        buttonPanel.add(buttonStop);
        buttonPanel.add(counterLabel);
        return buttonPanel;
    }
}

