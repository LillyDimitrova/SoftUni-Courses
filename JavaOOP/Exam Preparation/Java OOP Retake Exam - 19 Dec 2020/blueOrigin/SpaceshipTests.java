package blueOrigin;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipTests {
    private Spaceship spaceship;

    @Test(expected = NullPointerException.class)
    public void testConstructorThrowInvalidName() {
        spaceship = new Spaceship(null, 2);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowEmptyName() {
        spaceship = new Spaceship("", 2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowNegativeCapacity() {
        spaceship = new Spaceship("Spaceship", -1);
    }
    @Test
    public void testConstructor() {
        spaceship = new Spaceship("Spaceship1", 2);
        Assert.assertEquals(2,spaceship.getCapacity());
        Assert.assertEquals("Spaceship1", spaceship.getName());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowFullCapacity() {
        spaceship = new Spaceship("Spaceship1", 1);
        Astronaut astronaut = new Astronaut("Lili",2);
        spaceship.add(astronaut);
        Assert.assertEquals(1,spaceship.getCount());
        Astronaut astronaut1 = new Astronaut("Ivan", 3);
        spaceship.add(astronaut1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowExistAstronaut() {
        spaceship = new Spaceship("Spaceship1", 2);
        Astronaut astronaut = new Astronaut("Lili",2);
        spaceship.add(astronaut);
        spaceship.add(astronaut);
    }
    @Test
    public void testAddisCorrect() {
        spaceship = new Spaceship("Spaceship1", 2);
        Astronaut astronaut = new Astronaut("Lili",2);
        Assert.assertEquals(0, spaceship.getCount());
        spaceship.add(astronaut);
        Assert.assertEquals(1, spaceship.getCount());
        Assert.assertEquals(2, astronaut.getOxygenInPercentage(), 0.0);

    }
    @Test
    public void testRemoveIsCorrect() {
        spaceship = new Spaceship("Spaceship1", 2);
        Astronaut astronaut = new Astronaut("Lili",2);
        spaceship.add(astronaut);
        boolean astronaut1 = spaceship.remove("Lili");
        Assert.assertTrue(astronaut1);
    }
}
