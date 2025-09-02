package com.example.student.port;
import com.example.student.model.Student;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
public interface StudentInput {
    Student parseSingle(Map<String, String> form) throws IllegalArgumentException;
    List<Student> importCsv(Path csvPath) throws IOException;
}