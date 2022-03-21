package com.softuni.training;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[] numbers = readNumbers(scanner);
        System.out.println(String.join(", ", numbers));

    }

    private static String[] readNumbers(Scanner scanner) {
        String[] numbers = new String[10];
        for (int i = 0; i < numbers.length; i++) {
            try {
                int currentSymbol = Integer.parseInt(scanner.nextLine());
                if (currentSymbol > i + 1 && currentSymbol < 100) {
                    numbers[i] = currentSymbol + "";
                } else {
                    System.out.printf("Your number is not in range %d - 100!%n", i + 1);
                    i--;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Number!");
                i--;
            }

        }
        return numbers;
    }
}
