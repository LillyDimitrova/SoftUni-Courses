package Animals_06;

public abstract class Animal {
    private final String name;
    private final int age;
    private final String gender;

    protected Animal(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public void checkFields() {
        if (name.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid input!");
        } else if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }else if (gender.trim().isEmpty()) {
            throw  new IllegalArgumentException("Invalid input!");
        }
    }
    protected abstract String produceSound();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append("\n");
        sb.append(name);
        sb.append(" ");
        sb.append(age);
        sb.append(" ");
        sb.append(gender);
        sb.append("\n");

        sb.append(this.produceSound());

        return sb.toString().trim();
    }
}
