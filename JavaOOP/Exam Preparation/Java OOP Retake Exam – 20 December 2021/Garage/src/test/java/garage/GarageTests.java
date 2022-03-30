package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GarageTests {
    private Car car;
    private Garage garage;

    @Before
    public void setUp() {
        this.car = new Car("Seat",100, 2.500);
        this.garage = new Garage();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowWhenCarIsNull() {
        garage.addCar(null);
    }
    @Test
    public void testAdd() {
        Assert.assertEquals(0,garage.getCount());
        garage.addCar(this.car);
        Assert.assertEquals(1,garage.getCount());
        Car car = garage.getCars().get(0);
        Assert.assertEquals("Seat",car.getBrand());
        Assert.assertEquals(100,car.getMaxSpeed());

    }
    @Test
    public void findAllCarsByBrand() {

        Car car1 = new Car("BMW", 150, 30.00);
        Car car2 = new Car("Lada", 130, 30.00);
        Car car3 = new Car("Reno", 180, 30.00);
        Car car4 = new Car("Lada", 90, 40.00);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        garage.addCar(car4);

        List<Car> allCarsByBrand = garage.findAllCarsByBrand("Lada");

        Assert.assertEquals(2,allCarsByBrand.size());
        Car currentCar = allCarsByBrand.get(0);
        Assert.assertEquals("Lada",currentCar.getBrand());
        Car currentCar2 = allCarsByBrand.get(1);
        Assert.assertEquals("Lada",currentCar.getBrand());
        Assert.assertSame(car2,currentCar);
        Assert.assertSame(car4,currentCar2);

    }
    @Test
    public void testTheMostExpensiveCar() {

        garage.addCar(new Car("BMW",170,23.500));
        garage.addCar(new Car("Lada",180,24.500));
        garage.addCar(new Car("Reno",160,225.500));

        Car car = garage.getTheMostExpensiveCar();
        Assert.assertEquals("Reno",car.getBrand());
        Assert.assertEquals(160,car.getMaxSpeed());
    }
    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        garage.addCar(new Car("BMW",200,23.500));
        garage.addCar(new Car("Lada",180,24.500));
        garage.addCar(new Car("Reno",140,225.500));
        garage.addCar(new Car("Passat",130,225.500));

        List<Car> cars = garage.findAllCarsWithMaxSpeedAbove(150);
        Assert.assertEquals(2, cars.size());
        Car car = cars.get(0);
        Car car2 = cars.get(1);
        Assert.assertEquals(car.getBrand(),"BMW");
        Assert.assertEquals(car2.getBrand(),"Lada");

    }
}