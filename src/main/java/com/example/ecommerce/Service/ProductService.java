package com.example.ecommerce.Service;

import com.example.ecommerce.Models.Products;
import com.example.ecommerce.Repository.ProductRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Products> getAll(){
        return repo.findAll();
    }

    public Products add(Products p){
        return repo.save(p);
    }
}
