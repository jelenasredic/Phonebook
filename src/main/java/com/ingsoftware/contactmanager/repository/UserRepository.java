package com.ingsoftware.contactmanager.repository;

import com.ingsoftware.contactmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmailIgnoreCase(String email);
}
