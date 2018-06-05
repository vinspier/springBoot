package com.example.rest.dao;

import com.example.rest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByName(String name);
}
