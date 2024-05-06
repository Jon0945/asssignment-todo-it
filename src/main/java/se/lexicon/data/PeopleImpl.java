package se.lexicon.data;

import se.lexicon.models.Person;

import java.util.ArrayList;
import java.util.Collection;

public class PeopleImpl implements People {
    private static Collection<Person> personList;

    public PeopleImpl() {
        personList = new ArrayList<>();
    }
    @Override
    public Person create(Person person) {
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
    public void deleteById(int id) {
        personList.removeIf(p -> p.getId() == id);
    }
}
