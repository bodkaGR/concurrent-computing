package net.bodkasoft.queueingtheory.simulation;

import net.bodkasoft.queueingtheory.monitor.QueueMonitor;
import net.bodkasoft.queueingtheory.consumer.Consumer;
import net.bodkasoft.queueingtheory.stat.SimulationResult;
import net.bodkasoft.queueingtheory.stat.Statistics;
import net.bodkasoft.queueingtheory.producer.Producer;

import java.util.concurrent.*;

public class SOMSimulation {

    private final int TOTAL_CUSTOMERS;

    private final Statistics statistics = new Statistics();
    private final BlockingQueue<Runnable> queue;
    private final ExecutorService pool;

    public SOMSimulation(int totalCustomers, int numServers, int queueCapacity) {
        this.TOTAL_CUSTOMERS = totalCustomers;
        this.pool = Executors.newFixedThreadPool(numServers);
        this.queue = new ArrayBlockingQueue<>(queueCapacity);
    }

    public SimulationResult startSimulation() {
        pool.execute(new Producer(queue, TOTAL_CUSTOMERS, statistics));

        for (int i = 0; i < 4; i++) {
            pool.execute(new Consumer(queue, statistics));
        }

        pool.execute(new QueueMonitor(queue, statistics));

        shutdownPool(pool);

        double avgQueueSize = statistics.getAverageQueueLength();
        double rejectionProbability = statistics.getRejectionProbability(TOTAL_CUSTOMERS);

//        System.out.println("\nAvg queue size: " + statistics.getAverageQueueLength());
//        System.out.println("Rejection probability: " + statistics.getRejectionProbability(TOTAL_CUSTOMERS));

        return new SimulationResult(avgQueueSize, rejectionProbability);
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
