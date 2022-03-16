package WildFarm_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String line = scanner.nextLine();
        while (!line.equals("End")) {

            String[] animalInfo = line.split("\\s+");
            String animalType = animalInfo[0];
            String animalName = animalInfo[1];
            Double animalWeight = Double.parseDouble(animalInfo[2]);
            String livingRegion = animalInfo[3];

            Animal animal = null;

            switch (animalType) {
                case "Mouse":
                    animal = new Mouse(animalType, animalName, animalWeight, livingRegion);
                    break;
                case "Zebra":
                    animal = new Zebra(animalType, animalName, animalWeight, livingRegion);
                    break;
                case "Cat":
                    String breed = animalInfo[4];
                    animal = new Cat(animalType, animalName, animalWeight, livingRegion, breed);
                    break;
                case "Tiger":
                    animal = new Tiger(animalType, animalName, animalWeight, livingRegion);
                    break;
            }
            animal.makeSound();

            String[] foodTokens = scanner.nextLine().split("\\s+");
            Food food = null;
            Integer quantity = Integer.parseInt(foodTokens[1]);

            if (foodTokens[0].equals("Vegetable")) {
                food = new Vegetable(quantity);
            } else {
                food = new Meat(quantity);
            }

            try {
                animal.eat(food);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            animals.add(animal);

            line = scanner.nextLine();
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
