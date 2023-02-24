package com.email.demo.repository;

import com.email.demo.model.MailAttachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MailAttachedResponsitory extends CrudRepository<MailAttachment, Long> {
}
