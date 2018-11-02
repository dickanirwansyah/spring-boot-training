package com.demo.belajarspring.entity.onetomany.repository;

import com.demo.belajarspring.entity.onetomany.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepo extends JpaRepository<Posts, Long> {

    Posts findByIdIn(Long id);

    @Query("SELECT p FROM Posts p ORDER BY p.createdAt ASC")
    List<Posts> findByCreatedAt();

    @Query("SELECT p FROM Posts p ORDER BY p.createdAt DESC")
    List<Posts> findByCreatedAtDesc();
}
