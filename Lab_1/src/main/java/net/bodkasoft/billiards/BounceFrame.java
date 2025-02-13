package net.bodkasoft.billiards;

import net.bodkasoft.billiards.ball.Ball;
import net.bodkasoft.billiards.ball.BallThread;
import net.bodkasoft.billiards.pocket.Pocket;
import net.bodkasoft.billiards.utils.Counter;
import net.bodkasoft.billiards.utils.Pair;
import net.bodkasoft.billiards.utils.SymbolPrinter;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Random;

public class BounceFrame extends JFrame {

    private final Canvas canvas;
    private final JLabel pocketedLabel;
    private final JLabel counterLabel;
    private final Counter counter;
    private final Random random = new Random();
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 600;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");

        counter = new Counter();

        pocketedLabel = new JLabel("Pocketed: 0");
        counterLabel = new JLabel("Counter: 0");

        this.canvas = new Canvas(pocketedLabel);
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

        JButton buttonBlackB = new JButton("Black ball");
        JButton buttonRedB = new JButton("Red ball");
        JButton buttonBlueB = new JButton("Blue ball");

        JButton buttonExperiment = new JButton("Experiment");
        JButton buttonJoinExperiment = new JButton("Join experiment");

        JButton buttonSymbols = new JButton("Symbols");

        JButton buttonWithoutSync = new JButton("Without sync");
        JButton buttonWithMethodSync = new JButton("With method sync");
        JButton buttonWithBlockSync = new JButton("With block sync");
        JButton buttonWithLock = new JButton("With lock");

        JButton buttonStop = new JButton("Stop");

        Map<JButton, Pair<Color, Integer>> buttonConfig = Map.of(
                buttonBlackB, new Pair<>(Color.black, Thread.NORM_PRIORITY),
                buttonRedB, new Pair<>(Color.red, Thread.MAX_PRIORITY),
                buttonBlueB, new Pair<>(Color.blue, Thread.MIN_PRIORITY)
        );

        // Balls with different priority
        buttonConfig.forEach((button, config) -> {
            button.addActionListener(e -> addButtonsWithDifferentPriorities(config));
            buttonPanel.add(button);
        });

        // Experiment with many balls
        buttonExperiment.addActionListener(e -> addExperiment());

        // Join experiment
        buttonJoinExperiment.addActionListener(e -> addJoinExperiment());

        // Prints characters into a string
        buttonSymbols.addActionListener(e -> addSymbolPrinting());

        // Counter experiment
        Map<JButton, Pair<Runnable, Runnable>> syncButtons = Map.of(
                buttonWithoutSync, new Pair<>(counter::increment, counter::decrement),
                buttonWithMethodSync, new Pair<>(counter::synchronizedIncMethod, counter::synchronizedDecMethod),
                buttonWithBlockSync, new Pair<>(counter::synchronizedIncBlock, counter::synchronizedDecBlock),
                buttonWithLock, new Pair<>(counter::lockedIncMethod, counter::lockedDecMethod)
        );

        syncButtons.forEach((button, config) -> {
            button.addActionListener(e -> {
                addIncDecCounter(config.getKey(), config.getValue());
                System.out.println("Counter: " + counter.getCount());
                counterLabel.setText("Counter: " + counter.getCount());
                counter.reset();
            });
            buttonPanel.add(button);
        });

        // Exit
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonJoinExperiment);
        buttonPanel.add(buttonSymbols);
        buttonPanel.add(buttonStop);
        buttonPanel.add(counterLabel);
        buttonPanel.add(pocketedLabel);
        return buttonPanel;
    }

    private void addButtonsWithDifferentPriorities(Pair<Color, Integer> config) {
        int x = random.nextInt(canvas.getWidth());
        int y = random.nextInt(canvas.getHeight());

        Ball ball = new Ball(x, y, config.getKey());
        canvas.addBall(ball);
        BallThread thread = new BallThread(ball, canvas);
        thread.setPriority(config.getValue());
        thread.start();
        System.out.println("Thread name = " + thread.getName());
    }

    private void addExperiment() {
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
    }

    private void addJoinExperiment() {
        int x = random.nextInt(canvas.getWidth() - 20);
        int y = random.nextInt(canvas.getHeight() - 20);

        Ball greenBall = new Ball(x, y, Color.green);
        Ball orangeBall = new Ball(x, y, Color.orange);

        canvas.addBall(greenBall);
        canvas.addBall(orangeBall);

        BallThread greenThread = new BallThread(greenBall, canvas);
        BallThread orangeThread = new BallThread(orangeBall, canvas, greenThread);

        greenThread.start();
        orangeThread.start();
    }

    private void addSymbolPrinting() {
        SymbolPrinter symbolPrinter = new SymbolPrinter();

        Thread dashThread = new Thread(() -> symbolPrinter.printSymbol("-", true));
        Thread pipeThread = new Thread(() -> symbolPrinter.printSymbol("|", false));

        dashThread.start();
        pipeThread.start();
    }

    private void addIncDecCounter(Runnable incMethod, Runnable decMethod) {
        Thread incThread = new Thread(() -> {
           for (int i = 0; i < 100000; i++) {
               incMethod.run();
           }
        });

        Thread decThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                decMethod.run();
            }
        });

        incThread.start();
        decThread.start();

        try{
            incThread.join();
            decThread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
