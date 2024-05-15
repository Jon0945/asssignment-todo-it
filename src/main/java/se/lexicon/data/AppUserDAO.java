package se.lexicon.data;

import se.lexicon.models.AppUser;

import java.util.Collection;

public interface AppUserDAO {
    AppUser persist(AppUser appUser); //Add new AppUser object to collection
    AppUser findByUsername(String username); //Returns single AppUser class object
    Collection<AppUser> findAll(); //Returns all AppUser class objects
    void remove(String username); //Removes one AppUser object from collection
}
