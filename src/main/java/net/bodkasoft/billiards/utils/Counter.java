package net.bodkasoft.billiards.utils;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Counter {

    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public synchronized void synchronizedIncMethod() {
        count++;
    }

    public synchronized void synchronizedDecMethod() {
        count--;
    }

    public void synchronizedIncBlock() {
        synchronized (this) {
            count++;
        }
    }

    public void synchronizedDecBlock() {
        synchronized (this) {
            count--;
        }
    }

    public void lockedIncMethod() {
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }

    public void lockedDecMethod() {
        lock.lock();
        try {
            count--;
        }finally {
            lock.unlock();
        }
    }

    public void reset() {
        this.count = 0;
    }
}
