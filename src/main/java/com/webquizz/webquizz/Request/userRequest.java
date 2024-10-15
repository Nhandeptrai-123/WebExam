package com.webquizz.webquizz.Request;

import com.webquizz.webquizz.Reponsitory.userReponsitory;
import com.webquizz.webquizz.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userRequest {
    @Autowired
    private userReponsitory userReponsitory;

    public user createUser(user user){
        return userReponsitory.save(user);
    }
}
