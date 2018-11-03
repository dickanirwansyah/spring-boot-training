package com.demo.belajarspring.entity.generic.dao;

import com.demo.belajarspring.entity.generic.entity.Person;
import com.demo.belajarspring.entity.generic.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class IPersonDAO implements GenericDAO<Person> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PersonRepo personRepo;

    @Override
    public Person insert(Person entity) {
        return null;
    }

    @Override
    public void inserted(Person entity) {
        entityManager.persist(entity);
    }

    @Override
    public Person update(Person entity, Long id) {
        return null;
    }

    @Override
    public void updated(Person id, Person entity) {

    }

    @Override
    public List<Person> list() {
        String hql="SELECT p FROM Person p ORDER BY p.id ASC";
        return entityManager.createQuery(hql)
                .getResultList();
    }

    @Override
    public void delete(Person entity) {

    }

    @Override
    public Person getId(Long id) {
        return entityManager.find(Person.class, id);
    }

    public boolean isExisting(String email, String firstname){
//        String hql="FROM Person as p WHERE p.email=? and p.firstname=?";
//        int count = entityManager.createQuery(hql)
//                .setParameter(1, email)
//                .setParameter(2, firstname)
//                .getResultList()
//                .size();

        int count = personRepo.findByEmailAndFirstname(email, firstname).size();
        return count > 0 ? true : false;
    }
}
