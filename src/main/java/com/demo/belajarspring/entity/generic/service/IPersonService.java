package com.demo.belajarspring.entity.generic.service;

import com.demo.belajarspring.entity.generic.dao.IPersonDAO;
import com.demo.belajarspring.entity.generic.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPersonService implements GenericService<Person> {

    @Autowired
    private IPersonDAO personDAO;

    @Override
    public synchronized boolean inserted(Person data) {
        if (personDAO.isExisting(data.getEmail(), data.getFirstname())){
            return false;
        }else {
            personDAO.inserted(data);
        }
        return true;
    }

    @Override
    public List<Person> list() {
        return personDAO.list();
    }

    @Override
    public Person getId(Long id) {
        return personDAO.getId(id);
    }
}
