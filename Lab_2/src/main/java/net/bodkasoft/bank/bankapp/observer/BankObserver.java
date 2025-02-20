package net.bodkasoft.bank.bankapp.observer;

@FunctionalInterface
public interface BankObserver {
    void onTransaction(long nTransacts);
}