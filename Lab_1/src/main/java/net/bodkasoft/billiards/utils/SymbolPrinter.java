package net.bodkasoft.billiards.utils;

public class SymbolPrinter {

    private boolean isPrintDash = true;

    public synchronized void printSymbol(String symbol, boolean isDash) {
        for (int i = 0; i < 100; i++) {
            while (isPrintDash != isDash) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(symbol);
            isPrintDash = !isPrintDash;
            notify();
        }
    }

    public void printSymbol(String symbol) {
        for (int i = 0; i < 100; i++) {
            System.out.print(symbol);
        }
    }
}
