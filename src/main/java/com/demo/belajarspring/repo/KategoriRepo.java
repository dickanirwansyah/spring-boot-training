package com.demo.belajarspring.repo;

import com.demo.belajarspring.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepo extends JpaRepository<Kategori, Long> {
}
