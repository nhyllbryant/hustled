package com.example.hustled.service;

import com.example.hustled.entity.User;
import com.example.hustled.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public boolean register(User user){
        if (userRepo.existsByUsername(user.getUsername())) {
            return false;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");

        User newUser = null;
        try{
            newUser = userRepo.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
        System.out.println("New User: " + (newUser != null ? newUser.getId() : null) + " | " + (newUser != null ? newUser.getUsername() : null) + " | " + (newUser != null ? newUser.getEmail() : null) + " | " + (newUser != null ? newUser.getPhone() : null));

        return true;
    }
}
