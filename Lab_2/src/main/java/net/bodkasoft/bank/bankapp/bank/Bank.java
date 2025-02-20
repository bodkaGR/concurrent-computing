package net.bodkasoft.bank.bankapp.bank;

import lombok.Getter;
import net.bodkasoft.bank.bankapp.observer.BankObserver;

import java.util.Arrays;

@Getter
public abstract class Bank {

    public static final int NTEST = 10000;
    protected final int[] accounts;
    protected long nTransacts;
    protected BankObserver bankObserver;

    public Bank(int nAccounts, int initialBalance){
        nTransacts = 0;
        accounts = new int[nAccounts];
        Arrays.fill(accounts, initialBalance);
    }

    public abstract void transfer(int fromAccount, int toAccount, int amount);

    public void setObserver(BankObserver bankObserver){
        this.bankObserver = bankObserver;
    }

    public int size(){
        return accounts.length;
    }
}
