package net.bodkasoft.bank.bankapp;

import net.bodkasoft.bank.bankapp.bank.Bank;
import net.bodkasoft.bank.bankapp.bank.LockBank;
import net.bodkasoft.bank.bankapp.bank.SynchronizedMethodBank;
import net.bodkasoft.bank.bankapp.bank.WaitNotifyBank;
import net.bodkasoft.bank.bankapp.observer.BankMonitor;
import net.bodkasoft.bank.bankapp.thread.TransferThread;

public class BankApplication {

    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {
        Bank bank = new WaitNotifyBank(NACCOUNTS, INITIAL_BALANCE);
        bank.setObserver(nTransacts -> BankMonitor.checkBankAccounts(nTransacts, bank));

        for (int i = 0; i < NACCOUNTS; i++){
            TransferThread transferThread = new TransferThread(bank, i, INITIAL_BALANCE);
            transferThread.setPriority(Thread.NORM_PRIORITY + i % 2);
            transferThread.start();
        }
    }
}