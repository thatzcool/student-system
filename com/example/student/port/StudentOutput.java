package com.example.student.port;
import com.example.student.model.Student;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
public interface StudentOutput {
    void printOne(Student s);
    void printList(List<Student> list);
    void exportCsv(List<Student> list, Path target) throws IOException;
}