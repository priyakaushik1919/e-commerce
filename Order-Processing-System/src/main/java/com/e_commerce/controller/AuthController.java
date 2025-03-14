package com.e_commerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.dto.AuthRequest;
import com.e_commerce.entity.User;
import com.e_commerce.repository.UserRepository;
import com.e_commerce.security.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;


    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        User user = userOpt.get();
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());

        return ResponseEntity.ok(token);
    }

    // @PostMapping("/register")
    // public ResponseEntity<?> register(@RequestBody User user) {
    //     if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
    //         return ResponseEntity.badRequest().body("Password is required.");
    //     }
    //     User savedUser = userService.save(user);
    //     return ResponseEntity.ok(savedUser);
    // }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
    // Check if the username already exists
    Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

    if (existingUser.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
    }

    // Check if the password is provided
    if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
        return ResponseEntity.badRequest().body("Password is required.");
    }

    // Encode and save user
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    
    return ResponseEntity.ok(savedUser);
}



//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody User user) {
//     // user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password before saving
//     userService.save(user);
//     return ResponseEntity.ok("User registered successfully");
// }

}
