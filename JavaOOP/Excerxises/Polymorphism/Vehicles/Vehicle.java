import java.text.DecimalFormat;

public  class Vehicle {
    protected double fuelQuantity;
    protected double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
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
