package com.example.studentworkstudy.repository;

import com.example.studentworkstudy.model.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkHourRepository extends JpaRepository<WorkHour, Long> {
    List<WorkHour> findByStudentEmailIgnoreCase(String studentEmail);
}
