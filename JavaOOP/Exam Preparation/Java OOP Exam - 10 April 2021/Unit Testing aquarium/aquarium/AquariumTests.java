package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowNameIsNull() {
        Aquarium aquarium = new Aquarium(null, 5);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowNameIsEmpty() {
        Aquarium aquarium = new Aquarium("", 5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowNegativeCapacity() {
        Aquarium aquarium = new Aquarium("Aquarium1", -2);

    }
    @Test
    public void testConstructorAquarium() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        Assert.assertEquals("Aquarium1", aquarium.getName());
        Assert.assertEquals(2, aquarium.getCapacity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowFullCapacity() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        Fish fish1 = new Fish("Nemo");
        Fish fish2 = new Fish("Dori");
        Fish fish3 = new Fish("Lili");
        aquarium.add(fish1);
        aquarium.add(fish2);
        aquarium.add(fish3);
    }
    @Test
    public void testAddFish() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        Assert.assertEquals(0,aquarium.getCount());
        Fish fish1 = new Fish("Nemo");
        aquarium.add(fish1);
        Assert.assertEquals(1,aquarium.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrowInvalidName() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        aquarium.remove("Nemo");
    }
    @Test
    public void testRemoveByName() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        Fish fish1 = new Fish("Nemo");
        Fish fish2 = new Fish("Dori");
        aquarium.add(fish1);
        aquarium.add(fish2);
        Assert.assertEquals(2,aquarium.getCount());
        aquarium.remove("Dori");
        Assert.assertEquals(1,aquarium.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSellFishThrow() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        aquarium.sellFish("Nemo");
    }
    @Test
    public void testSellFish() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        Fish fish1 = new Fish("Nemo");
        Fish fish2 = new Fish("Dori");
        aquarium.add(fish1);
        aquarium.add(fish2);
        Fish fishForSale = aquarium.sellFish("Nemo");
        Assert.assertEquals("Nemo",fishForSale.getName());
        Assert.assertFalse(fishForSale.isAvailable());
    }
    @Test
    public void testReport() {
        Aquarium aquarium = new Aquarium("Aquarium1", 2);
        Fish fish1 = new Fish("Nemo");
        Fish fish2 = new Fish("Dori");
        aquarium.add(fish1);
        aquarium.add(fish2);
        Assert.assertEquals("Fish available at Aquarium1: Nemo, Dori",aquarium.report());
    }
}

