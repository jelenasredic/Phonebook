package com.ingsoftware.contactmanager.repository;

import com.ingsoftware.contactmanager.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    boolean existsById(UUID id);

    Page<Contact> findAllByUserId(UUID id, Pageable page);
}
