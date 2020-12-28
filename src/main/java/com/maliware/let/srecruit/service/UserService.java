package com.maliware.let.srecruit.service;

import com.maliware.let.srecruit.model.User;
import com.maliware.let.srecruit.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> displayUsers(){
       return this.userRepo.findAll();
    }
}
