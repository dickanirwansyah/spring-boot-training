package com.demo.belajarspring.entity.onetomany.repository;

import com.demo.belajarspring.entity.onetomany.entity.Comment;
import com.demo.belajarspring.entity.onetomany.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long>{

    /** find comment by posts **/
    List<Comment> findByPosts(Posts posts);
}
