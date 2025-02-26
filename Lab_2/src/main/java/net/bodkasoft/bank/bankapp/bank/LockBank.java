package net.bodkasoft.bank.bankapp.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank extends Bank {

    private final Lock lock = new ReentrantLock();

    public LockBank(int nAccounts, int initialBalance) {
        super(nAccounts, initialBalance);
    }

    @Override
    public void transfer(int fromAccount, int toAccount, int amount) {
        lock.lock();
        try {
            accounts[fromAccount] -= amount;
            accounts[toAccount] += amount;
            nTransacts++;

            if (bankObserver != null){
                bankObserver.onTransaction(nTransacts);
            }
        } finally {
            lock.unlock();
        }
    }
}
