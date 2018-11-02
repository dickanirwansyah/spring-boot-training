package com.demo.belajarspring.repo;

import com.demo.belajarspring.entity.Produk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepo extends JpaRepository<Produk, Long>{
}
