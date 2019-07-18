package com.example.redis.service;

import com.example.redis.domain.Person;

public interface PersonService {
    Person savePerson(Person person);
    Person getPerson(String personId);
    Person selectByName(String name);
}
