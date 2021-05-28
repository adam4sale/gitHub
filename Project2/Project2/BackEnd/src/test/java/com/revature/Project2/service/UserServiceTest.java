package com.revature.Project2.service;

import com.revature.Project2.controller.UserController;
import com.revature.Project2.pojo.User;
import com.revature.Project2.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    UserService userService = new UserService();
    UserController controller= new UserController();
    @Autowired
    UserRepository userRepo;
    User user = new User();
    @BeforeTestClass
    public void beforeTests(){
        ObjectId objectId = new ObjectId();
        user = new User(objectId, "user",
                "email@email.com","123","United Kingdom",30 );
        user.setUsername("user");
        user.setPassword("123");
        userRepo.insert(user);
    }

    @Test
    public void verifyLoginTest(){
        try{
            boolean verify = userService.verifyLogin("user","123");
            Assert.isTrue(verify, "true");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUsersTest(){
        List<User> list = new ArrayList<>();
        try{
            list = userService.findAllUsers();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.noNullElements(list, "There should be at least one user");
    }




}