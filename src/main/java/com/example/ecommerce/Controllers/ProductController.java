package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Products;
import com.example.ecommerce.Repository.ProductRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepo repo;

    public ProductController(ProductRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public Products add(@RequestBody Products product) {
        return repo.save(product);
    }

    @GetMapping
    public List<Products> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Products getById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }
}
