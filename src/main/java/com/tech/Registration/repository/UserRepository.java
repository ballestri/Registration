package com.tech.Registration.repository;

import com.tech.Registration.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@SecurityRequirement(name = "bearer")
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
