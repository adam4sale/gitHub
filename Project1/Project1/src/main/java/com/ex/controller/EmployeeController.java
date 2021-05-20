package com.ex.controller;

import com.ex.pojos.User;
import com.ex.service.LogInService;
import com.ex.service.RequestService;
import com.ex.service.UserService;
import io.javalin.http.Context;
import org.apache.logging.log4j.LogManager;

import java.util.Date;
import java.util.Random;

public class EmployeeController {
    //vars
    org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
    LogInService log = new LogInService();
    UserService user = new UserService();
    RequestService request = new RequestService();
    private static User currentUser;

    /**
     * Takes a context object for the html form
     * and sets the static current user
     * @param ctx context
     */
    public void LogInEmployee(Context ctx){
        //vars
        //form param
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        //Set the objects
        String id = log.checkLogIn(username, password);
        this.currentUser = user.findUserById(id);//Set the current user
        String rank = currentUser.getRank();
        //Check the rank to determine which home page
        if(rank != null) {
            currentUser = user.findUserById(id);
            if(rank.equals("Commander") || rank.equals("Captain")){
                ctx.render("/html/ManagerHome.html");
            }
            else{ ctx.render("/html/EmployeeHome.html"); }
        }
        else{
            ctx.render("/html/EmployeeLogin.html");
        }
    }

    /**
     * Return the current user object
     * @param ctx context
     */
    public void getCurrentUser(Context ctx){
        ctx.json(currentUser);
    }

    /**
     * Submit a request to the MongoDB
     * @param ctx Context
     */
    public void submitRequest(Context ctx){
        Random random = new Random();
        int unique = random.nextInt(999);
        String body = ctx.formParam("body");
        String amount = ctx.formParam("amount");
        Date date = new Date();
        body += " [Unique ID: " + unique + "]";
        request.insertRequest(currentUser.getName(), body, amount, date, "pending" );
        ctx.render("/html/EmployeeRequest.html");
    }

    /**
     * Post a list of request from a the DB to
     * the Employee Request form.
     * @param ctx context
     */
    public void getYourRequests(Context ctx){
        rootLogger.info(request.getYourRequests(currentUser.getName()));
       ctx.json(request.getYourRequests(currentUser.getName()));
    }

    public void getAllRequests(Context ctx){
        //rootLogger.info(request.getYourRequests(currentUser.getName()));
        ctx.json(request.getAllRequests());
    }

    public void changePassword(Context ctx){
        String password = ctx.formParam("password");
        String confirm = ctx.formParam("confirm");

        boolean flag = log.changePassword(currentUser.getId(), password, confirm);
        rootLogger.info(flag + " is the flag in changePassword EmployeeController");

        if(flag){
            ctx.render("/html/EmployeeLogin.html");
        }
        else {
            ctx.render("/html/EmployeeEdit.html");
        }
    }

    public void setApproval(Context ctx){
        String body = ctx.formParam("approval");
        rootLogger.info(body);
        request.validateRequest(body);
        ctx.render("/html/ManagerRequests.html");
    }

//    public void getApproved(Context ctx){
//        ctx.json()
//    }

}
