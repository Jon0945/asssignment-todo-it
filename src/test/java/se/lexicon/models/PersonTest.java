package se.lexicon.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    private Person testPerson;
    private final String testFirstName = "Tony";
    private final String testLastName = "Test";
    private final String testEmail = "tony.test@gmail.com";

    @Before
    public void createPerson() {
        testPerson = new Person(testFirstName,testLastName,testEmail);
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
        String expectedEmail = testEmail;

        //Act
        //Done by @Before

        //Assert
        assertTrue(testPerson.getId() > 0);
        assertEquals(expectedFirstName,testPerson.getFirstName());
        assertEquals(expectedLastName,testPerson.getLastName());
        assertEquals(expectedEmail,testPerson.getEmail());
    }

    @Test
    public void testSetFirstName() {
        //Arrange
        String personFirstName = "John";

        //Act
        testPerson.setFirstName(personFirstName);

        //Assert
        assertEquals(personFirstName,testPerson.getFirstName());
    }

    @Test
    public void testSetLastName() {
        //Arrange
        String personLastName = "Doe";

        //Act
        testPerson.setLastName(personLastName);

        //Assert
        assertEquals(personLastName,testPerson.getLastName());
    }

    @Test
    public void testSetEmail() {
        //Arrange
        String personEmail = "john.doe@gmail.com";

        //Act
        testPerson.setEmail(personEmail);

        //Assert
        assertEquals(personEmail,testPerson.getEmail());
    }

    @Test
    public void testGetSummary() {
        //Act
        String result = testPerson.getSummary();

        //Assert
        assertTrue(result.contains(testFirstName));
        assertTrue(result.contains(testLastName));
        assertTrue(result.contains(testEmail));
    }
}
