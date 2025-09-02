package com.example.student.port;
import com.example.student.model.Student;
import java.util.Objects;
import java.util.function.Predicate;
public interface SearchStudent extends Predicate<Student> {
    static SearchStudent byNameContains(String keyword) {
        return s -> s.getName()!=null && s.getName().contains(keyword);
    }
    static SearchStudent byGrade(int g) { return s -> s.getGrade()==g; }
    static SearchStudent byMajor(String m) { return s -> Objects.equals(s.getMajor(), m); }
}