package com.example.student.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int grade;
    private String major;
    private double average;
    private LocalDateTime createdAt = LocalDateTime.now();
    public Student() {}
    public Student(String id, String name, int grade, String major, double average) {
        this.id = id; this.name = name; this.grade = grade; this.major = major; this.average = average;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    @Override public boolean equals(Object o) {
        if (this == o) return true; if (!(o instanceof Student)) return false;
        Student s = (Student) o; return Objects.equals(id, s.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
    @Override public String toString() {
        return "Student{"+"id='"+id+'\''+", name='"+name+'\''+", grade="+grade+", major='"+major+'\''+", average="+average+'}';
    }
}