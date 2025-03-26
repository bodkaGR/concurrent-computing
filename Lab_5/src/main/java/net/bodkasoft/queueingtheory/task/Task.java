package net.bodkasoft.queueingtheory.task;

import net.bodkasoft.queueingtheory.Equations;
import net.bodkasoft.queueingtheory.stat.Statistics;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Task to be executed by Consumer
 */
public class Task implements Runnable {

    private static final int SERVICE_TIME_MIN = 500;
    private static final int SERVICE_TIME_MAX = 2000;
    private static final double MEAN_SERVICE_TIME = (SERVICE_TIME_MIN + SERVICE_TIME_MAX) / 2.0;
    private static final double STD_DEV_SERVICE_TIME = MEAN_SERVICE_TIME * 0.3; // 30% від середнього
    private final Statistics statistics;

    public Task(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Equations.normalDistribution(MEAN_SERVICE_TIME, STD_DEV_SERVICE_TIME));
            statistics.incrementProcessed();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
