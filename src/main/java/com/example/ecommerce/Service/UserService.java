package com.example.ecommerce.Service;

import com.example.ecommerce.Models.User;
import com.example.ecommerce.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User create(User u){
        System.out.println((u.getEmail()));
        if(repo.findByEmail(u.getEmail()) != null){
            throw new RuntimeException("Email already exists");
        }
        return repo.save(u);
    }

    public List<User> getAll(){
        return repo.findAll();
    }

    public User getById(String id){
        return repo.findById(id).orElse(null);
    }
}
