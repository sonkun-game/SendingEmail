package com.email.demo.service;

import com.email.demo.model.CommonPath;
import com.email.demo.model.MailAttachment;
import com.email.demo.model.MailMessage;
import com.email.demo.repository.MailAttachedResponsitory;
import com.email.demo.repository.MailMessageRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@Service
public class MailService {
    private JavaMailSender javaMailSender;
    private MailMessageRepository mailRepo;
    private MailAttachedResponsitory mailAttachRepo;
    private CommonPath cp;

    @Autowired
    public MailService(JavaMailSender javaMailSender, MailMessageRepository mailRepo, MailAttachedResponsitory mailAttachRepo, CommonPath cp) {
        this.javaMailSender = javaMailSender;
        this.mailRepo = mailRepo;
        this.mailAttachRepo = mailAttachRepo;
        this.cp = cp;
    }

    public void sendEmail(MailMessage mail) throws MailException, MessagingException {

        mailRepo.save(mail);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper msg = new MimeMessageHelper(message, true);
        msg.setTo(mail.getMsg_to());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getText(),true);

        javaMailSender.send(msg.getMimeMessage());
    }

    public void sendEmailWithAttachFile(MailMessage mail, MailAttachment mailAttachment) throws MailException, MessagingException, IOException, NoSuchAlgorithmException {

        // for example this the file path in your PC name demo.txt
        String test_file_path = "D:\\Demo\\demo.txt";

        MailAttachment ma = cp.copyAttachedFileToLocal(test_file_path);
        mailAttachment.setFile_path(ma.getFile_path());
        mailAttachment.setFile_ext(ma.getFile_ext());
        mailAttachment.setFile_name(ma.getFile_name());

        // save mail to database
        mailRepo.save(mail);
        mailAttachment.setMail_id(String.valueOf(mail.getId()));
        mailAttachRepo.save(mailAttachment);

        // start sending email
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper msg = new MimeMessageHelper(message, true);
        msg.setTo(mail.getMsg_to());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getText(),true);
        // attach file
        String test = ma.getFile_path();
        FileSystemResource file = new FileSystemResource(test);
        msg.addAttachment(mailAttachment.getFile_name(), file);
        javaMailSender.send(msg.getMimeMessage());
    }

}
