package com.findyourride.AddUser.resources;

import com.findyourride.AddUser.Models.User;
import com.findyourride.AddUser.UserData.UserData;
import com.mongodb.client.MongoClient;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adduser")
public class AddUser {

    @RequestMapping("/working")
    public String working() {
        return "working";
    }

    @RequestMapping("/{userId}/{userName}/{userPhone}")
    public String addUser(@PathVariable("userId") String userId,
                                  @PathVariable("userName") String userName,
                                  @PathVariable("userPhone") String userPhone) {
        User user = new User(userId, userName, userPhone);
        boolean flag = UserData.insertUser(user);
        if(flag)
        {
            final String done = "done";
            return done;
        }
        else
        {
            final String error = "already exist";
            return error;
        }
    }

    @RequestMapping("/printusers")
    @Nullable
    public String printUsers() {
        String ans = "";
        User[] users = UserData.retrieveData();
        for(int i = 0; i < users.length; ++i)
        {
            ans += users[i].getUserId()+"   "+
                    users[i].getUserName()+"    "+
                    users[i].getUserPhone()+"\n";
        }
        return ans;
    }
}
