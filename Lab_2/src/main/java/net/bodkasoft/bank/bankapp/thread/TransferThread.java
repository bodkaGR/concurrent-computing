package net.bodkasoft.bank.bankapp.thread;

import net.bodkasoft.bank.bankapp.bank.Bank;

public class TransferThread extends Thread {
    private final Bank bank;
    private final int fromAccount;
    private final int maxAmount;
    private static final int REPS = 1000;

    public TransferThread(Bank bank, int fromAccount, int maxAmount){
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run(){
        while (true) {
            for (int i = 0; i < REPS; i++) {
                int toAccount = (int) (bank.size() * Math.random());
                int amount = (int) (maxAmount * Math.random()/REPS);
                bank.transfer(fromAccount, toAccount, amount);
            }
        }
    }
}
