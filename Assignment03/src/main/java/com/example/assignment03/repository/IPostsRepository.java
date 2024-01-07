package com.example.assignment03.repository;

import com.example.assignment03.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostsRepository extends JpaRepository<Posts, Integer> {
    Posts findById(int id);

    // Posts findByPatientsId(Pageable pageable, int id);


     List<Posts> findAllByPatientsId(int id);
}
