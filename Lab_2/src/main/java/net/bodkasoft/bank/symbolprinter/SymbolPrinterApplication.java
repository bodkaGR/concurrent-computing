package net.bodkasoft.bank.symbolprinter;

import net.bodkasoft.bank.symbolprinter.printer.SymbolPrinter;

public class SymbolPrinterApplication {

    private static final int LINES = 90;
    private static final SymbolPrinter printer = new SymbolPrinter();

    public static void main(String[] args) {

        Thread pipeThread = new Thread(() -> printSymbolLines(LINES, '|', 0));
        Thread slashThread = new Thread(() -> printSymbolLines(LINES, '/', 2));
        Thread backSlashThread = new Thread(() -> printSymbolLines(LINES, '\\', 1));

        pipeThread.start();
        slashThread.start();
        backSlashThread.start();

        try {
            pipeThread.join();
            slashThread.join();
            backSlashThread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printing completed");
    }

    private static void printSymbolLines(int lines, char symbol, int threadTurn) {
        for (int i = 0; i < lines; i++) {
            printer.print(symbol, threadTurn);
        }
    }
}
