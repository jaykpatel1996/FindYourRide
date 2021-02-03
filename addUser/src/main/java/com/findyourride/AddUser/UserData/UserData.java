package com.findyourride.AddUser.UserData;

import com.findyourride.AddUser.Models.User;
import com.mongodb.client.MongoClients;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    @Autowired
    private static MongoTemplate mongoTemplate;

    private static ArrayList<User> users = new ArrayList<>();

    //inserting user into database
    public static boolean insertUser(User user) {
//        for(User x : users)
//        {
//            if(x.getUserId().compareTo(user.getUserId()) == 0)
//                return false;
//        }
//        users.add(user);
//        return true;

        MongoOperations mongoOperations = new MongoTemplate(MongoClients.create(), "FindYourRide");
        String id = user.getUserId();
        Query query = new Query(Criteria.where("userId").is(id));
        List<User> userList = mongoOperations.find(query, User.class);
//        mongoOperations.remove(query, User.class);
//        for(User x : userList)
//        {
//            System.out.println(x.getUserId()+' '+x.getUserName()+' '+x.getUserPhone());
//        }
        if(userList.size() > 0)
        {
            return false;
        }
        mongoOperations.insert(user);

        return true;
    }

    //removing user from database
    public static boolean removeUser(String userId) {
        for(User x : users)
        {
            if(x.getUserId().compareTo(userId) == 0)
            {
                users.remove(x);
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static User[] retrieveData() {
        int arraySize = users.size();
        User[] ans = new User[arraySize];
        for(int i = 0; i < arraySize; ++i)
        {
            ans[i] = users.get(i);
        }
        return ans;
    }
}
