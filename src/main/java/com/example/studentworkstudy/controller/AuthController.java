package com.example.studentworkstudy.controller;

import com.example.studentworkstudy.dto.AuthRequest;
import com.example.studentworkstudy.dto.AuthResponse;
import com.example.studentworkstudy.dto.RegisterRequest;
import com.example.studentworkstudy.model.Role;
import com.example.studentworkstudy.model.User;
import com.example.studentworkstudy.repository.UserRepository;
import com.example.studentworkstudy.security.CustomUserDetails;
import com.example.studentworkstudy.security.JwtUtil;
import com.example.studentworkstudy.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          PasswordEncoder passwordEncoder, JwtUtil jwtUtil, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already taken!");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        Role role = Role.ROLE_STUDENT;
        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            role = Role.ROLE_ADMIN;
        }
        user.setRole(role);

        userRepository.save(user);
        
        if (role == Role.ROLE_STUDENT) {
            // Send Registration Email
            try {
                emailService.sendRegistrationEmail(user.getEmail(), user.getName());
            } catch (Exception e) {
                System.out.println("Failed to send email to " + user.getEmail() + ": " + e.getMessage());
            }
        }

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail().toLowerCase(), request.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        User user = userDetails.getUser();

        return ResponseEntity.ok(new AuthResponse(jwt, user.getName(), user.getEmail(), user.getRole().name()));
    }
}
