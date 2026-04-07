package com.example.studentworkstudy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;
    private Long jobId;

    // Additional fields for admin details
    private String studentName;
    private String studentAge;
    private String studentPhone;
    private String studentUniversity;
    private String jobTitle;
    
    @Column(columnDefinition = "TEXT")
    private String note;
    
    @Column(columnDefinition = "LONGTEXT")
    private String resume;
    
    private LocalDateTime appliedAt = LocalDateTime.now();
    
    private String status = "pending";

    public Application() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getStudentAge() { return studentAge; }
    public void setStudentAge(String studentAge) { this.studentAge = studentAge; }
    public String getStudentPhone() { return studentPhone; }
    public void setStudentPhone(String studentPhone) { this.studentPhone = studentPhone; }
    public String getStudentUniversity() { return studentUniversity; }
    public void setStudentUniversity(String studentUniversity) { this.studentUniversity = studentUniversity; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
