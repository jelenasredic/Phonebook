package com.ingsoftware.contactmanager.repository;

import com.ingsoftware.contactmanager.entity.Contact;
import com.ingsoftware.contactmanager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    boolean existsById(UUID id);

    Page<Contact> findAllByUserId(UUID id, Pageable page);
    Page<Contact> findAllByUserAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(User user, String firstName, String lastName, Pageable pageable);


  @Query(value = "SELECT * FROM contacts WHERE user_id= :users.id and id= :contacts.id", nativeQuery = true)
  Contact findContactById(@Param("users.id") UUID userId, @Param("contacts.id") UUID contactId);


}