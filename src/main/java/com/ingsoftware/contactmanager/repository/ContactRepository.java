package com.ingsoftware.contactmanager.repository;

import com.ingsoftware.contactmanager.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    boolean existsByEmailIgnoreCase(String email);
}
