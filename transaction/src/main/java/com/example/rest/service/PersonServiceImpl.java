package com.example.rest.service;

import com.example.rest.dao.PersonRepository;
import com.example.rest.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if(person.getName().equals("aa")){
            throw new IllegalArgumentException("该名称已存在，数据回滚");// 硬编码 手动抛出异常
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if(person.getName().equals("aa")){
            throw new IllegalArgumentException("该名称已存在，数据不回滚");
        }
        return p;
    }
}
