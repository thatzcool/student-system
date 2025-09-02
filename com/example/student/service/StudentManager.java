package com.example.student.service;
import com.example.student.model.Student;
import com.example.student.port.StudentIO;
import com.example.student.port.SearchStudent;
import java.io.IOException;
import java.util.*;
public final class StudentManager {
    private static final StudentManager INSTANCE = new StudentManager();
    private StudentIO repo;
    private StudentManager() {}
    public static StudentManager getInstance() { return INSTANCE; }
    public void setRepository(StudentIO repo) { this.repo = Objects.requireNonNull(repo); }
    public Student register(Student s) throws IOException { validate(s); return repo.save(s); }
    public boolean remove(String id) throws IOException { return repo.deleteById(id); }
    public List<Student> find(SearchStudent cond) throws IOException { return repo.search(cond); }
    public List<Student> listAllSorted(Comparator<Student> comp) throws IOException { return repo.sorted(comp); }
    public Optional<Student> get(String id) throws IOException { return repo.findById(id); }
    private void validate(Student s) {
        if (s.getId()==null || s.getId().isBlank()) throw new IllegalArgumentException("학번은 필수");
        if (s.getGrade()<1 || s.getGrade()>4) throw new IllegalArgumentException("학년은 1~4");
        if (s.getAverage()<0.0 || s.getAverage()>4.5) throw new IllegalArgumentException("평점은 0.0~4.5");
    }
}