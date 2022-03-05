package PizzaCalories_04;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        setName(name);
        setToppings(numberOfToppings);
    }

    public void setName(String name) {
        if (name.trim().isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }


    public void setToppings(int numberOfToppings) {
        if (numberOfToppings >= 1 && numberOfToppings <= 10) {
            this.toppings = new ArrayList<>(numberOfToppings);
        } else {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }
    public void addTopping (Topping topping) {
        toppings.add(topping);
    }
    public double getOverallCalories () {
        return dough.calculateCalories() + toppings.stream().mapToDouble(t -> t.calculateCalories()).sum();
    }
}
