package net.bodkasoft.bank.journal;

import net.bodkasoft.bank.journal.universityemployee.Assistant;
import net.bodkasoft.bank.journal.universityemployee.Lecturer;

import java.util.List;
import java.util.stream.Stream;

public class JournalApplication {

    private static final int MAX_GRADES_AMOUNT = 10;
    private static final int WEEKS_AMOUNT = 3;

    public static void main(String[] args) {

        List<String> group = List.of("Serhii", "Bober", "Maxim", "Volodimir", "Matvei", "Pheodosyi", "Nadia", "Nataliia", "Olha");

        Journal journal = new Journal(group, MAX_GRADES_AMOUNT);

        Thread lecturer = new Thread(new Lecturer(group, journal, WEEKS_AMOUNT));
        Thread assistant1 = new Thread(new Assistant(group, journal, "Assistant 1", WEEKS_AMOUNT));
        Thread assistant2 = new Thread(new Assistant(group, journal, "Assistant 2", WEEKS_AMOUNT));
        Thread assistant3 = new Thread(new Assistant(group, journal, "Assistant 3", WEEKS_AMOUNT));

        lecturer.start();
        assistant1.start();
        assistant2.start();
        assistant3.start();

        try {
            lecturer.join();
            assistant1.join();
            assistant2.join();
            assistant3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        journal.printStudentsGrades();
    }
}
