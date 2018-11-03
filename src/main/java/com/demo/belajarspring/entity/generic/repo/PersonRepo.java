package com.demo.belajarspring.entity.generic.repo;

import com.demo.belajarspring.entity.generic.entity.Person;
import com.demo.belajarspring.entity.generic.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByFirstname(String firstname);
    Person findByLastname(String lastname);
    Person findByStatus(String status);
    List<Person> findByEmailAndFirstname(String email, String firstname);

}
