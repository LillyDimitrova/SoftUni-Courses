package cats;

import org.junit.Assert;
import org.junit.Test;

public class HouseTests {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowNegativeCapacity() {
        House house = new House("House1", -2);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowNameIsNull() {
        House house = new House(null, 2);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowNameIsEmpty() {
        House house = new House("", 2);
    }
    @Test
    public void testConstructorHouse() {
        House house = new House("House1", 3);
        Assert.assertEquals("House1", house.getName());
        Assert.assertEquals(3, house.getCapacity());
        Assert.assertEquals(0,house.getCount());
    }
    @Test
    public void testAddCat() {
        House house = new House("House1", 1);
        Cat moni = new Cat("Moni");
        house.addCat(moni);
        Assert.assertEquals(1,house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowFullHouse() {
        House house = new House("House1", 1);
        Cat moni = new Cat("Moni");
        house.addCat(moni);
        Cat misho = new Cat("Misho");
        house.addCat(misho);
    }
    @Test
    public void testRemoveCat() {
        House house = new House("House1", 1);
        Cat moni = new Cat("Moni");
        house.addCat(moni);
        Assert.assertEquals(1,house.getCount());
        house.removeCat("Moni");
        Assert.assertEquals(0,house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrowInvalidName() {
        House house = new House("House1", 1);
        house.removeCat("Moni");
    }
    @Test
    public void testCatForSale() {
        House house = new House("House1", 1);
        Cat moni = new Cat("Moni");
        house.addCat(moni);
        Cat catForSale = house.catForSale("Moni");
        Assert.assertEquals("Moni", catForSale.getName());
        Assert.assertFalse(catForSale.isHungry());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleThrowInvalidName() {
        House house = new House("House1", 1);
        Cat moni = new Cat("Moni");
        house.addCat(moni);
        Cat catForSale = house.catForSale("Pesho");
    }
    @Test
    public void testStatistics() {
        House house = new House("House1", 5);
        Cat moni = new Cat("Moni");
        Cat misho = new Cat("Misho");
        house.addCat(moni);
        house.addCat(misho);
        Assert.assertEquals("The cat Moni, Misho is in the house House1!",house.statistics());
    }
}
