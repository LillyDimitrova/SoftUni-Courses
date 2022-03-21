package com.softuni.training;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            int number = Integer.parseInt(scanner.nextLine());
            if (number <= 0) {
                throw new NumberFormatException();
            }
            System.out.printf("%.2f\n", Math.sqrt(number));

        } catch (NumberFormatException ex) {
            System.out.println("Invalid");

        } finally {
            System.out.println("Goodbye");
        }
    }
}
