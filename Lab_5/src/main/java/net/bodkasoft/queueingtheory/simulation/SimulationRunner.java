package net.bodkasoft.queueingtheory.simulation;

import net.bodkasoft.queueingtheory.stat.SimulationResult;

import java.util.concurrent.Callable;

public class SimulationRunner implements Callable<SimulationResult> {

    private final int totalCustomers;
    private final int numServers;
    private final int queueCapacity;

    public SimulationRunner(int totalCustomers, int numServers, int queueCapacity) {
        this.totalCustomers = totalCustomers;
        this.numServers = numServers;
        this.queueCapacity = queueCapacity;
    }

    @Override
    public SimulationResult call() {
        SOMSimulation simulation = new SOMSimulation(totalCustomers, numServers, queueCapacity);
        return simulation.startSimulation();
    }
}
