package net.bodkasoft.bank.symbolprinter.printer;

public class SymbolPrinter {

    private int turn = 0;

    public synchronized void print(char symbol, int threadTurn) {
        while (turn % 3 != threadTurn) {
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(symbol);
        turn++;
        if (turn % 30 == 0) {
            System.out.println();
        }
        notifyAll();
    }
}
