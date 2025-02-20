package net.bodkasoft.bank.bankapp.bank;

public class WaitNotifyBank extends Bank {

    public WaitNotifyBank(int nAccounts, int initialBalance) {
        super(nAccounts, initialBalance);
    }

    @Override
    public void transfer(int fromAccount, int toAccount, int amount) {
        while (accounts[fromAccount] < amount) {
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        accounts[fromAccount] -= amount;
        accounts[toAccount] += amount;
        nTransacts++;
        notifyAll();

        if (bankObserver != null){
            bankObserver.onTransaction(nTransacts);
        }
    }
}
