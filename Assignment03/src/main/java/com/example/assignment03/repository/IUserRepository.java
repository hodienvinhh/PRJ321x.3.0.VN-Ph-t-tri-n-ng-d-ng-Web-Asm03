package com.example.assignment03.repository;

import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByToken(String token);
}
