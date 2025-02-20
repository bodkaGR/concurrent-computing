package net.bodkasoft.bank.bankapp.observer;

import net.bodkasoft.bank.bankapp.bank.Bank;

public class BankMonitor {

    public static void checkBankAccounts(long nTransacts, Bank bank) {
        if (nTransacts % Bank.NTEST == 0) {
            int sum = 0;
            for (int account: bank.getAccounts()){
                sum += account;
            }
            System.out.println("Transactions:" + nTransacts + " Sum: " + sum);
        }
    }
}