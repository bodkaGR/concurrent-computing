package net.bodkasoft.bank.journal.universityemployee;

import net.bodkasoft.bank.journal.Journal;

import java.util.List;
import java.util.Random;

public abstract class UniversityEmployee {
    protected Journal journal;
    protected List<String> students;
    protected final int weeksAmount;
    protected Random random = new Random();

    protected UniversityEmployee(List<String> students, Journal journal, int weeksAmount) {
        this.students = students;
        this.journal = journal;
        this.weeksAmount = weeksAmount;
    }
}
