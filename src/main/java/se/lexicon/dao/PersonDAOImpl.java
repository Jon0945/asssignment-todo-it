package se.lexicon.dao;

import se.lexicon.models.Person;

import java.util.ArrayList;
import java.util.Collection;

public class PersonDAOImpl implements PersonDAO {
    private static Collection<Person> personList;

    public PersonDAOImpl() {
        personList = new ArrayList<>();
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
    @Override
    public Person findByEmail(String email) {
        for(Person p : personList) {
            if(p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }
    @Override
    public Collection<Person> findAll() {
        return personList;
    }
    @Override
    public void remove(int id) {
        personList.removeIf(p -> p.getId() == id);
    }
}
