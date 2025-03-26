package net.bodkasoft.queueingtheory.producer;

import net.bodkasoft.queueingtheory.Equations;
import net.bodkasoft.queueingtheory.stat.Statistics;
import net.bodkasoft.queueingtheory.task.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Producer for loading tasks in queue
 */
public class Producer implements Runnable {

    private final BlockingQueue<Runnable> queue;
    private final int totalCustomers;
    private final Statistics statistics;

    public Producer(BlockingQueue<Runnable> queue, int totalCustomers, Statistics statistics) {
        this.queue = queue;
        this.totalCustomers = totalCustomers;
        this.statistics = statistics;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalCustomers; i++) {
            Runnable task = new Task(statistics);
            if (!queue.offer(task)) {
                statistics.incrementRejected();
            }
            try {
                Thread.sleep(Equations.evenDistribution(100, 500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
