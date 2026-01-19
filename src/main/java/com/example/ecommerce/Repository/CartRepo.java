package com.example.ecommerce.Repository;

import com.example.ecommerce.Models.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CartRepo extends MongoRepository<CartItem, String> {

    List<CartItem> findByUserId(String userId);
}
