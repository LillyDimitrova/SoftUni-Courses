package exam_prep_11_02_2022;

import java.util.Scanner;

public class Python {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int sizeOfMatrix = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[sizeOfMatrix][sizeOfMatrix];
        String[] commands = scanner.nextLine().split(", ");
        int pythonLength = 1;

        fillMatrix(scanner, sizeOfMatrix, matrix);
        int row = 0;
        int col = 0;
        for (int r = 0; r < sizeOfMatrix; r++) {
            for (int c = 0; c < sizeOfMatrix; c++) {
                if (matrix[r][c] == 's') {
                    row = r;
                    col = c;
                }
            }
        }
        int foodToBeEaten = getFood(matrix);
        boolean hasKilledByEnemy = false;

        for (int i = 0; i < commands.length; i++) {
            matrix[row][col] = '*';
            String directions = commands[i];
            switch (directions) {
                case "up":
                    row = move(row - 1, sizeOfMatrix);
                    break;
                case "down":
                    row = move(row + 1, sizeOfMatrix);
                    break;
                case "left":
                    col = move(col - 1, sizeOfMatrix);
                    break;
                case "right":
                    col = move(col + 1, sizeOfMatrix);
                    break;
            }
            if (matrix[row][col] == 'f') {
                foodToBeEaten--;
                pythonLength++;
            } else if (matrix[row][col] == 'e') {
                hasKilledByEnemy = true;
                break;
            } else if (foodToBeEaten == 0) {
                break;
            }
            matrix[row][col] = '*';
        }
        if (foodToBeEaten == 0) {
            System.out.printf("You win! Final python length is %d%n", pythonLength);
        } else if (hasKilledByEnemy) {
            System.out.println("You lose! Killed by an enemy!");
        } else {
            System.out.printf("You lose! There is still %d food to be eaten.", foodToBeEaten);
        }
    }

    private static int getFood(char[][] matrix) {
        int food = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c] == 'f') {
                    food++;
                }
            }
        }
        return food;
    }

    private static int move(int i, int sizeOfMatrix) {
        if (i < 0) {
            i = sizeOfMatrix - 1;
        } else if (i >= sizeOfMatrix) {
            i = 0;
        }
        return i;
    }

    private static void fillMatrix(Scanner scanner, int sizeOfMatrix, char[][] matrix) {
        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = scanner.nextLine().replaceAll("\\s+","");
            matrix[i] = line.toCharArray();
        }
    }
}
