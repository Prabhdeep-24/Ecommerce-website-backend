package com.example.ecommerce.Repository;

import com.example.ecommerce.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail(String email);
}
