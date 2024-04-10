package se.lexicon.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
public class TodoItemTaskTest {
    private Person testPerson;
    private final String testFirstName = "Tony";
    private final String testLastName = "Test";
    private final String testEmail = "tony.test@gmail.com";
    private TodoItem testTodoItem;
    private final String testTitle = "Do Laundry";
    private final String testTaskDescription = "Transform filthy clothes into clean clothes";
    private final LocalDate testDeadLine = LocalDate.parse("2024-03-25");
    private TodoItemTask testTask;

    @Before
    public void createTestObjects() {
        testPerson = new Person(testFirstName,testLastName,testEmail);
        testTodoItem = new TodoItem(testTitle,testTaskDescription,testDeadLine,testPerson);
        testTask = new TodoItemTask(testTodoItem,testPerson);
    }
    @After
    public void killTestObjects() {
        testPerson = null;
        testTodoItem = null;
        testTask = null;
    }

    @Test
    public void beforeWorks() {
        //Arrange
        TodoItem expectedItem = testTodoItem;
        Person expectedAssignee = testPerson;

        //Act
        //Done by @Before

        //Assert
        assertTrue(testTask.getId() > 0);
        assertEquals(expectedItem,testTask.getTodoItem());
        assertEquals(expectedAssignee,testTask.getAssignee());
    }

    @Test
    public void assignedBooleanTest() {
        //Arrange
        boolean oldBoolean = testTask.isAssigned();

        //Act
        testTask.setAssigned(!oldBoolean);

        //Assert
        assertTrue(oldBoolean != testTask.isAssigned());
    }
    @Test
    public void trueOrNotTest() {
        //Arrange
        String positive = testTask.isTrue(!testTask.isAssigned());
        String negative = testTask.isTrue(testTask.isAssigned());

        //Assert
        assertEquals(positive, testTask.isTrue(!testTask.isAssigned()));
        assertEquals(negative,testTask.isTrue(testTask.isAssigned()));
    }

    @Test
    public void testToString() {
        //Act
        String result = testTask.toString();

        //Assert
        assertTrue(result.contains(testTodoItem.getTitle()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionThrownTest() {
        testTask.setTodoItem(null);
    }
}





