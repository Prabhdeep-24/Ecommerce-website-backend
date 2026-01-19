package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.CartItem;
import com.example.ecommerce.Service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public CartItem add(@RequestBody CartItem item) {
        return service.addToCart(item);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getUserCart(@PathVariable String userId) {
        return service.getUserCart(userId);
    }

    @DeleteMapping("/{userId}")
    public void clear(@PathVariable String userId) {
        service.clearCart(userId);
    }
}
