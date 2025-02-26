package net.bodkasoft.bank.journal;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Journal {

    private final Map<String, List<Integer>> grades = new HashMap<>();
    private final Map<String, Lock> studentLocks = new HashMap<>();

    public Journal(List<String> students, int maxGradesAmount) {
        for (String student : students) {
            grades.put(student, new ArrayList<>(Collections.nCopies(maxGradesAmount, null)));
            studentLocks.put(student, new ReentrantLock());
        }
    }

    public void addGrade(String student, int grade) {
        Lock lock = studentLocks.get(student);
        lock.lock();
        try {
            List<Integer> studentGrades = grades.get(student);
            for (int i = 0; i < studentGrades.size(); i++) {
                if (studentGrades.get(i) == null) {
                    studentGrades.set(i, grade);
                    break;
                }
            }
            grades.put(student, studentGrades);
        } finally {
            lock.unlock();
        }
    }

    public void printStudentsGrades() {
        System.out.println("<---Electronic Journal--->");
        for(Map.Entry<String, List<Integer>> entry: grades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
