package com.findyourride.AddUser.Models;

import java.sql.Time;
import java.util.Date;

public class Route {
    private String userId;
    private String departureLocation;
    private String DestinationLocation;
    private Date departureDate;
    private Time departureTime;
    private Coordinates departureCoordinate;
    private Coordinates destinationCoordinate;
    private boolean hosted;

    public Route(String userId, Date departureDate, Time departureTime, Coordinates departureCoordinate, Coordinates destinationCoordinate, boolean hosted) {
        this.userId = userId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureCoordinate = departureCoordinate;
        this.destinationCoordinate = destinationCoordinate;
        this.hosted = hosted;
    }

    public Route(String userId, String departureLocation, String destinationLocation, Date departureDate, Time departureTime, Coordinates departureCoordinate, Coordinates destinationCoordinate, boolean hosted) {
        this.userId = userId;
        this.departureLocation = departureLocation;
        DestinationLocation = destinationLocation;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureCoordinate = departureCoordinate;
        this.destinationCoordinate = destinationCoordinate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return DestinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        DestinationLocation = destinationLocation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Coordinates getDepartureCoordinate() {
        return departureCoordinate;
    }

    public void setDepartureCoordinate(Coordinates departureCoordinate) {
        this.departureCoordinate = departureCoordinate;
    }

    public Coordinates getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public void setDestinationCoordinate(Coordinates destinationCoordinate) {
        this.destinationCoordinate = destinationCoordinate;
    }

    public boolean isHosted() {
        return hosted;
    }

    public void setHosted(boolean hosted) {
        this.hosted = hosted;
    }
}
