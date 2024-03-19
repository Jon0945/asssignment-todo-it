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
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    //Getters & Setters
    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {if(firstName == null || firstName.trim().isEmpty()){
            throw new IllegalArgumentException("FirstName cannot be null or empty");}
        this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {if(lastName == null || lastName.trim().isEmpty()){
            throw new IllegalArgumentException("LastName cannot be null or empty");
        }
        this.lastName = lastName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;}

    //Methods
    public String getSummary() {
        return "\nID: "+ id +
                "\nName: "+ firstName + " " + lastName +
                "\nEmail: "+ email;
    }
}

