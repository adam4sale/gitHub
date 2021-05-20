package com.ex.pojos;


import org.bson.types.ObjectId;

import java.util.Date;

public class Request {
    //vars
    public String name;
    public String body;
    public ObjectId id;
    public String amount;
    public Date date;
    public String approved;

    //Constructors
    public Request(){

    }



    public Request(String amount, String body, String name, Date date, String approved) {
        this.amount = amount;
        //this.id = id;
        this.body = body;
        this.name = name;
        this.date = date;
        this.approved = approved;
    }
    //Getters and Setters
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String isApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    //toString method
    @Override
    public String toString() {


        return "Request{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}
