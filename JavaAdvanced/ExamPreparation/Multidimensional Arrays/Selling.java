package exam_prep_11_02_2022;

import java.util.Scanner;

public class Selling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeOfMatrix = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[sizeOfMatrix][sizeOfMatrix];

        fillMatrix(scanner, sizeOfMatrix, matrix);

        int row = 0;
        int col = 0;
        for (int r = 0; r < sizeOfMatrix; r++) {
            for (int c = 0; c < sizeOfMatrix; c++) {
                if (matrix[r][c] == 'S') {
                    row = r;
                    col = c;
                }
            }
        }
        int money = 0;
        boolean isOutOfMatrix = false;
        while (money < 50) {
            String movement = scanner.nextLine();
            matrix[row][col] = '-';

            switch (movement) {
                case "up":
                    row--;
                    break;
                case "down":
                    row++;
                    break;
                case "left":
                    col--;
                    break;
                case "right":
                    col++;
                    break;
            }
            if (row < 0 || row >= matrix.length || col < 0 || col >= matrix.length) {
                isOutOfMatrix = true;
                break;
            } else if (matrix[row][col] == 'O') {
                matrix[row][col] = '-';
                for (int r = 0; r < matrix.length; r++) {
                    for (int c = 0; c < matrix.length; c++) {
                        if (matrix[r][c] == 'O') {
                            row = r;
                            col = c;
                        }
                    }
                }
            } else if (Character.isDigit(matrix[row][col])) {
                int currentMoney = Character.getNumericValue(matrix[row][col]);
                money += currentMoney;
            }
            matrix[row][col] = 'S';
        }
        if (money >= 50) {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }else if (isOutOfMatrix) {
            System.out.println("Bad news, you are out of the bakery.");
        }
        System.out.println("Money: " + money);
        printMatrix(matrix);

    }

    private static void printMatrix(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                System.out.print(matrix[r][c]);
            }
            System.out.println();
        }
    }

    private static void fillMatrix(Scanner scanner, int sizeOfMatrix, char[][] matrix) {
        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
        }
    }
}
