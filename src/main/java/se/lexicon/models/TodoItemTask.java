package se.lexicon.models;

public class TodoItemTask {
    //Static field
    private static int taskCounter = 1;

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
        id = taskCounter++;
        setAssigned(assigned);
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
    public String getSummary() {
        return "\nID: " + id +
                "\nAssigned: " + this.isTrue(assigned) +
                "\nTask: " + this.todoItem.getTitle() +
                "\nTask Assigned To: " + this.assignee.getFirstName() + " " + this.assignee.getLastName();
    }
}
