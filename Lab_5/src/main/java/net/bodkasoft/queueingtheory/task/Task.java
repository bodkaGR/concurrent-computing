package net.bodkasoft.queueingtheory.task;

import net.bodkasoft.queueingtheory.Equations;

/**
 * Task to be executed by Consumer
 */
public class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(Math.round(Equations.consumptionTime(0.09)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
