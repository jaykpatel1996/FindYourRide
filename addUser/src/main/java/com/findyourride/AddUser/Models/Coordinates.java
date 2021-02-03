package com.findyourride.AddUser.Models;

public class Coordinates {

    private Double latitude;
    private Double longitude;

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    //static methods

    public static Coordinates toCoordinates(String latitude, String longitude) {

        Double latD = Double.parseDouble(latitude);
        Double longD = Double.parseDouble(longitude);

        Coordinates ans = new Coordinates(latD, longD);

        return ans;
    }

    public static String[] toString(Coordinates coordinate) {
        String[] ans = new String[2];
        ans[0] = String.valueOf(coordinate.getLatitude());
        ans[1] = String.valueOf(coordinate.getLongitude());
        return ans;
    }

}
