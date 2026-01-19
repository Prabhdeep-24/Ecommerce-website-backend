package com.example.ecommerce.webhook;

import com.example.ecommerce.Repository.OrderRepo;
import com.example.ecommerce.Models.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class PaymentWebhookController {

    private final OrderRepo orderRepo;

    public PaymentWebhookController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping("/payment/{orderId}")
    public void update(@PathVariable String orderId) {

        Order o = orderRepo.findById(orderId).orElse(null);
        if (o == null) return;

        o.setStatus("PAID");
        orderRepo.save(o);
    }
}
