package com.FindYourRide.Routing.resources;

import com.FindYourRide.Routing.Model.Coordinates;
import com.FindYourRide.Routing.Model.Route;
import com.FindYourRide.Routing.Model.User;
import com.FindYourRide.Routing.Operations.MatchMaking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/addtrip")
public class AddTrip {

    @Qualifier("getMongoOperations")
    @Autowired
    private MongoOperations mongoOperations;


//    @RequestMapping("/{userId}/{departureCoordinateLat}/{departureCoordinateLong}/{destinationCoordinateLat}/{destinationCoordinateLong}/{departureDate}/{departureTime}/{hosted}")
    @RequestMapping("/newtrip")
    @ResponseBody
    public String addTrip(@RequestParam("userId") String userId,
                          @RequestParam("departureCoordinateLat") String departureCoordinateLatString,
                          @RequestParam("departureCoordinateLong") String departureCoordinateLongString,
                          @RequestParam("destinationCoordinateLat") String destinationCoordinateLatString,
                          @RequestParam("destinationCoordinateLong") String destinationCoordinateLongString,
                          @RequestParam("departureDate") String departureDateString,
                          @RequestParam("departureTime") String departureTimeString,
                          @RequestParam("hosted") boolean hosted)  {

        Coordinates departureCoordinate = Coordinates.toCoordinates(departureCoordinateLatString, departureCoordinateLongString);
        Coordinates destinationCoordinate = Coordinates.toCoordinates(destinationCoordinateLatString, destinationCoordinateLongString);

        Date departureDate = new Date(departureDateString);
        Time departureTime = new Time(Long.parseLong(departureTimeString));
        System.out.println(departureTime.toString());

        Query query = new Query(Criteria.where("userId").is(userId));

        Route insertObj = new Route(userId, departureDate, departureTime, departureCoordinate, destinationCoordinate, hosted);

        User updatingUser = mongoOperations.findOne(query, User.class);
        if(updatingUser == null)
        {
            return "user not found";
        }
        Route[] trips = updatingUser.getTrips();
        int emptySpace = 0;
        while(emptySpace < 10 && trips[emptySpace] != null)
        {
            ++emptySpace;
        }
        String ans = "";
        if(emptySpace < 10) {
            // match making algorithm
            ans = MatchMaking.findMatch(insertObj);
            trips[emptySpace] = insertObj;
        }
        else
        {
            return "reached limit of 10";
        }
        Update update = new Update().set("trips", trips);
        mongoOperations.updateFirst(query, update, User.class);
        String returningString = "trip added successfully" + ans;
        System.out.println(returningString);
        return returningString;

    }

}
