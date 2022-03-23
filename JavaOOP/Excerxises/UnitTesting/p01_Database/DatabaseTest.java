package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database database;
    private static final Integer[] NUMBERS = {15, 80, 35, 48, 50, 98};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    @Test
    public void testConstructorHasCreateValidObject() {

        Integer[] databaseElements = database.getElements();
        Assert.assertArrayEquals(databaseElements,NUMBERS);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMoreThanSixteenElements() throws OperationNotSupportedException {
        Integer[] elements = new Integer[17];
        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowLessThanOneElement() throws OperationNotSupportedException {
        Integer[] elements = new Integer[0];
        new Database(elements);
    }

    @Test
    public void testAddShouldElement() throws OperationNotSupportedException {
        Integer currentNumber = 17;
        database.add(currentNumber);
        Integer[] elements = database.getElements();
        Assert.assertEquals(elements.length,NUMBERS.length + 1);
        Assert.assertEquals(elements[elements.length - 1],currentNumber);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowWhenElementIsNull() throws OperationNotSupportedException {
        Integer currentNumber = null;
        database.add(currentNumber);

    }

    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Assert.assertEquals(database.getElements().length, NUMBERS.length - 1);
        Assert.assertEquals(Integer.valueOf(50),database.getElements()[database.getElements().length - 1]);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowWhenDatabaseIsEmpty() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }
}
