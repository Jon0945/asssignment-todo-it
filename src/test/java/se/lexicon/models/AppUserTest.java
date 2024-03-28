package se.lexicon.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppUserTest {
    private AppUser testAppUser;
    private final String testUsername = "tonytest1234";
    private final String testPassword = "password1234";

    @Before
    public void createTestObject() {
        testAppUser = new AppUser(testUsername,testPassword,AppRole.ROLE_APP_USER);
    }

    @After
    public void killTestObject() {
        testAppUser = null;
    }

    @Test
    public void beforeWorks() {
        //Arrange
        String expectedUserName = testUsername;
        String expectedPassWord = testPassword;
        AppRole expectedAppRole = AppRole.ROLE_APP_USER;

        //Act
        //Done by @Before

        //Assert
        assertEquals(expectedUserName,testAppUser.getUsername());
        assertEquals(expectedPassWord,testAppUser.getPassword());
        assertEquals(expectedAppRole,testAppUser.getRole());
    }

    @Test
    public void testEqualsAndHashCode() {
        //Arrange
        AppUser testAppUser2 = new AppUser(testUsername,testPassword,AppRole.ROLE_APP_USER);

        //Assert
        assertEquals(testAppUser2,testAppUser);
        assertEquals(testAppUser2.hashCode(),testAppUser.hashCode());
    }

    @Test
    public void testToString() {
        //Act
        String result = testAppUser.toString();

        //Assert
        assertTrue(result.contains(testUsername));
        assertTrue(result.contains(String.valueOf(AppRole.ROLE_APP_USER)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void username_exceptionThrownTest() {
        testAppUser.setUsername(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void password_exceptionThrownTest() {
        testAppUser.setPassword(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void role_exceptionThrownTest() {
        testAppUser.setRole(null);
    }

}
