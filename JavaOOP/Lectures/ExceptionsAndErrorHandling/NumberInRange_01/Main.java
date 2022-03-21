package com.softuni.training;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] range = scanner.nextLine().split("\\s+");
        int startOfRange = Integer.parseInt(range[0]);
        int endOfRange = Integer.parseInt(range[1]);

        System.out.printf("Range: [%d...%d]\n",startOfRange, endOfRange);
        int validNumber = readValidNumber(scanner, startOfRange, endOfRange);
        System.out.printf("Valid number: %d\n", validNumber);
    }

    private static int readValidNumber(Scanner scanner, int startOfRange, int endOfRange) {
        for (;;) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= startOfRange && number <= endOfRange) {
                    return number;
                }
            } catch (NumberFormatException e) {
            }
            System.out.printf("Invalid number: %s\n", input);
        }
    }
}
