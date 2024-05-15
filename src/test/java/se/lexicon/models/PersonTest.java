package se.lexicon.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    private Person testPerson;
    private final String testFirstName = "Tony";
    private final String testLastName = "Test";


    @Before
    public void createPerson() {
        testPerson = new Person(testFirstName,testLastName);
    }

    @After
    public void killPerson() {
        testPerson = null;
    }

    @Test
    public void beforeWorks() {
        //Arrange
        String expectedFirstName = testFirstName;
        String expectedLastName = testLastName;


        //Act
        //Done by @Before

        //Assert
        assertTrue(testPerson.getId() > 0);
        assertEquals(expectedFirstName,testPerson.getFirstName());
        assertEquals(expectedLastName,testPerson.getLastName());

    }

    @Test
    public void testEqualsAndHashCode() {
        //Arrange
        Person testPerson2 = new Person(testFirstName,testLastName);

        //Assert
        assertNotEquals(testPerson2,testPerson);
        assertNotEquals(testPerson2.hashCode(),testPerson.hashCode());
    }
    @Test
    public void testToString() {
        //Act
        String result = testPerson.toString();

        //Assert
        assertTrue(result.contains(testFirstName));
        assertTrue(result.contains(testLastName));
        assertTrue(result.contains(testEmail));
    }

    @Test(expected = IllegalArgumentException.class)
    public void firstName_exceptionThrownTest() {
        testPerson.setFirstName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lastName_exceptionThrownTest() {
        testPerson.setLastName(null);
    }
}
