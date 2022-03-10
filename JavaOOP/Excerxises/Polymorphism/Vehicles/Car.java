public class Car extends Vehicle{
     private static final double ADDITIONAL_AC_CONSUMPTION = 0.9;


    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
        this.fuelConsumption = this.fuelConsumption + ADDITIONAL_AC_CONSUMPTION;
    }

}
