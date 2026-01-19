package com.example.ecommerce.Repository;

import com.example.ecommerce.Models.Payments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepo extends MongoRepository<Payments, String> {
    Payments findByOrderId(String orderId);
}
