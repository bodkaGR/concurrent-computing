package net.bodkasoft.billiards;

import net.bodkasoft.billiards.ball.Ball;
import net.bodkasoft.billiards.ball.BallThread;
import net.bodkasoft.billiards.pocket.Pocket;
import net.bodkasoft.billiards.utils.Pair;
import net.bodkasoft.billiards.utils.SymbolPrinter;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Random;

public class BounceFrame extends JFrame {

    private final Canvas canvas;
    private final JLabel counterLabel;
    private final Random random = new Random();
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

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
        JButton buttonExperiment = new JButton("Experiment");
        JButton buttonJoinExperiment = new JButton("Join experiment");
        JButton buttonSymbols = new JButton("Symbols");

        Map<JButton, Pair<Color, Integer>> buttonConfig = Map.of(
                buttonStart, new Pair<>(Color.black, Thread.NORM_PRIORITY),
                buttonRedB, new Pair<>(Color.red, Thread.MAX_PRIORITY),
                buttonBlueB, new Pair<>(Color.blue, Thread.MIN_PRIORITY)
        );

        // Balls with different priority
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

        // Experiment with many balls
        buttonExperiment.addActionListener(e -> {
            for (int i = 1; i <= 1000; i++) {
                Ball blueBall = new Ball(0, 0, Color.blue);
                canvas.addBall(blueBall);
                BallThread thread2 = new BallThread(blueBall, canvas);
                thread2.setPriority(Thread.MIN_PRIORITY);
                thread2.start();
            }

            Ball redBall = new Ball(0, 0, Color.red);
            canvas.addBall(redBall);
            BallThread thread = new BallThread(redBall, canvas);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        });

        // Join experiment
        buttonJoinExperiment.addActionListener(e -> {
            int x = random.nextInt(canvas.getWidth());
            int y = random.nextInt(canvas.getHeight());

            Ball greenBall = new Ball(x, y, Color.green);
            Ball orangeBall = new Ball(x, y, Color.orange);

            canvas.addBall(greenBall);
            canvas.addBall(orangeBall);

            BallThread greenThread = new BallThread(greenBall, canvas);
            BallThread orangeThread = new BallThread(orangeBall, canvas, greenThread);

            greenThread.start();
            orangeThread.start();
        });

        // Prints characters into a string
        buttonSymbols.addActionListener(e -> {
            SymbolPrinter symbolPrinter = new SymbolPrinter();

            Thread dashThread = new Thread(() -> symbolPrinter.printSymbol("-", true));
            Thread pipeThread = new Thread(() -> symbolPrinter.printSymbol("|", false));

            dashThread.start();
            pipeThread.start();
        });

        // Exit
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonRedB);
        buttonPanel.add(buttonBlueB);
        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonJoinExperiment);
        buttonPanel.add(buttonSymbols);
        buttonPanel.add(buttonStop);
        buttonPanel.add(counterLabel);
        return buttonPanel;
    }
}