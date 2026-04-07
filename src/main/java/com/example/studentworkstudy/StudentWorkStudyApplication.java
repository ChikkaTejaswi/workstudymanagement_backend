package com.example.studentworkstudy;

import com.example.studentworkstudy.model.Role;
import com.example.studentworkstudy.model.User;
import com.example.studentworkstudy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StudentWorkStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentWorkStudyApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Force create or update the admin account unconditionally to fix any previous dummy data!
            User admin = userRepository.findByEmail("admin@college.edu").orElse(new User());
            admin.setName("System Admin");
            admin.setEmail("admin@college.edu");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);
            System.out.println("Admin account forcibly synced at startup!");
        };
    }
}
