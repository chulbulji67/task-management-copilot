package com.task.task_management.controller;

import com.task.task_management.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-email")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String testEmail() {
        emailService.sendEmail(
                "chulbulji67@gmail.com",
                "Test Email",
                "This is a test email from the Task Management App."
        );
        return "Test email sent!";
    }
}

