package com.email.demo.controller;


import com.email.demo.model.MailAttachment;
import com.email.demo.model.MailMessage;
import com.email.demo.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping(value = "/api/v1/{email}/sendMail")
    @ResponseBody
    public String sendDingEmail(@PathVariable String email,
                                @RequestParam String header,
                                @RequestParam String content) {
        if (email.isEmpty() || header.isEmpty() || content.isEmpty())
            return "Please not leave email, header and content blank !";
        MailMessage mail = new MailMessage();
        mail.setMsg_to(email);
        mail.setSubject(header);
        mail.setText(content);
        try {
            mailService.sendEmail(mail);
        } catch (MailException me) {
            System.err.println(me.getStackTrace());
            return "Email send failed !";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Email Send success !";
    }


    @GetMapping(value = "/api/v1/{email}/sendDingEmailWithAttached")
    @ResponseBody
    public String sendDingEmailWithAttached(@PathVariable String email,
                                            @RequestParam String header,
                                            @RequestParam String content) {
        MailMessage mail = new MailMessage();
        MailAttachment mailAttachment = new MailAttachment();

        mail.setMsg_to(email);
        mail.setSubject(header);
        mail.setText(content);

        if (email.isEmpty() || header.isEmpty() || content.isEmpty())
            return "Please not leave email, header and content blank !";
        try {
            mailService.sendEmailWithAttachFile(mail, mailAttachment);
        } catch (Exception me) {
            System.err.println(me);
            return "Email send failed !";
        }
        return "Email Send With Attached file success !";
    }
}
