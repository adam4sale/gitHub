package com.revature.Project2.repository;

import com.revature.Project2.pojo.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {
    List<Item> findItemByOwner(String owner);
    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    ArrayList<Item> findByTitle(String query);
}
