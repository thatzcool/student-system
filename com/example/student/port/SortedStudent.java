package com.example.student.port;
import com.example.student.model.Student;
import java.util.Comparator;
public interface SortedStudent {
    static Comparator<Student> byNameAsc()       { return Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER); }
    static Comparator<Student> byAverageDesc()   { return Comparator.comparingDouble(Student::getAverage).reversed(); }
    static Comparator<Student> byGradeThenName() { return Comparator.comparingInt(Student::getGrade).thenComparing(Student::getName); }
}