package com.example.studentworkstudy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;
    
    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime givenAt = LocalDateTime.now();

    public Feedback() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public LocalDateTime getGivenAt() { return givenAt; }
    public void setGivenAt(LocalDateTime givenAt) { this.givenAt = givenAt; }
}
