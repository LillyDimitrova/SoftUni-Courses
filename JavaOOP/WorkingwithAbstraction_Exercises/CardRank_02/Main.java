package CardRank_02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        CardRank[] cardRanks = CardRank.values();

        System.out.println(input + ":");
        for (CardRank cards : cardRanks) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", cards.ordinal(), cards.name());
        }
    }
}
