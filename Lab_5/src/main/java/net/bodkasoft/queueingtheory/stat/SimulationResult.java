package net.bodkasoft.queueingtheory.stat;

public class SimulationResult {

    private final double avgQueueLength;
    private final double rejectedProbability;

    public SimulationResult(double avgQueueLength, double rejectedProbability) {
        this.avgQueueLength = avgQueueLength;
        this.rejectedProbability = rejectedProbability;
    }

    public double getAvgQueueLength() {
        return avgQueueLength;
    }

    public double getRejectionProbability() {
        return rejectedProbability;
    }
}
