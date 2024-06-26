package se.lexicon.data;

import se.lexicon.models.AppUser;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserDAOImpl implements AppUserDAO{
    //Field
    private static Collection<AppUser> appUsers;

    //Constructor
    public AppUserDAOImpl() {
        appUsers = new ArrayList<>();}

    @Override
    public AppUser persist(AppUser appUser) {
        appUsers.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        for(AppUser user : appUsers) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    @Override
    public Collection<AppUser> findAll() { return appUsers;}
    @Override
    public void remove(String username) {
        for(AppUser user : appUsers) {
            if(user.getUsername().equals(username)) {
                appUsers.remove(user);
            }
        }
    }

}
