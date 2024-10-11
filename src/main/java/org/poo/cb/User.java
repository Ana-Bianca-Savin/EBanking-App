package org.poo.cb;

import java.util.ArrayList;

public class User {
    final private String email;
    final private String firstName;
    final private String lastName;
    final private String address;
    private boolean hasPremium;
    //  Portfolio that contains data about accounts and stocks
    private final Portfolio portfolio = new Portfolio();
    //  List of friends
    private final ArrayList<User> friends = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public boolean getHasPremium() {
        return hasPremium;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }
    public void setHasPremium(boolean hasPremium) {
        this.hasPremium = hasPremium;
    }

    public User(String email, String firstName, String lastName, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.hasPremium = false;
    }
}
