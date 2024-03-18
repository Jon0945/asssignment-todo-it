package se.lexicon.models;

public class Person {
    //Static Field
    private static int personCounter = 1;
    //Fields
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    //Constructor
    public Person( String firstName, String lastName, String email) {
        id = personCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Getters & Setters
    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    //Methods
    public String getSummary() {
        return "\nID: "+ id +
                "\nName: "+ firstName + " " + lastName +
                "\nEmail: "+ email;
    }
}

