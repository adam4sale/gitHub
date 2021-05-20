package com.ex;
import com.ex.controller.EmployeeController;
import com.ex.controller.PageController;
import com.ex.pojos.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.bson.types.ObjectId;

import java.util.Map;
//import javax.Json.*;

public class Main {
    public static void main(String[] args){
        //vars
        EmployeeController employee = new EmployeeController();
        PageController page = new PageController();

        //org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
        //Runs the javalin app
        Javalin app = Javalin.create(config -> config.addStaticFiles("/html"))
                .start(8080);//Start the Javalin app
        //Get and posts
        //Manager

        app.post("/form/approve", ctx -> {
            employee.setApproval(ctx);
        });
        app.put("/manager/requests/:id", ctx -> {
           String url = ctx.pathParam("id");
            //String url = ctx.fullUrl();
            System.out.println(url);

//            JSONObject obj = new JSONObject(ctx.body());
//            String username = obj.getString("username");
//            String password = obj.getString("password");

        });

        //Employee
        app.post("/changePassword", ctx -> {
            employee.changePassword(ctx);
        });

        app.get("/", ctx -> ctx.render("/html/EmployeeLogin.html"));

        app.post("/enter-home", ctx -> {
            employee.LogInEmployee(ctx);
        });

        app.post("/User", ctx -> {
            employee.getCurrentUser(ctx);
        });

        app.post("/Request", ctx -> {
            employee.getYourRequests(ctx);
        });

        app.post("/SubmitRequest", ctx -> {
            employee.submitRequest(ctx);
        });

        app.post("/Requests", ctx -> {employee.getAllRequests(ctx);});

//        app.post("/approved/*", ctx -> {
//            //String url = ctx.fullUrl();
//        });

        //page
        app.get("/employee/request", ctx -> {
            page.getRequestPage(ctx);
        });

        app.get("/home", ctx -> {
           page.getHomePage(ctx);
        });

        app.get("/employee/edit-user", ctx ->{
            page.getEditUser(ctx);
        });

        app.get("/homeManager", ctx -> {
            page.getManagerHome(ctx);
        });

        app.get("/manager/requests", ctx -> {
            page.getManagerRequests(ctx);
        });

        app.get("/login", ctx -> {
            page.getRequestLogin(ctx);
        });
    }
}
