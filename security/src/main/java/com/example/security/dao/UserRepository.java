package com.example.security.dao;

import com.example.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
