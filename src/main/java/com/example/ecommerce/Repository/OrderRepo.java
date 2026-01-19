package com.example.ecommerce.Repository;

import com.example.ecommerce.Models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
}
