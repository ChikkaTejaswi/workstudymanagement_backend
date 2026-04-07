package com.example.studentworkstudy.repository;

import com.example.studentworkstudy.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
