package com.altynbekova.top.securitydemo.repository;

import com.altynbekova.top.securitydemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAgeBetween(int min, int max);

    void deleteByAddressContains(String address);

    Optional<Person> findByName(String name);
}