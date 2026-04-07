package com.example.studentworkstudy.controller;

import com.example.studentworkstudy.model.Job;
import com.example.studentworkstudy.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        if (job.getStatus() == null) {
            job.setStatus("open");
        }
        return ResponseEntity.ok(jobRepository.save(job));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        return jobRepository.findById(id).map(job -> {
            if(jobDetails.getTitle() != null) job.setTitle(jobDetails.getTitle());
            if(jobDetails.getDepartment() != null) job.setDepartment(jobDetails.getDepartment());
            if(jobDetails.getDescription() != null) job.setDescription(jobDetails.getDescription());
            if(jobDetails.getLocation() != null) job.setLocation(jobDetails.getLocation());
            if(jobDetails.getHoursPerWeek() != null) job.setHoursPerWeek(jobDetails.getHoursPerWeek());
            if(jobDetails.getPay() != null) job.setPay(jobDetails.getPay());
            if(jobDetails.getDeadline() != null) job.setDeadline(jobDetails.getDeadline());
            if(jobDetails.getRequirements() != null) job.setRequirements(jobDetails.getRequirements());
            if(jobDetails.getStatus() != null) job.setStatus(jobDetails.getStatus());
            
            return ResponseEntity.ok(jobRepository.save(job));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
