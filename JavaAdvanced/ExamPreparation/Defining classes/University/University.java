import java.util.ArrayList;
import java.util.List;

public class University {
    public int capacity;
    public List<Student> students;

    public University(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getStudentCount() {
        return students.size();
    }

    public String registerStudent(Student student) {
        String output = "";
        if (students.contains(student)) {
            output = "Student is already in the university";
        } else if (capacity > students.size()) {
            students.add(student);
            output = String.format("Added student %s %s", student.firstName, student.lastName);
        } else {
            output = "No seats in the university";
        }
        return output;
    }

    public String dismissStudent(Student student) {
        String output = "";
        if (students.contains(student)) {
            students.remove(student);
            output = String.format("Removed student %s %s", student.getFirstName(), student.getLastName());
        } else {
            output = "Student not found";
        }
        return output;
    }

    public Student getStudent(String firstName, String lastName) {
        Student st = null;
        for (Student student : students) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                st = student;
            }
        }
        return st;
    }

    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        for (Student student : students) {
            output.append(String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s", student.firstName, student.lastName, student.bestSubject));
            output.append("\n");
        }
        return output.toString().trim();
    }
}
