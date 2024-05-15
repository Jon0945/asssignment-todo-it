package se.lexicon.models;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {
    //Fields
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private int assigneeId;

    //Getters & Setters
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public boolean isDone() {
        return done;
    }
    public int getAssigneeId() {
        return assigneeId;
    }

    //Constructor
    public Todo(int id,String title, String description, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }
    public Todo(String title, String description, LocalDate deadline) {
        this(0,title,description,deadline);
    }

    //Methods
    public boolean isOverdue() {
        return LocalDate.now().isAfter(this.deadline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todoitem = (Todo) o;
        return id == todoitem.id && done == todoitem.done && Objects.equals(title, todoitem.title) && Objects.equals(description, todoitem.description) && Objects.equals(deadline, todoitem.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadline, done);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", taskDescription='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                '}';
    }
}
