package net.bodkasoft.queueingtheory.consumer;

import net.bodkasoft.queueingtheory.stat.Statistics;
import net.bodkasoft.queueingtheory.task.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Consumer to execute tasks
 */
public class Consumer implements Runnable {

    private final BlockingQueue<Runnable> queue;
    private final Statistics statistics;

    public Consumer(BlockingQueue<Runnable> queue, Statistics statistics) {
        this.queue = queue;
        this.statistics = statistics;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Runnable task = queue.take();
                task.run();
                statistics.incrementProcessed();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
