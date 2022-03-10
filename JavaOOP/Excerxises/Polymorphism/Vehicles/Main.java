import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] tokens = scanner.nextLine().split("\\s+");
        double carFuelQuantity = Double.parseDouble(tokens[1]);
        double carFuelConsumption = Double.parseDouble(tokens[2]);

        Car car = new Car(carFuelQuantity, carFuelConsumption);

        tokens = scanner.nextLine().split("\\s+");
        double truckFuelQuantity = Double.parseDouble(tokens[1]);
        double truckFuelConsumption = Double.parseDouble(tokens[2]);

        Truck truck = new Truck(truckFuelQuantity, truckFuelConsumption);
        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);

        int countCommands = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countCommands; i++) {
            tokens = scanner.nextLine().split("\\s+");
            String commandName = tokens[0];
            String vehicleType = tokens[1];

            switch (commandName) {
                case "Drive":
                    double distance = Double.parseDouble(tokens[2]);
                    System.out.println(vehicleMap.get(vehicleType).drive(distance));
                    break;
                case "Refuel":
                    double litres = Double.parseDouble(tokens[2]);
                    vehicleMap.get(vehicleType).refuel(litres);
                    break;
            }
        }
        for (Vehicle v : vehicleMap.values()) {
            System.out.println(String.format("%s: %.2f",v.getClass().getSimpleName(), v.fuelQuantity));
        }
    }
}
