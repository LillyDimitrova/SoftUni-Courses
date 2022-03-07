package com.softuni.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Birthable> birthables = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] tokens = input.split("\\s+");

            String objectType = tokens[0];
            switch (objectType) {
                case "com.softuni.training.Citizen":
                    String name = tokens[1];
                    int age = Integer.parseInt(tokens[2]);
                    String id = tokens[3];
                    String birthdate = tokens[4];
                    Citizen citizen = new Citizen(name, age, id, birthdate);
                    birthables.add(citizen);
                    break;
                case "com.softuni.training.Pet":
                    String namePet = tokens[1];
                    String birthdatePet = tokens[2];
                    Pet pet = new Pet(namePet, birthdatePet);
                    birthables.add(pet);
                    break;
                case "com.softuni.training.Robot":
                    String model = tokens[1];
                    String idRobot = tokens[2];
                    Robot robot = new Robot(idRobot, model);
                    break;
            }


            input = scanner.nextLine();
        }
        String year = scanner.nextLine();
        for (Birthable birthable : birthables) {
            if (birthable.getBirthDate().endsWith(year)) {
                System.out.println(birthable.getBirthDate());
            }
        }
    }
}
