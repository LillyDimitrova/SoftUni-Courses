package exam_prep_11_02_2022;

import java.util.*;
import java.util.stream.Collectors;

public class OSPlaning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> taskStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(taskStack::push);

        ArrayDeque<Integer> threadQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int taskToBeKilled = Integer.parseInt(scanner.nextLine());
        boolean isТхеTaskKilled = false;
        while (!isТхеTaskKilled) {
            if (taskStack.peek() == taskToBeKilled) {
                isТхеTaskKilled = true;
                        break;
            } else if (threadQueue.isEmpty()) {
                break;
            } else if (threadQueue.peek() >= taskStack.peek()) {
                threadQueue.poll();
                taskStack.pop();
            } else {
                threadQueue.poll();
            }
        }
        System.out.printf("Thread with value %d killed task %d%n",threadQueue.peek(), taskToBeKilled);
        if (!threadQueue.isEmpty()) {
            List<String> output = new ArrayList<>();
            int n = threadQueue.size();
            for (int i = 0; i < n; i++) {
                    output.add(threadQueue.poll() + "");
            }
            System.out.print(String.join( " ", output));
        }
    }
}
