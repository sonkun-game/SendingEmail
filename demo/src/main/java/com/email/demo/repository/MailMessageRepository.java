package com.email.demo.repository;

import com.email.demo.model.MailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MailMessageRepository extends JpaRepository<MailMessage, Long> {

}
