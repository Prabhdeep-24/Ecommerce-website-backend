package com.example.ecommerce.Service;

import com.example.ecommerce.Models.CartItem;
import com.example.ecommerce.Models.Products;
import com.example.ecommerce.Models.User;
import com.example.ecommerce.Repository.CartRepo;
import com.example.ecommerce.Repository.ProductRepo;
import com.example.ecommerce.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public CartService(CartRepo c, UserRepo u, ProductRepo p){
        this.cartRepo = c;
        this.userRepo = u;
        this.productRepo = p;
    }

    // ADD TO CART
    public CartItem addToCart(CartItem item){

        User user = userRepo.findById(item.getUserId()).orElse(null);
        if(user == null)
            throw new RuntimeException("User not found");

        Products product = productRepo.findById(item.getProductId()).orElse(null);
        if(product == null)
            throw new RuntimeException("Product not found");

        if(item.getQuantity() <= 0)
            throw new RuntimeException("Quantity must be > 0");

        if(product.getStock() < item.getQuantity())
            throw new RuntimeException("Not enough stock");

        return cartRepo.save(item);
    }

    // GET USER CART
    public List<CartItem> getUserCart(String userId){
        User user = userRepo.findById(userId).orElse(null);
        if(user == null)
            throw new RuntimeException("User not found");

        return cartRepo.findByUserId(userId);
    }

    // CLEAR CART
    public void clearCart(String userId){
        cartRepo.deleteAll(cartRepo.findByUserId(userId));
    }
}
