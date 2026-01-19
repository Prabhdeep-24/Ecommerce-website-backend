package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.CartItem;
import com.example.ecommerce.Models.Order;
import com.example.ecommerce.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }

    // CREATE ORDER
    @PostMapping("/create/{userId}")
    public Order createOrder(@PathVariable String userId){
        return service.create(userId);
    }

    // MARK PAID
    @PutMapping("/pay/{orderId}")
    public Order markPaid(@PathVariable String orderId){
        return service.markPaid(orderId);
    }

    // GET ORDER
    @GetMapping("/{orderId}")
    public Order get(@PathVariable String orderId){
        return service.getById(orderId);
    }
}
