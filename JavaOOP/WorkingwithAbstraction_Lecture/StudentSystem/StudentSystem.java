import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repository;

    public StudentSystem() {
        this.repository = new HashMap<>();
    }

    public void parseCommand(String[] commandParts) {

        String command = commandParts[0];
        String name = commandParts[1];

        if (command.equals("Create")) {

            createStudent(commandParts, name);

        } else if (command.equals("Show")) {

            System.out.println(toString(name));
        }
    }

    private String toString(String name) {
        StringBuilder output = new StringBuilder();

        if (repository.containsKey(name)) {
            Student student = repository.get(name);
            output.append(String.format("%s is %s years old.", student.getName(), student.getAge()));

            if (student.getGrade() >= 5.00) {
                output.append(" Excellent student.");
            } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
                output.append(" Average student.");
            } else {
                output.append(" Very nice person.");
            }
        }
        return output.toString();
    }

    private void createStudent(String[] commandParts, String name) {
        int age = Integer.parseInt(commandParts[2]);
        double grade = Double.parseDouble(commandParts[3]);

        Student student = new Student(name, age, grade);
        repository.putIfAbsent(name, student);
    }
}
