package com.revature.Project2.pojo;

import lombok.*;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    //vars
    @Id
    private ObjectId id;
    private String username;
    private String email;
    private String password;
    private String country;
    private double balance;


    /*
    Setters, Getters, Constructors, and toString handled by Lombok!!!
     */
}
