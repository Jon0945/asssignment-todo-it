package se.lexicon.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TodoTest {
    private Todo testTodoitem;
    private final String testTitle = "Do Laundry";
    private final String testTaskDescription = "Transform filthy clothes into clean clothes";
    private final LocalDate testDeadLine = LocalDate.parse("2024-03-25");
    private final String testFirstName = "Tony";
    private final String testLastName = "Test";
    private final Person testCreator = new Person(testFirstName,testLastName);

    @Before
    public void createTodoItem() {
        testTodoitem = new Todo(testTitle,testTaskDescription,testDeadLine,testCreator.getId());
    }

    @After
    public void killTodoItem() {
        testTodoitem = null;
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
        assertTrue(testTodoitem.getId() > 0);
        assertEquals(expectedTitle, testTodoitem.getTitle());
        assertEquals(expectedTaskDescription, testTodoitem.getDescription());
        assertEquals(expectedDeadline, testTodoitem.getDeadline());
        assertEquals(expectedCreator, testTodoitem.getCreator());
    }

    @Test
    public void todoItemBooleanTest() {
        //Arrange
        boolean oldBoolean = testTodoitem.isDone();

        //Act
        testTodoitem.setDone(!oldBoolean);

        //Assert
        assertTrue(oldBoolean != testTodoitem.isDone());
    }

    @Test
    public void trueOrNotTest() {
        //Arrange
        String positive = testTodoitem.isTrue(!testTodoitem.isDone());
        String negative = testTodoitem.isTrue(testTodoitem.isDone());

        //Assert
        assertEquals(positive, testTodoitem.isTrue(!testTodoitem.isDone()));
        assertEquals(negative, testTodoitem.isTrue(testTodoitem.isDone()));
    }

    @Test
    public void testToString() {
        //Act
        String result = testTodoitem.toString();

        //Assert
        assertTrue(result.contains(testTitle));
        assertTrue(result.contains(testTaskDescription));
        assertTrue(result.contains(String.valueOf(testDeadLine)));
    }

    @Test
    public void testIsOverdue() {
        //Arrange
        testTodoitem.setDeadline(LocalDate.now().minusDays(10));

        //Act
        boolean result = testTodoitem.isOverdue();

        //Assert
        assertTrue(result);
    }

    @Test
    public void testIsNotOverdue() {
        //Arrange
        testTodoitem.setDeadline(LocalDate.now().plusDays(10));

        //Act
        boolean result = testTodoitem.isOverdue();

        //Assert
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void title_exceptionThrownTest() {
        testTodoitem.setTitle(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void deadline_exceptionThrownTest() {
        testTodoitem.setDeadline(null);
    }
}
