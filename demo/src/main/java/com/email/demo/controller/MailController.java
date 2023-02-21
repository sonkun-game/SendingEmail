package com.email.demo.controller;


import com.email.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping(value = "/sendMail")
    public String testGmail(){
        mailService.sendEmail(
                "mikechael74@gmail.com",
                "This is the header of the mail",
                "This is the content of the mail !!");
        return "Send success";
    }

    @GetMapping(value = "/")
    public String index() {
        return "home !";
    }

    @GetMapping(value = "/users/{id}")
    public String users() {
        return "home !";
    }
}
