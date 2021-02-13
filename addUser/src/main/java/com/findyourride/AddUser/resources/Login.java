package com.findyourride.AddUser.resources;

import com.findyourride.AddUser.Models.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class Login {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("loginUser")
    @ResponseBody
    public User login(@RequestParam("userPhone") String userPhone) {


//        can add password here

//        MongoOperations mongoOperations = new MongoTemplate(MongoClients.create(), "FindYourRide");

//        List<User> users = mongoOperations.find(new Query(Criteria.where("userPhone").is(userPhone)), User.class);

        User users = mongoTemplate.findOne(new Query(Criteria.where("userPhone").is(userPhone)), User.class);



//        TODO
        if(users != null)
        {
            return users;
        }
        else
        {
            return null;
        }

    }

}
