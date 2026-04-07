package com.example.studentworkstudy.repository;

import com.example.studentworkstudy.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentEmailIgnoreCase(String studentEmail);
}
