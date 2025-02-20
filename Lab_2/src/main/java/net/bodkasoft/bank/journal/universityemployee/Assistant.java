package net.bodkasoft.bank.journal.universityemployee;

import net.bodkasoft.bank.journal.Journal;

import java.util.List;

public class Assistant extends UniversityEmployee implements Runnable {

    private final String name;

    public Assistant(List<String> students, Journal journal, String name, int weeksAmount) {
        super(students, journal, weeksAmount);
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < weeksAmount; i++) {
            for (String student: students) {
                int grade = random.nextInt(100);
                journal.addGrade(student, grade);
                System.out.println(name + " graded " + student + " with " + grade);
            }
        }
    }
}
