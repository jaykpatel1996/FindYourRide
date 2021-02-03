package com.FindYourRide.Routing.resources;

import com.FindYourRide.Routing.Model.Coordinates;
import com.FindYourRide.Routing.Model.Route;
import com.FindYourRide.Routing.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Date departureDate = new Date(departureDateString);
        Time departureTime = null;
        try {
            departureTime = new Time(formatter.parse(departureTimeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        if(emptySpace < 10) {
            trips[emptySpace] = insertObj;
        }
        else
        {
            return "reached limit of 10";
        }
        Update update = new Update().set("trips", trips);
        mongoOperations.updateFirst(query, update, User.class);
        return "trip added successfully";

    }

}
