package net.bodkasoft.bank;

import net.bodkasoft.bank.bank.Bank;
import net.bodkasoft.bank.thread.TransferThread;

public class Main {
    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++){
            TransferThread transferThread = new TransferThread(bank, i, INITIAL_BALANCE);
            transferThread.setPriority(Thread.NORM_PRIORITY + i % 2);
            transferThread.start();
        }
    }
}