package GreedyTimes_06;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    long input = Long.parseLong(scanner.nextLine());
    String[] safe = scanner.nextLine().split("\\s+");

    Map<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();
    long gold = 0;
    long stones = 0;
    long money = 0;

        for(int i = 0; i < safe.length; i += 2) {

        String name = safe[i];
        long count = Long.parseLong(safe[i + 1]);

        String command = "";

        if (name.length() == 3) {
            command = "Cash";
        } else if (name.toLowerCase().endsWith("gem")) {
            command = "Gem";
        } else if (name.toLowerCase().equals("gold")) {
            command = "Gold";
        }

        if (command.equals("")) {
            continue;
        } else if (input < bag.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + count) {
            continue;
        }

        switch (command) {
            case "Gem":
                if (!bag.containsKey(command)) {
                    if (bag.containsKey("Gold")) {
                        if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else if (bag.get(command).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                    continue;
                }
                break;
            case "Cash":
                if (!bag.containsKey(command)) {
                    if (bag.containsKey("Gem")) {
                        if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else if (bag.get(command).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                    continue;
                }
                break;
        }

        if (!bag.containsKey(command)) {
            bag.put((command), new LinkedHashMap<String, Long>());
        }

        if (!bag.get(command).containsKey(name)) {
            bag.get(command).put(name, 0L);
        }


        bag.get(command).put(name, bag.get(command).get(name) + count);
        if (command.equals("Gold")) {
            gold += count;
        } else if (command.equals("Gem")) {
            stones += count;
        } else if (command.equals("Cash")) {
            money += count;
        }
    }

        for (var x : bag.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
        }
    }
}
