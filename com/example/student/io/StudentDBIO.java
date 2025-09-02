package com.example.student.io;
import com.example.student.model.Student;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
public abstract class StudentDBIO extends ObjectIO {
    protected StudentDBIO(Path path) { super(path); }
    protected abstract Map<String, Student> loadMap() throws IOException, ClassNotFoundException;
    protected abstract void saveMap(Map<String, Student> map) throws IOException;
}