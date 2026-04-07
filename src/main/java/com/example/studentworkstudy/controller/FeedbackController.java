package com.example.studentworkstudy.controller;

import com.example.studentworkstudy.model.Feedback;
import com.example.studentworkstudy.repository.FeedbackRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return ResponseEntity.ok(feedbackRepository.findAll());
    }

    @GetMapping("/student/{email}")
    public ResponseEntity<List<Feedback>> getStudentFeedback(@PathVariable String email) {
        return ResponseEntity.ok(feedbackRepository.findByStudentEmailIgnoreCase(email));
    }

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackRepository.save(feedback));
    }
}
