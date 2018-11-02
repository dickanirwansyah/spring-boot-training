package com.demo.belajarspring.entity.training.repo;

import com.demo.belajarspring.entity.training.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
