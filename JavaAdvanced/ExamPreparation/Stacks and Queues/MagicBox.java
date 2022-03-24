package exam_prep_11_02_2022;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MagicBox {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> firstBoxQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondBoxStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(secondBoxStack::push);
        int totalSum = 0;
        while (!firstBoxQueue.isEmpty() && !secondBoxStack.isEmpty()) {
            int sum = firstBoxQueue.peek() + secondBoxStack.peek();
            if (sum % 2 == 0) {
                totalSum += sum;
                firstBoxQueue.poll();
                secondBoxStack.pop();
            } else {
                firstBoxQueue.offer(secondBoxStack.pop());
            }
        }
        if (firstBoxQueue.isEmpty()) {
            System.out.println("First magic box is empty.");
        } else {
            System.out.println("Second magic box is empty.");
        }
        if (totalSum >= 90) {
            System.out.println("Wow, your prey was epic! Value: " + totalSum);
        } else {
            System.out.println("Poor prey... Value: " + totalSum);
        }
    }
}
