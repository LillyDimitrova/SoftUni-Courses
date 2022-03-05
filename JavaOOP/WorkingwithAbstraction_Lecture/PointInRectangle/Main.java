package PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int xBottomLeft = input[0];
        int yBottomLeft = input[1];

        Point bottomLeft = new Point(xBottomLeft, yBottomLeft);

        int xTopRight = input[2];
        int yTopRight = input[3];

        Point topRight = new Point(xTopRight, yTopRight);

        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int countCoordinates = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countCoordinates; i++) {
            int[] currentCoordinates = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Point currentPoint = new Point(currentCoordinates[0], currentCoordinates[1]);
            System.out.println(rectangle.contains(currentPoint));;
        }

    }
}
