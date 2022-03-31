package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseHouse implements House{
    private String name;
    private int capacity;
    private List<Toy> toys;
    private List<Cat> cats;

    protected BaseHouse(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public int sumSoftness() {
       return toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (capacity < cats.size()) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public void feeding() {
        cats.forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append(" ").append(this.getClass().getSimpleName()).append(":").append("\n");
        if (cats.isEmpty()){
            builder.append("Cats: none").append("\n");
        } else {
            builder.append("Cats: ");
            for (Cat c : cats) {
                builder.append(c.getName()).append(" ");
            }
            builder.append("\n");
        }
        builder.append("Toys: ").append(toys.size()).append(" Softness: ").append(sumSoftness()).append("\n");

        return builder.toString().trim();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
                throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public List<Cat> getCats() {
        return this.cats;
    }

    @Override
    public List<Toy> getToys() {
        return this.toys;
    }
}
