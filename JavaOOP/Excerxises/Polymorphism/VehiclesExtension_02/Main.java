package Vehicles_01;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static Vehicles_01.Bus.ADDITIONAL_FULL_BUS_CONSUMPTION;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] tokens = scanner.nextLine().split("\\s+");
        Vehicle car = createVehicle(tokens);

        tokens = scanner.nextLine().split("\\s+");
        Vehicle truck = createVehicle(tokens);

        tokens = scanner.nextLine().split("\\s+");
        Vehicle bus = createVehicle(tokens);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        vehicleMap.put("Bus", bus);

        int countCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countCommands; i++) {
            tokens = scanner.nextLine().split("\\s+");
            String commandName = tokens[0];
            String vehicleType = tokens[1];

            switch (commandName) {
                case "Drive":
                    double distance = Double.parseDouble(tokens[2]);
                    Vehicle vehicle = vehicleMap.get(vehicleType);
                    if (vehicle instanceof Bus) {
                        bus.setFuelConsumption(bus.getFuelConsumption() + ADDITIONAL_FULL_BUS_CONSUMPTION);
                        System.out.println(bus.drive(distance));
                        bus.setFuelConsumption(bus.getFuelConsumption() - ADDITIONAL_FULL_BUS_CONSUMPTION);
                        continue;
                    }
                    System.out.println(vehicleMap.get(vehicleType).drive(distance));
                    break;
                case "Refuel":
                    double litres = Double.parseDouble(tokens[2]);
                    vehicleMap.get(vehicleType).refuel(litres);
                    break;
                case "DriveEmpty":
                    double driveEmptyDistance = Double.parseDouble(tokens[2]);
                    System.out.println(bus.drive(driveEmptyDistance));
                    break;
            }
        }
        for (Vehicle v : vehicleMap.values()) {
            System.out.println(String.format("%s: %.2f",v.getClass().getSimpleName(), v.fuelQuantity));
        }
    }
    private static Vehicle createVehicle(String[] tokens) {
        String vehicleType = tokens[0];
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double fuelConsumption = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);

        Vehicle vehicle = null;
        switch (vehicleType){
            case "Car":
                vehicle =  new Car(fuelQuantity, fuelConsumption, tankCapacity);
                break;
            case "Truck":
                vehicle =  new Truck(fuelQuantity, fuelConsumption, tankCapacity);
                break;
            case "Bus":
                vehicle = new Bus(fuelQuantity, fuelConsumption, tankCapacity);
        }
        return vehicle;
    }
}
