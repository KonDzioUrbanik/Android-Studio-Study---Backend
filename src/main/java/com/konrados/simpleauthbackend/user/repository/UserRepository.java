package com.konrados.simpleauthbackend.user.repository;

import com.konrados.simpleauthbackend.user.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NotBlank String username);

}
