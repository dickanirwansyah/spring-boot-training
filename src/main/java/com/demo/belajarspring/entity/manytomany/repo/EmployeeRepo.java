package com.demo.belajarspring.entity.manytomany.repo;

import com.demo.belajarspring.entity.manytomany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
}
