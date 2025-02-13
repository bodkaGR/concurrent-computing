package net.bodkasoft.bank.bank;

public class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long nTransacts;

    public Bank(int nAccounts, int initialBalance){
        nTransacts = 0;
        accounts = new int[nAccounts];

        for (int i = 0; i < accounts.length; i++){
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int fromAccount, int toAccount, int amount) {
        accounts[fromAccount] -= amount;
        accounts[toAccount] += amount;
        nTransacts++;

        if (nTransacts % NTEST == 0){
            test();
        }
    }

    public void test(){
        int sum = 0;
        for (int account: accounts){
            sum += account;
        }
        System.out.println("Transactions:" + nTransacts + " Sum: " + sum);
    }

    public int size(){
        return accounts.length;
    }
}
