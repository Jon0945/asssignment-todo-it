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
    public void testGetSummary() {
        //Act
        String result = testPerson.getSummary();

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

    @Test(expected = IllegalArgumentException.class)
    public void email_exceptionThrownTest() {
        testPerson.setEmail(null);
    }
}
