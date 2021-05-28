package com.revature.Project2.pojo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "items")
public class Item {
    //vars
    private ObjectId id;
    private String title;
    private double price;
    private String owner;
    private int increment;
    private String image;
    private boolean accepted;
    /*Thank you Lombok for the getters, setters, constructors, and toString()
    */
}
