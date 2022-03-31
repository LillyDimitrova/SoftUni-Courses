package catHouse.repositories;

import catHouse.entities.toys.Toy;


import java.util.ArrayList;

import java.util.List;


public class ToyRepository implements Repository{
    private List<Toy> toys;

    public ToyRepository() {
        this.toys = new ArrayList<>();
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public boolean removeToy(Toy toy) {
        return toys.remove(toy);
    }

    @Override
    public Toy findFirst(String type) {
        Toy toy = null;
        for (Toy t : toys) {
            if (t.getClass().getSimpleName().equals(type)) {
                toy = t;
            }
        }
        return toy;
    }
}
