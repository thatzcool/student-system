package com.example.student.port;
import com.example.student.model.Student;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
public interface StudentIO {
    boolean exists(String id) throws IOException;
    Student save(Student s) throws IOException;
    Optional<Student> findById(String id) throws IOException;
    List<Student> findAll() throws IOException;
    boolean deleteById(String id) throws IOException;
    long count() throws IOException;
    List<Student> search(Predicate<Student> filter) throws IOException;
    List<Student> sorted(Comparator<Student> comp) throws IOException;
}