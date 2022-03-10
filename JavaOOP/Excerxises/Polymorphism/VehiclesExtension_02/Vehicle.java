package Vehicles_01;

import java.text.DecimalFormat;

public  class Vehicle {
    protected double fuelQuantity;
    protected double fuelConsumption;
    protected double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public String drive(double distance) {

        double fuelNeeded = distance * fuelConsumption;

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        if (fuelNeeded < fuelQuantity) {

            fuelQuantity -= fuelNeeded;
            return String.format("%s travelled %s km", getClass().getSimpleName(), decimalFormat.format(distance));
        } else {
            return String.format("%s needs refueling", getClass().getSimpleName());
        }
    }

    public void refuel(double liters) {
        if (liters <= 0) {
            System.out.println("Fuel must be a positive number");
            return;
        }

        if (liters > tankCapacity - fuelQuantity) {
            System.out.println("Cannot fit fuel in tank");
            return;
        }
        fuelQuantity += liters;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
