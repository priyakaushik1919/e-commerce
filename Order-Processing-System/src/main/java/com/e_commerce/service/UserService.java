package com.e_commerce.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.User;
import com.e_commerce.repository.UserRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        // Initialize an admin user for demonstration if one does not exist.
        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

	public User getUserId(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
    
        if (userOptional.isEmpty()) {
            System.out.println("❌ No user found for username: " + username);
            return null;
        }
    
        User user = userOptional.get();
        System.out.println("✅ User found in DB: " + user.getUsername());
    
        // Check password
        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());
        System.out.println("🔍 Password match result: " + passwordMatch);
    
        if (passwordMatch) {
            return user;
        } else {
            System.out.println("❌ Password does not match for user: " + username);
            return null;
        }
    }
    
    
    
}

