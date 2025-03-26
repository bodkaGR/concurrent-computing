package net.bodkasoft.queueingtheory;

import net.bodkasoft.queueingtheory.simulation.SOMSimulation;
import net.bodkasoft.queueingtheory.stat.Statistics;

import java.util.concurrent.*;

public class QueueingTheory {
    private static final int NUM_SERVERS = 6;
    private static final int QUEUE_CAPACITY = 5;

    private static final Statistics statistics = new Statistics();
    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
//    private static final ExecutorService threadPool = new ThreadPoolExecutor(NUM_SERVERS, NUM_SERVERS, 0L, TimeUnit.MILLISECONDS, queue, new ThreadPoolExecutor.DiscardPolicy());
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(NUM_SERVERS);

    public static void main(String[] args) {
        SOMSimulation simulation = new SOMSimulation(queue, threadPool, statistics);
        simulation.startSimulation();
    }
}