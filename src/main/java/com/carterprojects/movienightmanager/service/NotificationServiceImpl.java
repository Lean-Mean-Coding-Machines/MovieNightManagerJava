package com.carterprojects.movienightmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.admin.email}")
    String adminEmail;

    public void sendEmailNotification() {
        var message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo("carterw8989@hotmail.com");
        message.setSubject("This is a test");
        message.setText("This is a test");
        mailSender.send(message);
    }

}
