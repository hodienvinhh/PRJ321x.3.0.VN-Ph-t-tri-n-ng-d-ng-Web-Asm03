package com.example.assignment03.repository;

import com.example.assignment03.entity.Places;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlacesRepository extends JpaRepository<Places, Integer> {
}
