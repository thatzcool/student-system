package com.example.student.io;
import com.example.student.model.Student;
import com.example.student.port.StudentIO;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public final class StudentFileIO extends StudentDBIO implements StudentIO, Serializable {
    private static final long serialVersionUID = 1L;
    private static final StudentFileIO INSTANCE = new StudentFileIO(Paths.get("data", "students.dat"));
    public static StudentFileIO getInstance() { return INSTANCE; }
    private Map<String, Student> cache = new HashMap<>();
    private StudentFileIO(Path path) {
        super(path);
        try {
            Map<String, Student> loaded = loadMap();
            if (loaded != null) cache.putAll(loaded);
        } catch (Exception ignore) {}
    }
    @Override protected Map<String, Student> loadMap() throws IOException, ClassNotFoundException {
        Object obj = readObjectFromFile();
        if (obj == null) return new HashMap<>();
        @SuppressWarnings("unchecked")
        Map<String, Student> map = (Map<String, Student>) obj;
        return map;
    }
    @Override protected void saveMap(Map<String, Student> map) throws IOException {
        writeObjectToFile(map);
    }
    @Override public synchronized boolean exists(String id) { return cache.containsKey(id); }
    @Override public synchronized Student save(Student s) throws IOException {
        Objects.requireNonNull(s, "student");
        Objects.requireNonNull(s.getId(), "id");
        cache.put(s.getId(), s);
        saveMap(cache);
        return s;
    }
    @Override public synchronized Optional<Student> findById(String id) { return Optional.ofNullable(cache.get(id)); }
    @Override public synchronized List<Student> findAll() { return new ArrayList<>(cache.values()); }
    @Override public synchronized boolean deleteById(String id) throws IOException {
        Student removed = cache.remove(id);
        if (removed != null) saveMap(cache);
        return removed != null;
    }
    @Override public synchronized long count() { return cache.size(); }
    @Override public synchronized List<Student> search(java.util.function.Predicate<Student> filter) {
        return cache.values().stream().filter(filter).collect(Collectors.toList());
    }
    @Override public synchronized List<Student> sorted(Comparator<Student> comp) {
        return cache.values().stream().sorted(comp).collect(Collectors.toList());
    }
}