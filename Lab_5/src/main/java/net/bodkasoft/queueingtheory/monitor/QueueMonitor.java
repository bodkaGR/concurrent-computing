package net.bodkasoft.queueingtheory.monitor;

import net.bodkasoft.queueingtheory.stat.Statistics;

import java.util.concurrent.BlockingQueue;

public class QueueMonitor implements Runnable{
    private final BlockingQueue<Runnable> queue;
    private final Statistics statistics;

    public QueueMonitor(BlockingQueue<Runnable> queue, Statistics statistics) {
        this.queue = queue;
        this.statistics = statistics;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int queueSize = queue.size();
            statistics.addQueueLength(queueSize);
            System.out.println("Поточна кількість задач у черзі: " + queueSize);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
