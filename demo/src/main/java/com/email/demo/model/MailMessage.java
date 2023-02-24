package com.email.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table( name = "mail_message")
public class MailMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "msg_from")
    private String msg_from;
    @Column(name = "msg_to")
    private String msg_to;
    @Column(name = "msg_cc")
    private String msg_cc;
    @Column(name = "text")
    private String text;
    @Column(name = "subject")
    private String subject;
    @Column(name = "status")
    private String status;
}
