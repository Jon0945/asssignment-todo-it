package se.lexicon.dao;

import se.lexicon.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOList implements PersonDAO {
    private static List<Person> personList;

    public PersonDAOList() {
        personList = new ArrayList<Person>();
    }
    @Override
    public Person persist(Person person) {
        personList.add(person);
        return person;
    }
    @Override
    public Person findById(int id) {
        for(Person p : personList) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public Person findByEmail(String email) {
        for(Person p : personList) {
            if(p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }
    public List<Person> findAll() {
        return personList;
    }
    public void remove(int id) {
        personList.removeIf(p -> p.getId() == id);
    }
}
