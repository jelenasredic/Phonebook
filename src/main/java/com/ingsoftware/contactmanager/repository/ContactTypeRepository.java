package com.ingsoftware.contactmanager.repository;

import com.ingsoftware.contactmanager.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, UUID> {
    boolean existsByTypeIgnoreCase(String type);

}
