package com.FindYourRide.Routing.Operations;

import com.FindYourRide.Routing.Model.Route;
import com.FindYourRide.Routing.Model.User;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MatchMaking {

    public static String findMatch(Route insertObj) {

        boolean hosted = insertObj.isHosted();
        MongoOperations mongoOperations = new MongoTemplate(MongoClients.create(), "FindYourRide");
        String ans = "";
        if(hosted == false)
        {
            System.out.println("hosted false called");
            //need to return hosted == yes
//            Query hostedTrueQuery = new Query(Criteria.where("trips").elemMatch(Criteria.where("hosted").is(true)));
            Query hostedTrueQuery = new Query(Criteria.where("userId").is("123"));
            List<User> userList = mongoOperations.find(hostedTrueQuery, User.class);
            if(userList.size() == 0)
            {
                System.out.println(insertObj.getUserId() + " query rejected because no pairing is available");
                return ans;
            }
            for(User x : userList) {
                int numberOfTrips = x.getTrips().length;
                for(int i = 0; i < numberOfTrips; ++i)
                {
                    if(Distance.isValidDistance(insertObj.getDepartureCoordinate().getLatitude(),
                            insertObj.getDepartureCoordinate().getLongitude(),
                            x.getTrips()[i].getDepartureCoordinate().getLatitude(),
                            x.getTrips()[i].getDepartureCoordinate().getLongitude(),
                            5.0) == true && x.getTrips()[i].isHosted() == true)
                    {
                        ans += x.getUserPhone()+"-->"+Distance.distanceBetweenTwoPoints(
                                insertObj.getDepartureCoordinate().getLatitude(),
                                insertObj.getDepartureCoordinate().getLongitude(),
                                x.getTrips()[i].getDepartureCoordinate().getLatitude(),
                                x.getTrips()[i].getDepartureCoordinate().getLongitude()
                        ) + "\n";
                    }
//                    printing distance between two coordinates
                    System.out.println(Distance.distanceBetweenTwoPoints(insertObj.getDepartureCoordinate().getLatitude(),
                            insertObj.getDepartureCoordinate().getLongitude(),
                            x.getTrips()[i].getDepartureCoordinate().getLatitude(),
                            x.getTrips()[i].getDepartureCoordinate().getLongitude()));
                }
            }
            System.out.println("hosted false execution done");
        }
        else
        {
            System.out.println("hosted true called");
            //need to return hosted == no
            Query hostedFalseQuery = new Query(Criteria.where("userId").ne(insertObj.getUserId()));
            List<User> userList = mongoOperations.find(hostedFalseQuery, User.class);
            for(User x : userList) {
                int numberOfTrips = x.getTrips().length;
                for(int i = 0; i < numberOfTrips; ++i)
                {
                    if(Distance.isValidDistance(insertObj.getDepartureCoordinate().getLatitude(),
                            insertObj.getDepartureCoordinate().getLongitude(),
                            x.getTrips()[i].getDepartureCoordinate().getLatitude(),
                            x.getTrips()[i].getDepartureCoordinate().getLongitude(),
                            5.0) == true && x.getTrips()[i].isHosted() == false)
                    {
                        ans += x.getUserPhone()+"-->"+Distance.distanceBetweenTwoPoints(
                                insertObj.getDepartureCoordinate().getLatitude(),
                                insertObj.getDepartureCoordinate().getLongitude(),
                                x.getTrips()[i].getDepartureCoordinate().getLatitude(),
                                x.getTrips()[i].getDepartureCoordinate().getLongitude()
                        ) + "\n";
                    }
                }
            }
            System.out.println("true executed successfully");
        }
        return ans;
    }

}
