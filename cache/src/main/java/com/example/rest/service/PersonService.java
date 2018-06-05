package com.example.rest.service;

import com.example.rest.domain.Person;

public interface PersonService {
    Person save(Person person);
    void remove(Long id);
    Person findOne(Person person);
}
