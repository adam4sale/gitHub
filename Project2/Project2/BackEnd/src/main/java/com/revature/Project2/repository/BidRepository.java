package com.revature.Project2.repository;

import com.revature.Project2.pojo.Bid;
import com.revature.Project2.pojo.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BidRepository extends MongoRepository<Bid, ObjectId> {
    ArrayList<Bid> findBidByOwner(String owner);
    ArrayList<Bid> findBidByItem(String item);
    Bid findBidById(String id);
    ArrayList<Bid> findBidsByBidderAndStatusEquals(String username, String status);
//    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    ArrayList<Bid> findBidsByBidderAndStatusEqualsAndItemIsLike(String username, String status, String query);
}