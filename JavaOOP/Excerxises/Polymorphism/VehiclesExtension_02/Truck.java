package Vehicles_01;

import Vehicles_01.Vehicle;

public class Truck extends Vehicle {
    private static final double ADDITIONAL_AC_CONSUMPTION = 1.6;
    private static final double REFUEL_PERCENTAGE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.fuelConsumption = this.fuelConsumption + ADDITIONAL_AC_CONSUMPTION;
    }

    @Override
    public void refuel(double liters) {
        liters = liters * REFUEL_PERCENTAGE;
        super.refuel(liters);
    }
}
