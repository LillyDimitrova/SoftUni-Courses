package Vehicles_01;

public class Bus extends Vehicle{
    public final static double ADDITIONAL_FULL_BUS_CONSUMPTION = 1.4;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }
}
