package com.revature.Project2.controller;

import com.revature.Project2.pojo.Item;
import com.revature.Project2.pojo.User;
import com.revature.Project2.repository.ItemRepository;
import com.revature.Project2.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the controller for the Items that are
 * placed for sale by users.
 *
 */
@RestController
@RequestMapping("/item")
@Log4j2
public class ItemController {
    //vars
    @Autowired
    ItemService service;
    @Autowired
    ItemRepository repo;

    /**
     * This controller handles when a user adds a new item to sell.
     * @param item this is the object of an item to be sold
     * @return returns a status of 200 if it is successful
     */
    @PostMapping("/addItem")
    public String addItem(@RequestBody Item item) {
        //item
        log.info("adding item in controller");
        String status = "error";
        item.setAccepted(false);
        if(service.insertItem(item)){status = "200";}
        return status;
    }
    
    /**
     * Users the ItemService to get a List of
     * Items by the owner of the items
     * @param owner
     * @return List of Items
     */
    @GetMapping("/owner_items/{owner}")
    public List<Item> getOwnerItems(@PathVariable("owner") String owner){
        log.info("getting owners items in controller");
        return service.findOwnerItems(owner);
    }

    /**
     * Uses the ItemService to get an Item by
     * the owner and title of the object
     * @param owner
     * @param title
     * @return Item
     */
    @GetMapping("/owner_items/{owner}/{title}")
    public Item getOwnerItem(@PathVariable("owner") String owner,
                             @PathVariable("title") String title){
        log.info("finding the owner of an item in controller");
        return service.findOwnerItem(owner, title);
    }

    /**
     * This method controls the browsing using a query
     * @param query this is the query that the user uses to search for items
     * @param username this is the username of the user querying, this is used to
     *                 ensure the user's own items are not displayed
     * @return an arrraylist of items are returned to the user
     */
    @GetMapping("/browse/{query}/{username}")
    public ArrayList<Item> browseItems(@PathVariable("query") String query, @PathVariable("username") String username){
        log.info("searching for items in controller");
        ArrayList data= new ArrayList();
        try{ data= service.findItems(query, username); } catch (Exception e){ e.printStackTrace();
        }return data; }

    /**
     * This method controlls the handling of returning all accepted items of a user
     * @param owner  the user who's bid has been accepted
     * @return this returns a list of items that has been accepted
     */
    @GetMapping("/items/accepted/{owner}")
    public List<Item> getAcceptedItems(@PathVariable("owner") String owner){
        log.info("getting accepted items in controller");
        return service.findAcceptedOwnerItems(owner); }

}
