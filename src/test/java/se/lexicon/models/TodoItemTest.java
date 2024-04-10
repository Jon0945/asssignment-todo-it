package se.lexicon.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TodoItemTest {
    private TodoItem testTodoItem;
    private final String testTitle = "Do Laundry";
    private final String testTaskDescription = "Transform filthy clothes into clean clothes";
    private final LocalDate testDeadLine = LocalDate.parse("2024-03-25");
    private final String testFirstName = "Tony";
    private final String testLastName = "Test";
    private final String testEmail = "tony.test@gmail.com";
    private final Person testCreator = new Person(testFirstName,testLastName,testEmail);

    @Before
    public void createTodoItem() {
        testTodoItem = new TodoItem(testTitle,testTaskDescription,testDeadLine,testCreator);
    }

    @After
    public void killTodoItem() {
        testTodoItem = null;
    }

    @Test
    public void beforeWorks() {
        //Arrange
        String expectedTitle = testTitle;
        String expectedTaskDescription = testTaskDescription;
        LocalDate expectedDeadline = testDeadLine;
        Person expectedCreator = testCreator;

        //Act
        //Done by @Before

        //Assert
        assertTrue(testTodoItem.getId() > 0);
        assertEquals(expectedTitle,testTodoItem.getTitle());
        assertEquals(expectedTaskDescription,testTodoItem.getTaskDescription());
        assertEquals(expectedDeadline,testTodoItem.getDeadline());
        assertEquals(expectedCreator,testTodoItem.getCreator());
    }

    @Test
    public void todoItemBooleanTest() {
        //Arrange
        boolean oldBoolean = testTodoItem.isDone();

        //Act
        testTodoItem.setDone(!oldBoolean);

        //Assert
        assertTrue(oldBoolean != testTodoItem.isDone());
    }

    @Test
    public void trueOrNotTest() {
        //Arrange
        String positive = testTodoItem.isTrue(!testTodoItem.isDone());
        String negative = testTodoItem.isTrue(testTodoItem.isDone());

        //Assert
        assertEquals(positive, testTodoItem.isTrue(!testTodoItem.isDone()));
        assertEquals(negative,testTodoItem.isTrue(testTodoItem.isDone()));
    }

    @Test
    public void testToString() {
        //Act
        String result = testTodoItem.toString();

        //Assert
        assertTrue(result.contains(testTitle));
        assertTrue(result.contains(testTaskDescription));
        assertTrue(result.contains(String.valueOf(testDeadLine)));
    }

    @Test
    public void testIsOverdue() {
        //Arrange
        testTodoItem.setDeadline(LocalDate.now().minusDays(10));

        //Act
        boolean result = testTodoItem.isOverdue();

        //Assert
        assertTrue(result);
    }

    @Test
    public void testIsNotOverdue() {
        //Arrange
        testTodoItem.setDeadline(LocalDate.now().plusDays(10));

        //Act
        boolean result = testTodoItem.isOverdue();

        //Assert
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void title_exceptionThrownTest() {
        testTodoItem.setTitle(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void deadline_exceptionThrownTest() {
        testTodoItem.setDeadline(null);
    }
}
