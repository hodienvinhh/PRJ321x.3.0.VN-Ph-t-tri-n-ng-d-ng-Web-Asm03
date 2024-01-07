package com.example.assignment03.repository;

import com.example.assignment03.entity.Comments;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentsRepository extends JpaRepository<Comments, Integer> {
}
