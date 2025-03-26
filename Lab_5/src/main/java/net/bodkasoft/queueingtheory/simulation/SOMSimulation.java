package net.bodkasoft.queueingtheory.simulation;

import net.bodkasoft.queueingtheory.monitor.QueueMonitor;
import net.bodkasoft.queueingtheory.consumer.Consumer;
import net.bodkasoft.queueingtheory.stat.Statistics;
import net.bodkasoft.queueingtheory.producer.Producer;

import java.util.concurrent.*;

public class SOMSimulation {

    private final int TOTAL_CUSTOMERS = 10000;

    private final ExecutorService pool;
    private final BlockingQueue<Runnable> queue;
    private final Statistics statistics;

    public SOMSimulation(BlockingQueue<Runnable> queue, ExecutorService pool, Statistics statistics) {
        this.queue = queue;
        this.pool = pool;
        this.statistics = statistics;
    }

    public void startSimulation() {
        pool.execute(new Producer(queue, TOTAL_CUSTOMERS, statistics));

        for (int i = 0; i < 4; i++) {
            pool.execute(new Consumer(queue, statistics));
        }

        pool.execute(new QueueMonitor(queue, statistics));

        shutdownPool(pool);

        System.out.println("Avg queue size: " + statistics.getAverageQueueLength());
        System.out.println("Rejection probability: " + statistics.getRejectionProbability(TOTAL_CUSTOMERS));
    }

    private void shutdownPool(ExecutorService threadPool) {
        threadPool.shutdown();
        try{
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        }catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
