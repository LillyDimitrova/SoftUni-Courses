import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Buyer> buyers = new HashMap<>();

        int countOfPeople = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countOfPeople; i++) {

            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 4) {
                addCitizen(tokens, buyers);

            } else {
                addRebel(tokens, buyers);
            }
        }

        String name = scanner.nextLine();
        while (!name.equals("End")) {

            if (buyers.containsKey(name)) {
                Buyer buyer = buyers.get(name);
                buyer.buyFood();
            }

            name = scanner.nextLine();
        }

        int totalFoodPurchased = 0;
        for (Buyer b : buyers.values()) {
            totalFoodPurchased += b.getFood();
        }

        System.out.println(totalFoodPurchased);
    }

    private static void addCitizen(String[] tokens, Map<String, Buyer> buyers) {
        String name = tokens[0];
        int age = Integer.parseInt(tokens[1]);
        String id = tokens[2];
        String birthdate = tokens[3];

        Citizen citizen = new Citizen(name, age, id, birthdate);
        buyers.put(name, citizen);
    }

    private static void addRebel(String[] tokens, Map<String, Buyer> buyers) {
        String name = tokens[0];
        int age = Integer.parseInt(tokens[1]);
        String group = tokens[2];

        Rebel rebel = new Rebel(name, age, group);
        buyers.put(name, rebel);
    }
}
