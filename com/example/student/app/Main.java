package com.example.student.app;
import com.example.student.io.InMemoryStudentIO;
// import com.example.student.io.StudentFileIO;
import com.example.student.model.Student;
import com.example.student.port.SearchStudent;
import com.example.student.port.SortedStudent;
import com.example.student.service.StudentManager;
import java.util.List;
public class Main {
    public static void main(String[] args) throws Exception {
        var manager = StudentManager.getInstance();
        manager.setRepository(new InMemoryStudentIO());
        // manager.setRepository(StudentFileIO.getInstance());
        manager.register(new Student("2025001", "김지우", 1, "CS", 4.3));
        manager.register(new Student("2025002", "박서현", 2, "Math", 3.8));
        manager.register(new Student("2025003", "이민준", 3, "CS", 3.2));

        List<Student> cs = manager.find(SearchStudent.byMajor("CS"));
        System.out.print("전공: \n");
        for (Student s : cs) {
            System.out.printf("id= %s name= %s grade = %d major= %s average= %.1f\n" ,  s.getId(), s.getName(), s.getGrade(), s.getMajor(), s.getAverage());
        }
        System.out.println();

        List<Student> sorted = manager.listAllSorted(SortedStudent.byAverageDesc());
        System.out.println("평점 내림차순: " );
        sorted.stream().forEach(System.out::println);
    }
}