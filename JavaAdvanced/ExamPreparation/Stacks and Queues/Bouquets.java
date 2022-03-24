package exam_prep_11_02_2022;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bouquets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> tulipsStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(tulipsStack::push);

        ArrayDeque<Integer> daffodilsQueue = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int store = 0;
        int countBouquets = 0;


        while (!tulipsStack.isEmpty() && !daffodilsQueue.isEmpty()) {
            int sum = tulipsStack.peek() + daffodilsQueue.peek();

            if (sum == 15) {
                countBouquets++;

            } else if (sum > 15) {
                while (sum >= 15) {
                    tulipsStack.push(tulipsStack.pop() - 2);
                    sum = tulipsStack.peek() + daffodilsQueue.peek();
                }
                if (sum == 15) {
                    countBouquets++;

                } else {
                    store += sum;
                }
            } else {
                store += sum;
            }
            tulipsStack.pop();
            daffodilsQueue.poll();
        }
        countBouquets += store / 15;
        if (countBouquets >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!", countBouquets);
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - countBouquets);
        }
    }
}
