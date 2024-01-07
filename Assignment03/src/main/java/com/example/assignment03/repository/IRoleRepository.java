package com.example.assignment03.repository;

import com.example.assignment03.entity.Role;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}
