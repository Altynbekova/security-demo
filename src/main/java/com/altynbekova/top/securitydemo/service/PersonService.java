package com.altynbekova.top.securitydemo.service;

import com.altynbekova.top.securitydemo.entity.Person;
import com.altynbekova.top.securitydemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(long id) {
        return personRepository.findById(id).
                orElseThrow(() -> new RuntimeException());
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }
}

