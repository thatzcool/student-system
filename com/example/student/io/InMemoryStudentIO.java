package com.example.student.io;
import com.example.student.model.Student;
import com.example.student.port.StudentIO;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class InMemoryStudentIO implements StudentIO {
    private final Map<String, Student> store = new HashMap<>();
    @Override public boolean exists(String id) { return store.containsKey(id); }
    @Override public Student save(Student s) { store.put(s.getId(), s); return s; }
    @Override public Optional<Student> findById(String id) { return Optional.ofNullable(store.get(id)); }
    @Override public List<Student> findAll() { return new ArrayList<>(store.values()); }
    @Override public boolean deleteById(String id) { return store.remove(id) != null; }
    @Override public long count() { return store.size(); }
    @Override public List<Student> search(Predicate<Student> f) { return store.values().stream().filter(f).collect(Collectors.toList()); }
    @Override public List<Student> sorted(Comparator<Student> c) { return store.values().stream().sorted(c).collect(Collectors.toList()); }
}