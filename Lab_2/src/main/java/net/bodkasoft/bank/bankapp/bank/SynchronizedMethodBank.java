package net.bodkasoft.bank.bankapp.bank;

public class SynchronizedMethodBank extends Bank {

    public SynchronizedMethodBank(int nAccounts, int initialBalance) {
        super(nAccounts, initialBalance);
    }

    @Override
    public synchronized void transfer(int fromAccount, int toAccount, int amount) {
        accounts[fromAccount] -= amount;
        accounts[toAccount] += amount;
        nTransacts++;

        if (bankObserver != null){
            bankObserver.onTransaction(nTransacts);
        }
    }
}
