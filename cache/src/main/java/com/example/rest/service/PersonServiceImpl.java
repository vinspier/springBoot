package com.example.rest.service;

import com.example.rest.dao.PersonRepository;
import com.example.rest.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @CachePut(value = "people",key = "#person.id")// 缓存名为people，key为person的id
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("缓存名为people，key为person的id: "+p.getId());
        return p;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("删除了ID，key为"+id+"的数据缓存");
       // personRepository.delete(id);
    }

    @Override
    @Cacheable(value = "people",key="#person.id")
    public Person findOne(Person person) {
        Person p = personRepository.findOne(person.getId());
        System.out.println("缓存名为people，key为person的id: "+p.getId());
        return p;
    }
}
