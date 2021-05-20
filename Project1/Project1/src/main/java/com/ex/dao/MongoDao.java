package com.ex.dao;

import com.ex.pojos.LogIn;
import com.ex.pojos.Request;
import com.ex.pojos.User;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import static com.mongodb.client.model.Filters.eq;


public class MongoDao {
    //vars
    MongoCollection<User> userCollection;
    MongoCollection<LogIn> logCollection;
    MongoCollection<Request> reqCollection;
    //log4j logger
    org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

        //Constructor
        public MongoDao() {
            //mongodb connection
            ConnectionString connect = new ConnectionString("mongodb://localhost:27017/enterprise");
            CodecProvider codec = PojoCodecProvider.builder().register("com.ex.pojos").build();
            CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings
                    .getDefaultCodecRegistry(), CodecRegistries.fromProviders(codec));
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connect)
                    .retryWrites(true)
                    .codecRegistry(registry)//codecs convert between bson and java
                    .build();
            MongoClient client = MongoClients.create(settings);
            MongoDatabase database = client.getDatabase("enterprise");

            //Collections
            this.userCollection =
                    database.getCollection("employees", User.class);
            this.logCollection = database.getCollection("logIn", LogIn.class);
            this.reqCollection = database.getCollection("request", Request.class);

        }

        //User methods

    /**
     * Method listUsers will find a list of all users from the db
     * @return a list of all users onboard enterprise
     */
    public List<User> listUsers() {
            List<User> list = new ArrayList<>();
            try {
                list = userCollection.find().into(new ArrayList<>());
            } catch (Exception e) {
                rootLogger.info(e.getMessage());
                System.out.println(e.getMessage());
            }
            return list;
        }

    public User findUserById(String id){
        User user = new User();
        try{
            user = this.userCollection.find()
                    .filter(eq("_id", new String(id))).first();

        }
        catch (Exception e){
            rootLogger.info(e.getMessage());
        }
        return user;
    }

        //LogIn methods

    public List<LogIn> listLogIns() {
        List<LogIn> list = new ArrayList<>();
        try {
            list = logCollection.find().into(new ArrayList<>());
        } catch (Exception e) {
            rootLogger.info(e.getMessage());
            System.out.println(e.getMessage());
        }
        return list;
    }

    public LogIn checkLogIn(String username){
        LogIn log = new LogIn();
        try{
            log = this.logCollection.find()
                    .filter(eq("username", new String(username))).first();
        }
        catch (Exception e){
            rootLogger.info(e.getMessage());
        }
        return log;
    }

    //Requests

    /**
     *Grab a list of requests from the MongoDB
     * @return list
     */
    public List<Request> listRequests(){
        List<Request> list = new ArrayList<>();
        try{
            list = reqCollection.find().into(new ArrayList<>());
            rootLogger.info("You got a list");
        }
        catch(Exception e){
            rootLogger.info(e.getMessage());
        }
        return list;
    }

    public void changePassword(String id, String newPassword){
        LogIn log = logCollection.find().filter(eq("_id", new String(id))).first();
        log.setPassword(newPassword);
        logCollection.deleteOne(eq("_id", id));
        logCollection.insertOne(log);
    }

    /**
     * Posts a employee request for worker compensation
     * to the Mongo database
     * @param req
     */
    public void postRequest(Request req){
        try{
        reqCollection.insertOne(req);
        rootLogger.info("A request was posted by "+ req.getName());//should work...
        }
        catch(Exception e){
            rootLogger.info(e.getMessage());
        }
    }

    public void changeApproval(String body, String approval){
        try{
            Request request = reqCollection.find().filter(eq("body", new String(body))).first();
            request.setApproved(approval);
            reqCollection.deleteOne(eq("body", body));
            reqCollection.insertOne(request);
        }
        catch (Exception e){
            rootLogger.info(e.getMessage());
        }
    }

}
