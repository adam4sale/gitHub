package com.revature.Project2.controller;

import com.revature.Project2.pojo.User;
import com.revature.Project2.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/user")
@Log4j2
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        log.info("attempting login");
        if (service.verifyLogin(user.getUsername(), user.getPassword())) return ResponseEntity.ok(service.findUser(user.getUsername()));
        else return new ResponseEntity<>("Invalid login credentials",
                    HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/signup")
    public int signup(@RequestBody User user){
        log.info("User controller: signup");
        int status = 400;
        if(service.addUser(user)){ status = 200; }
        return status;
    }

    @GetMapping("/getBalance/{username}")
    public double getBalance(@PathVariable("username") String username){
        return service.findBalance((username));
    }

    @PostMapping("/deposit")
    @ResponseBody
    public void deposit(@RequestParam double deposit, @RequestParam String username) {
        service.depositForUser(deposit, username);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity withdraw(@RequestParam double withdrawal, @RequestParam String username) {
        if (service.userCanWithdraw(withdrawal, username)) { service.withdrawForUser(withdrawal, username);
            return new ResponseEntity<>("Successful Withdraw", HttpStatus.OK);
        }
        else { return new ResponseEntity<>("Balance can't be below zero",
                    HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/update_username")
    public void updateUsername(@RequestParam String updatedInfo, @RequestParam String username) {
        service.updateUsernameForUser(updatedInfo, username); }

    @PostMapping("/update_country")
    public void updateCountry(@RequestParam String updatedInfo, @RequestParam String username) {
        service.updateCountryForUser(updatedInfo, username); }

}
