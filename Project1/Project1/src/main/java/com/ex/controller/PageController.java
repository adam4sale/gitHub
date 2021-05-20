package com.ex.controller;

import com.ex.pojos.*;
import com.ex.service.*;
import io.javalin.http.Context;

public class PageController {
    //employee

    /**
     * Renders the EmployeeRequest page
     * @param ctx Context
     */
    public void getRequestPage(Context ctx){
        ctx.render("/html/EmployeeRequest.html");
    }

    public void getHomePage(Context ctx){ctx.render("/html/EmployeeHome.html");}

    public void getEditUser(Context ctx){
        ctx.render("/html/EmployeeEdit.html");
    }

    //manager
    public void getManagerRequests(Context ctx){
        ctx.render("/html/ManagerRequests.html");
    }

    public void getManagerHome(Context ctx){
        ctx.render("/html/ManagerHome.html");
    }

    public void getRequestLogin(Context ctx){
        ctx.render("/html/EmployeeLogin.html");
    }
}
