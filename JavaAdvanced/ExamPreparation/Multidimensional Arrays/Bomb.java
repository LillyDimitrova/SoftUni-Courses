package exam_prep_11_02_2022;

import java.util.Scanner;

public class Bomb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeOfMatrix = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[sizeOfMatrix][sizeOfMatrix];

        String[] commands = scanner.nextLine().split(",");

        fillMatrix(scanner, sizeOfMatrix, matrix);

        int row = 0;
        int col = 0;
        int countOfBombs = getCountBombs(matrix);
        boolean isEndOfTheGame = false;

        for (int r = 0; r < sizeOfMatrix; r++) {
            for (int c = 0; c < sizeOfMatrix; c++) {
                if (matrix[r][c] == 's') {
                    row = r;
                    col = c;
                }
            }
        }
        for (int i = 0; i < commands.length; i++) {
            String movement = commands[i];
            matrix[row][col] = '+';
            switch (movement) {
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
            if (matrix[row][col] == 'e') {
                isEndOfTheGame = true;
                break;
            } else if (matrix[row][col] == 'B') {
                countOfBombs--;
                System.out.println("You found a bomb!");
                if (countOfBombs <= 0) {
                    break;
                }
            }
                matrix[row][col] = 's';
        }
        if (isEndOfTheGame) {
            System.out.printf("END! %d bombs left on the field", countOfBombs);
        } else if (countOfBombs <= 0) {
            System.out.println("Congratulations! You found all bombs!");
        }  else {
            System.out.printf("%s bombs left on the field. Sapper position: (%d,%d)",countOfBombs, row, col);
        }

    }

    private static int getCountBombs(char[][] matrix) {
        int bombs = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c] == 'B') {
                    bombs++;
                }
            }
        }
        return bombs;
    }

    private static int move(int i, int sizeOfMatrix) {
        if (i < 0) {
            i = 0;
        } else if (i >= sizeOfMatrix) {
            i = sizeOfMatrix - 1;
        }
        return i;
    }

    private static void fillMatrix(Scanner scanner, int sizeOfMatrix, char[][] matrix) {
        for (int r = 0; r < sizeOfMatrix; r++) {
            String line = scanner.nextLine().replaceAll("\\s+", "");
            matrix[r] = line.toCharArray();
        }
    }
}
