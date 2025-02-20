package net.bodkasoft.bank.journal.universityemployee;

import net.bodkasoft.bank.journal.Journal;

import java.util.List;

public class Lecturer extends UniversityEmployee implements Runnable {

    public Lecturer(List<String> students, Journal journal, int weeksAmount) {
        super(students, journal, weeksAmount);
    }

    @Override
    public void run() {
        for (int i = 0; i < weeksAmount; i++) {
            for (String student: students) {
                int grade = random.nextInt(100);
                journal.addGrade(student, grade);
                System.out.println("Lecturer graded " + student + " with " + grade);
            }
        }
    }
}
