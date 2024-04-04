package se.lexicon.dao;

import se.lexicon.models.Person;

import java.util.Collection;
import java.util.List;

public interface PersonDAO {
    Person persist(Person person); //Add new Person class object to collection
    Person findById(int id); //Returns single Person class object
    Person findByEmail(String email); //Returns single Person class object
    Collection<Person> findAll(); //Returns all Person class objects
    void remove(int id); //Removes one Person class object from collection
}
