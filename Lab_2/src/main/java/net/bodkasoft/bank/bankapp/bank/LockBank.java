package net.bodkasoft.bank.bankapp.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank extends Bank {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public LockBank(int nAccounts, int initialBalance) {
        super(nAccounts, initialBalance);
    }

    @Override
    public void transfer(int fromAccount, int toAccount, int amount) {
        lock.lock();
        try {
            while (accounts[fromAccount] < amount) {
                try {
                    condition.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            accounts[fromAccount] -= amount;
            accounts[toAccount] += amount;
            nTransacts++;
            condition.signalAll();

            if (bankObserver != null){
                bankObserver.onTransaction(nTransacts);
            }
        } finally {
            lock.unlock();
        }
    }
}
