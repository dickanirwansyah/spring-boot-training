package com.demo.belajarspring.entity.manytomany.repo;

import com.demo.belajarspring.entity.manytomany.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill, Long>{


    Skill findByIdIs(Long id);

}
