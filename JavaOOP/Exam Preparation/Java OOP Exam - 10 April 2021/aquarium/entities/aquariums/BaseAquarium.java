package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BaseAquarium implements Aquarium{
    private String name;
    private int capacity;
    private List<Decoration> decorations;
    private List<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        decorations = new ArrayList<>();
        fish = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (capacity > this.fish.size()) {
            this.fish.add(fish);
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removeFish(Fish fish) {
            this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish f : fish) {
            f.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s (%s):",this.name, getClass().getSimpleName())).append("\n");
        builder.append("Fish: ");
        if (fish.isEmpty()) {
            builder.append("none").append("\n");
        } else {
            List<String> fishNames = new ArrayList<>();
            for (Fish f : fish) {
                fishNames.add(f.getName());
            }
            builder.append(String.join(" ",fishNames)).append("\n");
        }

        builder.append("Decorations: ").append(decorations.size()).append("\n");
        builder.append(String.format("Comfort: %d",calculateComfort())).append(System.lineSeparator());
        return builder.toString();
    }

    @Override
    public List<Fish> getFish() {
        return this.fish;
    }

    @Override
    public List<Decoration> getDecorations() {
        return this.decorations;
    }
}
