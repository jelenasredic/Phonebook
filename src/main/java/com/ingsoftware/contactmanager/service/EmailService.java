package com.ingsoftware.contactmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {
    private JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendConfirmationEmail(String recipient) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("boskovicmihailo2107@gmail.com7");
        message.setTo(recipient);
        message.setSubject("Welcome");
        message.setText("Registration is successful.");
        emailSender.send(message);
    }
}

