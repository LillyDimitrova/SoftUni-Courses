package groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private List<Pet> data;
    private int capacity;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (capacity > data.size()) {
            data.add(pet);
        }
    }
    public boolean remove(String name) {
        Pet pet = null;
        for (Pet p : data) {
            if (p.getName().equals(name)) {
                pet = p;
                data.remove(pet);
                return true;
            }
        }
        return false;
    }
    public Pet getPet(String name, String owner) {
        Pet pet = null;
        for (Pet p : data) {
            if (p.getName().equals(name) && p.getOwner().equals(owner)) {
                pet = p;
            }
        }
        return pet;
    }
    public int getCount() {
        return data.size();
    }
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The grooming salon has the following clients:").append("\n");
        for (Pet pet : data) {
            stringBuilder.append(pet.getName()).append(" ").append(pet.getOwner()).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
