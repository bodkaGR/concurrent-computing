package net.bodkasoft.bank.producerconsumer;

public class Drop {

    private int message;
    private boolean isEmpty = true;

    public synchronized int take() {
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        isEmpty = true;
        System.out.println("MESSAGE RECEIVED: " + message);
        notifyAll();
        return message;
    }

    public synchronized void put(int message) {
        while (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        isEmpty = false;

        this.message = message;
        System.out.println("MESSAGE SENT: " + message);
        notifyAll();
    }
}
