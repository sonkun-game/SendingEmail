package com.email.demo.controller;


import com.email.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping(value = "/api/v1/{email}/sendMail")
    @ResponseBody
    public String sendDingEmail(@PathVariable String email,
                                @RequestParam String header,
                                @RequestParam String content) {
        if (email.isEmpty() || header.isEmpty() || content.isEmpty())
            return "Please not leave email, header and content blank !";
        try {
            mailService.sendEmail(email, header, content);
        } catch (MailException me) {
            System.err.println(me.getStackTrace());
            return "Email send failed !";
        }
        return "Email Send success !";
    }
}
