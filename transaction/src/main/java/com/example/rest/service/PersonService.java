package com.example.rest.service;

import com.example.rest.domain.Person;

public interface PersonService {
    Person savePersonWithRollBack(Person person);
    Person savePersonWithoutRollBack(Person person);
}
