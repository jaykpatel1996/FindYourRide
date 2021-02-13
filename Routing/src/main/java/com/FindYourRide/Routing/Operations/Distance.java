package com.FindYourRide.Routing.Operations;

public class Distance {

    public static Double distanceBetweenTwoPoints(double lat1,
                                           double long1,
                                           double lat2,
                                           double long2) {

        //converting latitude and longitude into radian

        long1 = Math.toRadians(long1);
        long2 = Math.toRadians(long2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = long2 - long1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371;

        return(c * r);
    }

    public static boolean isValidDistance(double lat1, double long1, double lat2, double long2, double distance) {
        double dist = Distance.distanceBetweenTwoPoints(lat1, long1, lat2, long2);
        if(dist <= distance)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
