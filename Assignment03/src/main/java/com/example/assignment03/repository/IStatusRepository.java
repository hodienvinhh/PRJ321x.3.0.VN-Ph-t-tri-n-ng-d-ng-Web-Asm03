package com.example.assignment03.repository;

import com.example.assignment03.entity.Status;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepository extends JpaRepository<Status, Integer> {
}
