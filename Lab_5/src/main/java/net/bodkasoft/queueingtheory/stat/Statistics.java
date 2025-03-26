package net.bodkasoft.queueingtheory.stat;

import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    private final AtomicInteger rejectedCustomers = new AtomicInteger(0);
    private final AtomicInteger processedCustomers = new AtomicInteger(0);
    private final AtomicInteger totalQueueLength = new AtomicInteger(0);
    private final AtomicInteger queueObservations = new AtomicInteger(0);

    public void incrementRejected() {
        rejectedCustomers.incrementAndGet();
    }

    public void incrementProcessed() {
        processedCustomers.incrementAndGet();
    }

    public void addQueueLength(int length) {
        totalQueueLength.addAndGet(length);
        queueObservations.incrementAndGet();
    }

    public double getAverageQueueLength() {
        return queueObservations.get() == 0 ? 0 : (double) totalQueueLength.get() / queueObservations.get();
    }

    public double getRejectionProbability(int totalCustomers) {
        return (double) rejectedCustomers.get() / totalCustomers;
    }
}
