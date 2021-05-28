package com.revature.Project2.repository;

import com.revature.Project2.pojo.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findUserByUsername(String username);
}
