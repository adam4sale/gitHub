package com.ex.service;

import com.ex.dao.MongoDao;
import com.ex.pojos.User;

import java.util.List;

public class UserService {
    MongoDao dao = new MongoDao();

    /**
     * Method that uses the MongoDao to get a list of users
     * @return a list of employees onboard enterprise
     */
    public List<User> getUsers(){
        List<User> list = dao.listUsers();
        return list;
    }

    /**
     * Returns a user by their id
     * @param id
     * @return
     */
    public User findUserById(String id){
        User user = dao.findUserById(id);
        return user;
    }
}
