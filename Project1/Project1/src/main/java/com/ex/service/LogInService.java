package com.ex.service;

import com.ex.dao.MongoDao;
import com.ex.pojos.LogIn;

import java.util.List;

public class LogInService {
    MongoDao dao = new MongoDao();

    /**
     * Method that uses the MongoDao to get a list of users
     * @return a list of employees onboard enterprise
     */
    public List<LogIn> getLogIns(){
        List<LogIn> list = dao.listLogIns();
        return list;
    }

    /**
     * checkLogIn for LogInService checks the login of the employee
     * @param username user's name
     * @param password user's password
     * @return boolean flag
     */
    public String checkLogIn(String username, String password){
        LogIn log = dao.checkLogIn(username);
        String flag = null;
        if(log != null){
            if(password.equals(log.getPassword())){
                flag = log.getId();
            }
        }
        else{
            System.out.println("Null object");
        }
        return flag;
    }

    /**
     * Checks if the password the same as the last
     * @param id
     * @param password
     * @param newPassword
     * @return boolean flag
     */
    public boolean changePassword(String id, String password, String newPassword){
        boolean flag = false;

        if(password.equals(newPassword)) {
            flag = true;
            dao.changePassword(id, password);
            System.out.println("password was changed to " + password);
        }
        else {
            System.out.println("No password change");
        }
        return flag;
    }
}
