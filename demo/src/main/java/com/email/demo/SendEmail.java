package com.email.demo;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public void testSendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "my-mail-server");
        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom("testspring44aa@gmail.com");
            msg.setRecipients(Message.RecipientType.TO,
                    "mikechael74@gmail.com");
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            msg.setText("Hello, world!\n");
            Transport.send(msg, "testspring44aa@gmail.com", "tavkhotighpvetrr");
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }

    public void testSendMailV2() {
        String smtpServer = "smtp.gmail.com";
        int port = 465;
        final String userid = "testspring44aa@gmail.com";//change accordingly
        final String password = "tavkhotighpvetrr";//change accordingly
        String contentType = "text/html";
        String subject = "test: bounce an email to a different address " +
                "from the sender";
        String from = "testspring44aa@gmail.com";
        String to = "bouncer@fauxmail.com";//some invalid address
        String bounceAddr = "mikechael74@gmail.com";//change accordingly
        String body = "Test: get message to bounce to a separate email address";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "465");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.from", bounceAddr);
        Session mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userid, password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.addFrom(InternetAddress.parse(from));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setContent(body, contentType);

            Transport transport = mailSession.getTransport();

            System.out.println("Sending ....");
            transport.connect(smtpServer, port, userid, password);
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            System.out.println("Sending done ...");
            transport.close();
        } catch (Exception e) {
            System.err.println("Error Sending: ");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        new SendEmail().testSendMail();
    }// end function main()
}
