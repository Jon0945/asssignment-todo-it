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

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline == null) {
            throw new IllegalArgumentException("deadline cannot be null");
        }
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    //Constructor
    public Todo(String title, String taskDescription, LocalDate deadline, int assigneeId) {
        id = this.id;
        setTitle(title);
        setDescription(taskDescription);
        setDeadline(deadline);
        setDone(done);
        setAssigneeId(assigneeId);
    }

    //Methods
    public String isTrue(boolean condition) {
        if (condition) {
            return "Yes";
        }
        return "No";
    }

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
