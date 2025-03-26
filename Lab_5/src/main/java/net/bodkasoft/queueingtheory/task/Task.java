package net.bodkasoft.queueingtheory.task;

import net.bodkasoft.queueingtheory.Equations;
import net.bodkasoft.queueingtheory.stat.Statistics;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Task to be executed by Consumer
 */
public class Task implements Runnable {

    private final Statistics statistics;

    public Task(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Math.round(Equations.serviceTime(0.09)));
            statistics.incrementProcessed();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
