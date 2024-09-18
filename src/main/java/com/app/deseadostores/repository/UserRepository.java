package com.app.deseadostores.repository;

import com.app.deseadostores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailIgnoreCaseAndEnabledTrue(String email);

    Optional<User> findUserByEmailIgnoreCase(String email);

    Optional<User> findByIdAndEnabledTrue(Long userId);
}
