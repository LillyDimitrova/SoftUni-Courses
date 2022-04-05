package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {
    private ComputerManager computerManager;

    @Before
    public void setUp() {
        computerManager = new ComputerManager();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowInvalidComputer() {
        computerManager.addComputer(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowExistComputer() {
        Computer computer = new Computer("REX", "Asus", 2500);
        computerManager.addComputer(computer);
        Assert.assertEquals(1, computerManager.getCount());
        computerManager.addComputer(computer);
    }
    @Test
    public void testGetComputers() {
        Computer computer = new Computer("REX", "Asus", 2500);
        Computer computer1 = new Computer("CSS", "Lenovo", 3500);
        Computer computer2 = new Computer("FIX", "Acer", 4500);
        computerManager.addComputer(computer);
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer2);

        List<Computer> computerList = computerManager.getComputers();
        Assert.assertEquals(computerManager.getCount(),computerList.size());
        Assert.assertEquals(computerManager.getComputers(), computerList);
    }
    @Test
    public void testGetComputer() {
        Computer computer = new Computer("REX", "Asus", 2500);
        computerManager.addComputer(computer);
        Computer current = computerManager.getComputer("REX", "Asus");
        Assert.assertEquals(computer.getManufacturer(),current.getManufacturer());
        Assert.assertEquals(computer.getModel(), current.getModel());
        Assert.assertTrue(computer.getPrice() == current.getPrice());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowInvalidManufacturer() {
        computerManager.getComputer("ACC","Asus");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowInvalidModel() {
        Computer computer = new Computer("REX", "Asus", 2500);
        computerManager.addComputer(computer);
        computerManager.getComputer("REX","Lenovo");
    }
    @Test
    public void testRemove() {
        Computer computer = new Computer("REX", "Asus", 2500);
        Computer computer1 = new Computer("CSS", "Lenovo", 3500);
        Computer computer2 = new Computer("FIX", "Acer", 4500);
        computerManager.addComputer(computer);
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer2);
        Assert.assertEquals(3,computerManager.getCount());
        Computer current = computerManager.removeComputer("FIX", "Acer");
        Assert.assertEquals(2,computerManager.getCount());
        Assert.assertEquals("FIX",current.getManufacturer());
        Assert.assertEquals("Acer", current.getModel());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrowInvalidManufacturer() {
        computerManager.removeComputer("ACC","Asus");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrowInvalidModel() {
        Computer computer = new Computer("REX", "Asus", 2500);
        computerManager.addComputer(computer);
        computerManager.removeComputer("REX","Lenovo");
    }
    @Test
    public void testGetComputersByManufacturer() {
        Computer computer = new Computer("REX", "Asus", 2500);
        Computer computer1 = new Computer("REX", "Lenovo", 3500);
        Computer computer2 = new Computer("FIX", "Acer", 4500);
        computerManager.addComputer(computer);
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer2);
        List<Computer> computerList = computerManager.getComputersByManufacturer("REX");
        Assert.assertEquals(2,computerList.size());
        Computer computer3 = computerList.get(0);
        Assert.assertEquals("REX",computer3.getManufacturer());
    }


}