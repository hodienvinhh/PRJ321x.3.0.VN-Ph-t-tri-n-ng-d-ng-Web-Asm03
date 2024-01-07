package com.example.assignment03.repository;

import com.example.assignment03.entity.SupporterLogs;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupporterLogsRepository extends JpaRepository<SupporterLogs, Integer> {
}
