package se.lexicon.data;

import se.lexicon.models.Person;

import java.util.Collection;

public interface People {
    Person create(Person person); //Add new Person class object to collection
    Collection<Person> findAll(); //Returns all Person class objects
    Person findById(int id); //Returns single Person class object
    Collection<Person> findByName(String name); //Returns all Person class objects matching String
    Person update(Person person); //Updates information about a Person class object
    boolean deleteById(int id); //Removes one Person class object from collection
}
