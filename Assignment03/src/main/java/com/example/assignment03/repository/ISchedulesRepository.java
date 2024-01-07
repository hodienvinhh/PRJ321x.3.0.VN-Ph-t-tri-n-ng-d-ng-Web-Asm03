package com.example.assignment03.repository;

import com.example.assignment03.entity.Schedules;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchedulesRepository extends JpaRepository<Schedules, Integer> {
}
