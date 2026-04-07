package com.example.studentworkstudy.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("chikkatejaswi8@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Registration Successful - Student Work Study System");
        message.setText("Dear " + name + ",\n\n" +
                "Welcome to the Student Work Study System! You have successfully registered.\n\n" +
                "You can now login and start applying for jobs or tracking your work hours.\n\n" +
                "Best regards,\nStudent Work Study Team");
        
        mailSender.send(message);
    }
}
