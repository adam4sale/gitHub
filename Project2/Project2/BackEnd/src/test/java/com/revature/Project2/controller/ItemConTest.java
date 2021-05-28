package com.revature.Project2.controller;

import com.revature.Project2.pojo.Item;
import com.revature.Project2.repository.ItemRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//need this to test @BeforeAll annotation
public class ItemConTest {
    @Autowired
    ItemController controller;

    @Autowired
    ItemRepository repository;

    /**
     * postItemTest() will run first because of
     * annotation @BeforeAll
     */

    @BeforeAll
    public void postItemTest(){
//        private ObjectId id;
//        private String title;
//        private double price;
//        private String owner;
//        private int increment;
//        private String image;

        Item item = new Item(null, "title", 0.0, "owner", 0, null, false );
        String flag = controller.addItem(item);
        Assert.isTrue(flag.equalsIgnoreCase("200"), "Return 200, not error");
    }

    @Test
    public void getOwnerItemTest(){
        Item item = controller.getOwnerItem("owner", "title");
        Assert.notNull(item, "Item isn't null");
    }

    /**
     * Test needs a value in the test db with the owner: "owner"
     */
    @Test
    public void getOwnerItemsTest(){
        List<Item> list = controller.getOwnerItems("owner");
        Assert.notEmpty(list, "list isn't empty");
    }
//
    @Test
    public void browseItemsTest() {
        try {
            Item item = new Item(null, "item", 0, "owner",
                    0, "none", false);
            repository.insert(item);
            ArrayList<Item> list = controller.
                    browseItems("item", "owner");
            Assert.noNullElements(list, "No null elements");
            repository.delete(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAcceptedItemsTest(){
        try {
            Item item = new Item(null, "item", 0, "owner",
                    0, "none", true);
            repository.insert(item);
            List<Item> list = controller.getAcceptedItems(item.getOwner());
            Assert.noNullElements(list, "No null element");
            repository.delete(item);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


