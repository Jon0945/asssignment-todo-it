package se.lexicon.models;

import java.util.Objects;
import se.lexicon.sequencer.TodoItemTaskIdSequencer;

public class TodoItemTask {
    //Fields
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;

    //Getters & Setters
    public int getId() {return id;}
    public boolean isAssigned() {return assigned;}
    public void setAssigned(boolean assigned) {this.assigned = assigned;}
    public TodoItem getTodoItem() {return todoItem;}
    public void setTodoItem(TodoItem todoItem) {
        if(todoItem == null) {
            throw new IllegalArgumentException("todoItem cannot be null");
        }
        this.todoItem = todoItem;}
    public Person getAssignee() {return assignee;}
    public void setAssignee(Person assignee) {
            this.assignee = assignee;
            this.assigned = true;
        }

    //Constructor
    public TodoItemTask(TodoItem todoItem, Person assignee) {
        id = TodoItemTaskIdSequencer.nextId();
        setTodoItem(todoItem);
        setAssignee(assignee);
    }

    //Methods
    public String isTrue(boolean condition) {
        if (condition) {
            return "Yes";
        }
        return "No";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return id == that.id && assigned == that.assigned && Objects.equals(todoItem, that.todoItem);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, assigned, todoItem);
    }

    @Override
    public String toString() {
        return "TodoItemTask{" +
                "id=" + id +
                ", assigned=" + assigned +
                ", todoItem=" + todoItem +
                '}';
    }
}
