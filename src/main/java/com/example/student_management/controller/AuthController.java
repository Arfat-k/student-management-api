package com.example.student_management.controller;

import com.example.student_management.model.user;
import com.example.student_management.repository.UserRepository;
import com.example.student_management.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // Register
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody user user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Username already exists");
            return ResponseEntity.badRequest().body(error);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody user user) {
        user existing = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid password");
            return ResponseEntity.badRequest().body(error);
        }

        String token = jwtUtil.generateToken(existing.getUsername(), existing.getRole());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", existing.getRole());
        return ResponseEntity.ok(response);
    }
}