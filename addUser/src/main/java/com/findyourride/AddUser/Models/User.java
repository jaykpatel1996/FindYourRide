package com.findyourride.AddUser.Models;


public class User {

    private String userId;
    private String userName;
    private String userPhone;
    private Route[] trips;

    public User()
    {

    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User(String userId, String userName, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.trips = new Route[10];
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Route[] getTrips() {
        return trips;
    }

    public void setTrips(Route[] trips) {
        this.trips = trips;
    }
}
