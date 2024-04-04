package se.lexicon.dao;

import se.lexicon.models.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserDAOList implements AppUserDAO{
    //Field
    private static List<AppUser> appUsers;

    //Constructor
    public AppUserDAOList() {
        appUsers = new ArrayList<AppUser>();}

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
    public List findAll() { return appUsers;}
    @Override
    public void remove(String username) {
        for(AppUser user : appUsers) {
            if(user.getUsername().equals(username)) {
                appUsers.remove(user);
            }
        }
    }

}
