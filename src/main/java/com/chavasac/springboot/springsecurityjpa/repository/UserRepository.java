package com.chavasac.springboot.springsecurityjpa.repository;

import com.chavasac.springboot.springsecurityjpa.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
