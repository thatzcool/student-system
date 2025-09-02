package com.example.student.io;
import java.io.*;
import java.nio.file.*;
public abstract class ObjectIO {
    protected final Path path;
    protected ObjectIO(Path path) { this.path = path; }
    protected final synchronized void writeObjectToFile(Object data) throws IOException {
        Files.createDirectories(path.getParent() == null ? Path.of(".") : path.getParent());
        Path tmp = path.resolveSibling(path.getFileName() + ".tmp");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tmp))) {
            oos.writeObject(data);
        }
        Files.move(tmp, path, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }
    protected final synchronized Object readObjectFromFile() throws IOException, ClassNotFoundException {
        if (!Files.exists(path)) return null;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            return ois.readObject();
        }
    }
}