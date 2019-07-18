package com.example.redis.dao;

import com.example.redis.domain.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {
    Person selectByName(String username);
    Integer savePerson(Person person);
}
