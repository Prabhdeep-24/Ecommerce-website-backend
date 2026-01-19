package com.example.ecommerce.Repository;

import com.example.ecommerce.Models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Products, String> {
}
