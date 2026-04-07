package com.example.studentworkstudy.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String department;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String location;
    private String hoursPerWeek;
    private String pay;
    private String deadline;

    @ElementCollection
    private List<String> requirements;

    private String status = "open"; // open, closed

    public Job() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getHoursPerWeek() { return hoursPerWeek; }
    public void setHoursPerWeek(String hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }
    public String getPay() { return pay; }
    public void setPay(String pay) { this.pay = pay; }
    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public List<String> getRequirements() { return requirements; }
    public void setRequirements(List<String> requirements) { this.requirements = requirements; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
