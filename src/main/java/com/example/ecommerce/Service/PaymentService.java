package com.example.ecommerce.Service;

import com.example.ecommerce.Models.Payments;
import com.example.ecommerce.Repository.PaymentRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepo repo;
    private final RazorpayClient razorpay;

    public PaymentService(PaymentRepo r, RazorpayClient rc){
        this.repo = r;
        this.razorpay = rc;
    }

    // CREATE RAZORPAY ORDER
    public Payments createPayment(Payments p) throws Exception {

        if(p.getAmount() <= 0)
            throw new RuntimeException("Invalid amount");

        JSONObject options = new JSONObject();
        options.put("amount", p.getAmount());
        options.put("currency", "INR");
        options.put("receipt", p.getOrderId());

        // IMPORTANT FIX HERE 👇
        Order order = razorpay.orders.create(options);

        p.setRazorpayOrderId(order.get("id").toString());
        p.setStatus("CREATED");

        return repo.save(p);
    }

    // PAYMENT SUCCESS CALLBACK
    public Payments paymentSuccess(String orderId){

        Payments p = repo.findByOrderId(orderId);

        if(p == null)
            throw new RuntimeException("Payment not found");

        p.setStatus("SUCCESS");
        return repo.save(p);
    }

    public Payments getByOrderId(String orderId){
        Payments p = repo.findByOrderId(orderId);

        if(p == null)
            throw new RuntimeException("Payment not found");

        return p;
    }

}
