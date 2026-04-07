package com.example.studentworkstudy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "work_hours")
public class WorkHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;
    private String date;
    private Double hours;
    private String description;
    
    private String status = "pending"; // pending, approved, rejected

    public WorkHour() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Double getHours() { return hours; }
    public void setHours(Double hours) { this.hours = hours; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
