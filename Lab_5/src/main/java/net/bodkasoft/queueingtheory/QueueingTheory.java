package net.bodkasoft.queueingtheory;

import net.bodkasoft.queueingtheory.simulation.SOMSimulation;
import net.bodkasoft.queueingtheory.simulation.SimulationRunner;
import net.bodkasoft.queueingtheory.stat.SimulationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class QueueingTheory {

    private static final int TOTAL_CUSTOMERS = 100000;
    private static final int NUM_SERVERS = 6;
    private static final int QUEUE_CAPACITY = 5;
    private static final int NUM_SIMULATIONS = 4;

    public static void main(String[] args) {
        // Simulation with 1 run
        SimulationResult result = getSimulationResult();

        System.out.println("\nAvg queue size: " + result.getAvgQueueLength());
        System.out.println("Rejection probability: " + result.getRejectionProbability());

        // Simulation with 4 runs
        List<Future<SimulationResult>> results = getSimulationResultsBuRuns();
        results.forEach(future -> {
            try {
                System.out.println("\nAvg queue size: " + future.get().getAvgQueueLength());
                System.out.println("Rejection probability: " + future.get().getRejectionProbability());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static SimulationResult getSimulationResult() {
        SOMSimulation simulation = new SOMSimulation(TOTAL_CUSTOMERS, NUM_SERVERS, QUEUE_CAPACITY);
        return simulation.startSimulation();
    }

    private static List<Future<SimulationResult>> getSimulationResultsBuRuns() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_SIMULATIONS);
        List<Future<SimulationResult>> results = new ArrayList<>();

        for (int i = 0; i < NUM_SIMULATIONS; i++) {
            results.add(executor.submit(new SimulationRunner(TOTAL_CUSTOMERS, NUM_SERVERS, QUEUE_CAPACITY)));
        }

        executor.shutdown();
        return results;
    }
}