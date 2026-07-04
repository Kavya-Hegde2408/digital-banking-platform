package com.kavya.digitalbanking.auth.repository;

import com.kavya.digitalbanking.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    //Optional<User> findByFirstname(String Firstname);

    boolean existsByEmail(String email);

    //boolean existsByFirstname(String firstname);
}
