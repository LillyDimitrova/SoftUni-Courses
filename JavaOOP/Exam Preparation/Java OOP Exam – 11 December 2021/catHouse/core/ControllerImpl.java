package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.*;

public class ControllerImpl implements Controller{
    private ToyRepository toys;
    private Map<String, House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house = null;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        houses.put(name, house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy = null;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        House house = houses.get(houseName);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        toys.removeToy(toy);
        house.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat = null;
        House house = houses.get(houseName);
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName ,catBreed ,price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        if (cat.getClass().getSimpleName().equals("ShorthairCat") && !house.getClass().getSimpleName().equals("ShortHouse")
        || cat.getClass().getSimpleName().equals("LonghairCat") && !house.getClass().getSimpleName().equals("LongHouse")){
            return ConstantMessages.UNSUITABLE_HOUSE;
        } else  {
            house.addCat(cat);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }
    }

    @Override
    public String feedingCat(String houseName) {
        House house = houses.get(houseName);
        Collection<Cat> catsInTheHouse = house.getCats();
        int count = 0;
        for (Cat c : catsInTheHouse) {
            c.eating();
            count++;
        }

        return String.format(ConstantMessages.FEEDING_CAT, count);
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.get(houseName);
        double toySum = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double catSum = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double totalSum = toySum + catSum;
        return String.format(ConstantMessages.VALUE_HOUSE,houseName, totalSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder =  new StringBuilder();
        for (House h : houses.values()) {
            builder.append(h.getStatistics()).append("\n");
        }
        return builder.toString().trim();
    }
}
