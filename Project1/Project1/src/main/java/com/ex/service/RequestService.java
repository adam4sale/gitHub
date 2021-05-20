package com.ex.service;

import com.ex.dao.MongoDao;
import com.ex.pojos.Request;
import com.ex.pojos.User;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class RequestService {
    //Database object
    MongoDao dao = new MongoDao();


    /**
     * insertRequest(...) inserts a full request of a user
     * @param name
     * @param body
     * @param amount
     * @param date
     * @param approved
     */
    public void insertRequest(String name, String body, String amount, Date date, String approved){
        Request request = new Request(amount, body, name, date, approved);
        dao.postRequest(request);
    }

    /**
     * getAllRequests() should return all the unapproved requests
     * of all the users in
     * @return list of all requests
     */
    public List<Request> getAllRequests(){

        List<Request> list = dao.listRequests();
        //remove all requests where isApproved() is true
        Iterator<Request> i = list.iterator();
        try{
            while(i.hasNext()){
                Request rq = i.next();
                if(rq.isApproved().equals("approved") || rq.isApproved().equals("declined")){
                    i.remove();
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    /**
     * getYourRequests(String name) should filter all the
     * request of a single user
     * @param name
     * @return list of user requests
     */
    public List<Request> getYourRequests(String name) {

        List<Request> list = dao.listRequests();
        //remove all requests where isApproved() is true
        Iterator<Request> i = list.iterator();
        try {
            while (i.hasNext()) {
                Request rq = i.next();
                if (!rq.getName().equals(name)) {
                    i.remove();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public void validateRequest(String value){
        try{
            String approval = null;
            //Scanner scan = new Scanner(value);
            char sub = value.charAt(0);
            String body = value.substring(2, value.length());
            body = body.substring(1, body.length() - 1);
            System.out.println(body);
            if(sub == 'a'){
                approval = "approved";
            }
            else if(sub == 'd'){
                approval = "declined";
            }
            if(approval != null){
                dao.changeApproval(body,  approval);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
