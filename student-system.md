classDiagram
direction BT
class InMemoryStudentIO {
  + InMemoryStudentIO() 
  + search(Predicate~Student~) List~Student~
  + findAll() List~Student~
  + findById(String) Optional~Student~
  + count() long
  + exists(String) boolean
  + sorted(Comparator~Student~) List~Student~
  + save(Student) Student
  + deleteById(String) boolean
}
class Main {
  + Main() 
  + main(String[]) void
}
class ObjectIO {
  # ObjectIO(Path) 
  # writeObjectToFile(Object) void
  # readObjectFromFile() Object
}
class SearchStudent {
<<Interface>>
  + byNameContains(String) SearchStudent
  + byGrade(int) SearchStudent
  + byMajor(String) SearchStudent
}
class SortedStudent {
<<Interface>>
  + byGradeThenName() Comparator~Student~
  + byAverageDesc() Comparator~Student~
  + byNameAsc() Comparator~Student~
}
class Student {
  + Student(String, String, int, String, double) 
  + Student() 
  - String major
  - int grade
  - String name
  - LocalDateTime createdAt
  - String id
  - double average
  + toString() String
  + equals(Object) boolean
  + hashCode() int
   String name
   LocalDateTime createdAt
   int grade
   String major
   String id
   double average
}
class StudentDBIO {
  # StudentDBIO(Path) 
  # loadMap() Map~String, Student~
  # saveMap(Map~String, Student~) void
}
class StudentFileIO {
  - StudentFileIO(Path) 
  - StudentFileIO INSTANCE
  + exists(String) boolean
  + save(Student) Student
  + findAll() List~Student~
  + findById(String) Optional~Student~
  + sorted(Comparator~Student~) List~Student~
  # saveMap(Map~String, Student~) void
  + count() long
  + search(Predicate~Student~) List~Student~
  # loadMap() Map~String, Student~
  + deleteById(String) boolean
   StudentFileIO INSTANCE
}
class StudentIO {
<<Interface>>
  + deleteById(String) boolean
  + count() long
  + search(Predicate~Student~) List~Student~
  + sorted(Comparator~Student~) List~Student~
  + exists(String) boolean
  + findById(String) Optional~Student~
  + findAll() List~Student~
  + save(Student) Student
}
class StudentInput {
<<Interface>>
  + parseSingle(Map~String, String~) Student
  + importCsv(Path) List~Student~
}
class StudentManager {
  - StudentManager() 
  - StudentManager INSTANCE
  + register(Student) Student
  + remove(String) boolean
  - validate(Student) void
  + find(SearchStudent) List~Student~
  + get(String) Optional~Student~
  + listAllSorted(Comparator~Student~) List~Student~
   StudentManager INSTANCE
   StudentIO repository
}
class StudentOutput {
<<Interface>>
  + exportCsv(List~Student~, Path) void
  + printList(List~Student~) void
  + printOne(Student) void
}

InMemoryStudentIO "1" *--> "store *" Student 
InMemoryStudentIO  ..>  StudentIO 
StudentDBIO  -->  ObjectIO 
StudentFileIO "1" *--> "cache *" Student 
StudentFileIO  -->  StudentDBIO 
StudentFileIO  ..>  StudentIO 
StudentManager "1" *--> "repo 1" StudentIO 
