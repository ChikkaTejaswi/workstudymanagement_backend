package com.example.studentworkstudy.controller;

import com.example.studentworkstudy.model.Application;
import com.example.studentworkstudy.repository.ApplicationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationRepository.findAll());
    }

    @GetMapping("/student/{email}")
    public ResponseEntity<List<Application>> getStudentApplications(@PathVariable String email) {
        return ResponseEntity.ok(applicationRepository.findByStudentEmailIgnoreCase(email));
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        if (application.getStatus() == null) {
            application.setStatus("pending");
        }
        return ResponseEntity.ok(applicationRepository.save(application));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(@PathVariable Long id, @RequestBody Application statusUpdate) {
        return applicationRepository.findById(id).map(app -> {
            app.setStatus(statusUpdate.getStatus());
            return ResponseEntity.ok(applicationRepository.save(app));
        }).orElse(ResponseEntity.notFound().build());
    }
}
