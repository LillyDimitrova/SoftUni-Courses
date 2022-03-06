package com.softuni.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Identifiable> identifiableList = new ArrayList<>();
        while (!input.equals("End")) {

            String[] tokens = input.split("\\s+");

            Identifiable identifiable;
            String name = tokens[0];

            if (tokens.length == 2) {
                String id = tokens[1];

                identifiable = new Robot(id, name);
            } else {

                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];

                identifiable = new Citizen(name,age, id);
            }

            identifiableList.add(identifiable);

            input = scanner.nextLine();
        }
        String lastDigits = scanner.nextLine();

        List<String> output = new ArrayList<>();
        for (Identifiable el : identifiableList) {
            if (el.getId().endsWith(lastDigits)) {
                output.add(el.getId());
            }
        }

        System.out.println(String.join(System.lineSeparator(), output));
    }
}
