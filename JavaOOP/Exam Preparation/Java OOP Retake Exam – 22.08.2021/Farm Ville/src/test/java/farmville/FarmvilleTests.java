package farmville;

import org.junit.Assert;
import org.junit.Test;

public class FarmvilleTests {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowNegativeCapacity() {
        Farm farm = new Farm("Farm", -2);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowNameIsNull() {
        Farm farm = new Farm(null, 5);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowNameIsEmpty() {
        Farm farm = new Farm("", 5);
    }
    @Test
    public void testConstructorFarm() {
        Farm farm = new Farm("Farm", 5);
        Assert.assertEquals("Farm", farm.getName());
        Assert.assertEquals(5, farm.getCapacity());
        Assert.assertEquals(0,farm.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowFullCapacity() {
        Farm farm = new Farm("Farm", 1);
        Animal animal = new Animal("Cat", 3.3);
        farm.add(animal);
        Assert.assertEquals(1,farm.getCount());
        Animal animal1 = new Animal("Dog", 3.2);
        farm.add(animal1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowAnimalTypeExists() {
        Farm farm = new Farm("Farm", 3);
        Animal animal = new Animal("Cat", 3.3);
        Animal animal1 = new Animal("Cat", 3.3);
        farm.add(animal);
        farm.add(animal1);
    }
    @Test
    public void testAddAnimal() {
        Farm farm = new Farm("Farm", 3);
        Animal animal = new Animal("Cat", 3.3);
        Animal animal1 = new Animal("Dog", 3.2);
        farm.add(animal);
        farm.add(animal1);
        Assert.assertEquals(2,farm.getCount());
        Assert.assertEquals(3.2, animal1.getEnergy(), 0.0);
    }
    @Test
    public void testRemoveType() {
        Farm farm = new Farm("Farm", 3);
        Animal animal = new Animal("Cat", 3.3);
        Animal animal1 = new Animal("Dog", 3.2);
        farm.add(animal);
        farm.add(animal1);
        Assert.assertEquals(2,farm.getCount());
        boolean isRemove = farm.remove("Cat");
        Assert.assertTrue(isRemove);
        Assert.assertEquals(1,farm.getCount());
    }
}
