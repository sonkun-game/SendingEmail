package com.email.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "mail_attachment")
public class MailAttachment  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "file_name")
    private String file_name;
    private String old_file_name;
    @Column(name = "file_path")
    private String file_path;
    @Column(name = "file_ext")
    private String file_ext;
    @Column(name = "mail_id")
    private String mail_id;
}
