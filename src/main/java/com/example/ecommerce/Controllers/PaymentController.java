package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Payments;
import com.example.ecommerce.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // CREATE PAYMENT
    @PostMapping("/create")
    public Payments create(@RequestBody Payments p) throws Exception {
        return service.createPayment(p);
    }

    // PAYMENT SUCCESS
    @PostMapping("/success/{orderId}")
    public Payments success(@PathVariable String orderId) {
        return service.paymentSuccess(orderId);
    }

    // GET PAYMENT BY ORDER ID
    @GetMapping("/order/{orderId}")
    public Payments get(@PathVariable String orderId) {
        return service.getByOrderId(orderId);
    }
}
