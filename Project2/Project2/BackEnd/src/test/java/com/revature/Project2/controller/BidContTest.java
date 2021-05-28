package com.revature.Project2.controller;

import com.revature.Project2.pojo.Bid;
import com.revature.Project2.pojo.Item;
import com.revature.Project2.repository.BidRepository;
import com.revature.Project2.repository.ItemRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class BidContTest {

    @Autowired
    BidController controller;
    @Autowired
    BidRepository repository;
    @Autowired
    ItemRepository itemRepo;

    @Test
    public void getBidsTest(){
        //getBids()

        List<Bid> bids = controller.getBids();
        Assert.noNullElements(bids, "not null");
    }

    @Test
    public void getItemBidsTest(){
       ArrayList<Bid> list = controller.getItemBids("owner", "item");
       Assert.noNullElements(list, "No elements should be null");
    }

    @Test
    public void getBidsByIdTest(){
        //getBidsById()

        List<Bid> bids = controller.getBidsById("adam");
        Assert.noNullElements(bids, "Adam has bids");
    }

    @Test
    public void getAcceptedBidsTest(){
        ArrayList<Bid> list = controller.getAcceptedBids("username", "*");
        Assert.noNullElements(list, "No null elements");
    }
    @Test
    public void postBidTest(){
        Date testDate = new Date();
        Bid bid = new Bid(null, "owner","item", "bidder", 0, testDate, "pending");
        boolean flag = controller.postBid(bid);
//        Assert.isTrue(flag, "flag is true");
    }

    @Test
    public void bidStatusTest(){
        try{
        Item item = new Item(null, "item", 0, "owner", 0, "none", false);
        itemRepo.insert(item);
        Bid bid = new Bid("69", "item", "owner", "bidder", 0, new Date(), "pending");
        boolean post = controller.postBid(bid);
        repository.insert(bid);
        String stat = controller.bidStatus("0", "accept");
        Assert.isTrue(stat.equalsIgnoreCase("accept" ), "Strings should be equal");
        repository.delete(bid);
        itemRepo.delete(item);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
