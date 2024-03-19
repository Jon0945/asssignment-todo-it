package se.lexicon.models;

import java.time.LocalDate;

public class TodoItem {
    //Static Field
    private static int itemCounter = 1;

    //Fields
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadline;
    private boolean done;
    private Person creator;

    //Getters & Setters
    public int getId() {return id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        this.title = title;}
    public String getTaskDescription() {return taskDescription;}
    public void setTaskDescription(String taskDescription) {this.taskDescription = taskDescription;}
    public LocalDate getDeadline() {return deadline;}
    public void setDeadline(LocalDate deadline) {if(deadline == null) {
            throw new IllegalArgumentException("deadline cannot be null");
        }
        this.deadline = deadline;}
    public boolean isDone() {return done;}
    public void setDone(boolean done) {this.done = done;}
    public Person getCreator() {return creator;}
    public void setCreator(Person creator) {this.creator = creator;}

    //Constructor
    public TodoItem(String title,String taskDescription,LocalDate deadline, Person creator) {
        id = itemCounter++;
        setTitle(title);
        setTaskDescription(taskDescription);
        setDeadline(deadline);
        setDone(done);
        setCreator(creator);}
    //Methods
    public String isTrue(boolean condition) {if (condition) {
            return "Yes";}
        return "No";}
    public boolean isOverdue() {
        return LocalDate.now().isAfter(this.deadline);
    }
    public String getSummary() {
        return  "\nID: "+ id +
                "\nTitle: "+ title +
                "\nDescription: "+ taskDescription +
                "\nDeadline: "+ deadline.toString() +
                "\nDone: "+ this.isTrue(done) +
                "\nCreator: "+creator.getFirstName() + " " + creator.getLastName();}}