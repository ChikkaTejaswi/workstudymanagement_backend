package com.example.studentworkstudy.controller;

import com.example.studentworkstudy.model.WorkHour;
import com.example.studentworkstudy.repository.WorkHourRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workhours")
public class WorkHourController {

    private final WorkHourRepository workHourRepository;

    public WorkHourController(WorkHourRepository workHourRepository) {
        this.workHourRepository = workHourRepository;
    }

    @GetMapping
    public ResponseEntity<List<WorkHour>> getAllWorkHours() {
        return ResponseEntity.ok(workHourRepository.findAll());
    }

    @GetMapping("/student/{email}")
    public ResponseEntity<List<WorkHour>> getStudentWorkHours(@PathVariable String email) {
        return ResponseEntity.ok(workHourRepository.findByStudentEmailIgnoreCase(email));
    }

    @PostMapping
    public ResponseEntity<WorkHour> submitWorkHour(@RequestBody WorkHour workHour) {
        if (workHour.getStatus() == null) {
            workHour.setStatus("pending");
        }
        return ResponseEntity.ok(workHourRepository.save(workHour));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<WorkHour> updateStatus(@PathVariable Long id, @RequestBody WorkHour statusUpdate) {
        return workHourRepository.findById(id).map(wh -> {
            wh.setStatus(statusUpdate.getStatus());
            return ResponseEntity.ok(workHourRepository.save(wh));
        }).orElse(ResponseEntity.notFound().build());
    }
}
