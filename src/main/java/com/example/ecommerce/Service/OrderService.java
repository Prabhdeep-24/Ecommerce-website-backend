package com.example.ecommerce.Service;

import com.example.ecommerce.Models.*;
import com.example.ecommerce.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepo orderRepo;
    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public OrderService(OrderRepo o, CartRepo c, UserRepo u, ProductRepo p){
        this.orderRepo = o;
        this.cartRepo = c;
        this.userRepo = u;
        this.productRepo = p;
    }

    public Order create(String userId){

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cart = cartRepo.findByUserId(userId);
        if(cart.isEmpty())
            throw new RuntimeException("Cart is empty");

        double total = 0;

        HashMap<String, Products> productMap = new HashMap<>();

        for(CartItem c : cart){

            Products p = productRepo.findById(c.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if(p.getStock() < c.getQuantity())
                throw new RuntimeException("Not enough stock for " + p.getName());

            total += c.getQuantity() * p.getPrice();
            productMap.put(p.getId(), p);
        }

        Order o = new Order();
        o.setUserId(userId);
        o.setTotalAmount(total);
        o.setStatus("CREATED");
        o.setCreatedAt(Instant.now());

        for(CartItem c : cart){
            Products p = productMap.get(c.getProductId());
            p.setStock(p.getStock() - c.getQuantity());
            productRepo.save(p);
        }

        cartRepo.deleteAll(cart);
        return orderRepo.save(o);
    }

    public Order markPaid(String id){

        Order o = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if(o.getStatus().equals("PAID"))
            throw new RuntimeException("Order already paid");

        o.setStatus("PAID");
        return orderRepo.save(o);
    }

    public Order getById(String id){

        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
