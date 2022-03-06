package Encapsulation_Lecture;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Person(String firstName, String lastName, int age,double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }
    public void increaseSalary(double percentAge) {
        if (this.getAge() < 30) {
            this.setSalary(this.getSalary() + (this.getSalary() * percentAge / 200));
        }else {
            this.setSalary(this.getSalary() + (this.getSalary() * percentAge / 100));
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %f leva",firstName, lastName, salary);
    }
}
