package net.bodkasoft.bank.producerconsumer;

public class Drop {
    /**
     Message sent from producer
     to consumer.
     */
    private int message;

    /**
     True if consumer should wait
     for producer to send message,
     false if producer should wait for
     consumer to retrieve message.
     */
    private boolean isEmpty = true;

    public synchronized int take() {
        // Wait until message is available.
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        // Toggle status.
        isEmpty = true;

        // Notify producer that status has changed.
        notifyAll();
        return message;
    }

    public synchronized void put(int message) {
        // Wait until message has been retrieved.
        while (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        // Toggle status.
        isEmpty = false;

        // Store message.
        this.message = message;

        // Notify consumer that status has changed.
        notifyAll();
    }
}
