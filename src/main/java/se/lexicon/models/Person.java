package se.lexicon.models;

import java.util.Objects;
import se.lexicon.sequencer.PersonIdSequencer;

public class Person {
    //Fields
    private int id;
    private String firstName;
    private String lastName;


    //Constructor
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person(String firstName,String lastName) {
        this(0,firstName,lastName);
    }

    //Getters & Setters
    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().isEmpty()){
            throw new IllegalArgumentException("FirstName cannot be null or empty");
        }
        this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().isEmpty()){
            throw new IllegalArgumentException("LastName cannot be null or empty");
        }
        this.lastName = lastName;}

    //Equals & Hashcode Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

