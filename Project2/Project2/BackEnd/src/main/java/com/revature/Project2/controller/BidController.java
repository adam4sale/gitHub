package com.revature.Project2.controller;

import com.revature.Project2.pojo.Bid;
import com.revature.Project2.repository.BidRepository;
import com.revature.Project2.service.BidService;
import com.revature.Project2.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bid")
@Log4j2
public class BidController {
    //vars
    @Autowired
    BidRepository repo;
    @Autowired
    BidService service;


    //methods

    /**
     * Uses the MongoRepository findAll()
     * @return List<Bid>
     */
    @GetMapping("/bids")
    public List<Bid> getBids(){
        log.info("Finding all bids in controller");
        List<Bid> list = repo.findAll();
        return list;
    }

    /**
     * Get bids of an owner by item name
     * @param owner
     * @param item
     * @return ArrayList of bids
     */
    @GetMapping("/bids/{owner}/{itemName}")
    public ArrayList<Bid> getItemBids(@PathVariable("owner") String owner, @PathVariable("itemName") String item){
        log.info("finding bids by item in controller");
        ArrayList<Bid> bids = service.findBidByItem(owner, item);
        return bids;
    }

    /**
     * Finds bids by document var username
     * @param username
     * @return ArrayList of bids
     */
    @GetMapping("/bids/{username}")
    public ArrayList<Bid> getBidsById(@PathVariable("username") String username){
        log.info("finding bids by ID in controller");
        ArrayList result =service.findBidByOwner(username);
        System.out.println(result);
        return result;
    }

    /**
     * Finds all bids with an "accept" status
     * @param username
     * @return ArrayList of bids
     */
    @GetMapping("/accepted/{username}/{query}")
    public ArrayList<Bid> getAcceptedBids(@PathVariable("username") String username,@PathVariable("query")String query){
        ArrayList result =service.findAcceptedBidsByBidder(username, "accept", query);
        System.out.println(result);
        return result;
    }

    /**
     * posts a bid to the Database
     * @param bid
     * @return boolean value
     */
    @PostMapping(value ="/post_bid")
    public boolean postBid(@RequestBody Bid bid){
        log.info("request to post a bid in controller");
        return service.postBid(bid);
    }

    /**
     * Updates a bids status
     * @param id
     * @param status
     * @return String status
     */
    @PostMapping(value="/bid_status/{value}")
    public String bidStatus(@RequestBody String id, @PathVariable("value") String status){
        log.info("setting status of bid in controller");
        return service.setBidStatus(id, status); }}