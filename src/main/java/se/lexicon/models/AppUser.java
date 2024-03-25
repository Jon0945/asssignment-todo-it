package se.lexicon.models;

import java.util.Objects;

public class AppUser {
    //Fields
    private String username;
    private String password;
    private AppRole role;

    //Constructor
    public AppUser(String username, String password, AppRole role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    //Getters & Setters
    public String getUsername() {return username;}
    public void setUsername(String username) {
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {
        if(password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;}
    public AppRole getRole() {return role;}
    public void setRole(AppRole role) {
        if(role == null){throw new IllegalArgumentException("Role cannot be null");}
        this.role = role;}

    //Equal & Hashcode Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "Username='" + username + '\'' +
                ", Role=" + role +
                '}';
    }
}
