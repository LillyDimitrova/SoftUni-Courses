package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import javax.naming.OperationNotSupportedException;

    public class DatabaseTest {

        private Database database;
        private static final Person[] PEOPLE = {new Person(1,"Ivan"),new Person(2,"Georgi"),new Person(3,"Pesho")};

        @Before
        public void prepareDatabase() throws OperationNotSupportedException {
            database = new Database(PEOPLE);
        }

        @Test
        public void testConstructorHasCreateValidObject() {

            Person[] databaseElements = database.getElements();
            Assert.assertArrayEquals(databaseElements,PEOPLE);

        }

        @Test(expected = OperationNotSupportedException.class)
        public void testConstructorThrowWhenMoreThanSixteenElements() throws OperationNotSupportedException {
            Person[] people = new Person[17];
            new Database(people);
        }

        @Test(expected = OperationNotSupportedException.class)
        public void testConstructorThrowLessThanOneElement() throws OperationNotSupportedException {
            Person[] people = new Person[0];
            new Database(people);
        }

        @Test
        public void testAddShouldElement() throws OperationNotSupportedException {

            database.add(new Person(4,"Lili"));
            Person[] elements = database.getElements();
            int expectedId = 4;
            String expectedName = "Lili";
            Assert.assertEquals(elements.length,PEOPLE.length + 1);
            Assert.assertEquals(elements[elements.length - 1].getId(),expectedId);
            Assert.assertEquals(elements[elements.length - 1].getUsername(),expectedName);
        }

        @Test(expected = OperationNotSupportedException.class)
        public void testAddThrowWhenElementIsNull() throws OperationNotSupportedException {
            database.add(null);

        }

        @Test
        public void testRemoveLastElement() throws OperationNotSupportedException {

            database.remove();
            int expectedId = 2;
            String expectedName = "Georgi";
            Person[] people = database.getElements();
            Assert.assertEquals(PEOPLE.length - 1, people.length);
            Assert.assertEquals(expectedId,people[people.length - 1].getId());
            Assert.assertEquals(expectedName,people[people.length - 1].getUsername());

        }

        @Test(expected = OperationNotSupportedException.class)
        public void testRemoveThrowWhenDatabaseIsEmpty() throws OperationNotSupportedException {
            database = new Database();
            database.remove();
        }

        @Test(expected = OperationNotSupportedException.class)
        public void testFindByUsernameThrowNullParam() throws OperationNotSupportedException {
            database.findByUsername(null);
        }

        @Test(expected = OperationNotSupportedException.class)
        public void testFindByUsernameThrowEmptyDatabase() throws OperationNotSupportedException {
            database = new Database();
            database.findByUsername("Lili");
        }

        @Test
        public void testFindByUsername() throws OperationNotSupportedException {
            Person person = database.findByUsername("Ivan");
            Assert.assertEquals(1,person.getId());
            Assert.assertEquals("Ivan",person.getUsername());
        }

        @Test(expected = OperationNotSupportedException.class)
        public void testFindByUserNameMoreThanOnePerson() throws OperationNotSupportedException {
            database.add(new Person(4,"Ivan"));
            database.findByUsername("Ivan");
        }

         @Test
        public void testFindById() throws OperationNotSupportedException {
            Person person = database.findById(2);
             Assert.assertEquals(2,person.getId());
             Assert.assertEquals("Georgi",person.getUsername());
         }

        @Test(expected = OperationNotSupportedException.class)
        public void testFindByIdThrowEmptyDatabase() throws OperationNotSupportedException {
            database = new Database();
            database.findById(2);
        }

        @Test(expected = OperationNotSupportedException.class)
        public void testFindByIdMoreThanOnePerson() throws OperationNotSupportedException {
            database.add(new Person(2,"Ivan"));
            database.findById(2);
        }
}