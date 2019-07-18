package com.example.redis.service;

import com.example.redis.dao.PersonDao;
import com.example.redis.dao.PersonMapper;
import com.example.redis.dao.RedisDao;
import com.example.redis.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements  PersonService{

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonDao personDao;

    public Person getPerson(String name){
        Person person = redisDao.getPerson(name);
        if(person != null){
            person = personDao.getPerson(name);
        }
        return person;
    }

    public Person selectByName(String name){
        /** 先从缓存中获取用户信息 */
        Person person = redisDao.getPerson(name);
        if(person != null){
            /** 缓存中无用户信息 则从数据库中获取 */
            person = personMapper.selectByName(name);
            redisDao.setPerson(name,person);
        }
        return person;
    }

    public Person savePerson(Person person){
        personMapper.savePerson(person);
        redisDao.setPerson(person.getName(),person);
        return person;
    }

}
