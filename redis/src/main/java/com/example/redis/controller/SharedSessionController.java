package com.example.redis.controller;

import com.example.redis.dao.RedisDao;
import com.example.redis.domain.Person;
import com.example.redis.service.PersonServiceImpl;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SharedSessionController {

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping(value = "/person/get")
    @ResponseBody
    public String request1(@Param("name") String name, HttpServletRequest req){
        Person person = redisDao.getPerson(name);
        return person.toString();
    }

    @RequestMapping(value = "/person/del")
    @ResponseBody
    public String request2(@Param("name") String name,HttpServletRequest req){
        Person person = redisDao.delPerson(name);
        if(person != null){
            return person.toString();
        }
        return null;
    }

    @RequestMapping(value = "/person/selectByName")
    @ResponseBody
    public String selectByName(@Param("name") String name,HttpServletRequest req){
        Person person = personService.selectByName(name);
        if(person != null){
            return person.toString();
        }
        return null;
    }

    @RequestMapping(value = "/person/save")
    @ResponseBody
    public Person savePerson(Person person,HttpServletRequest req){
        person = personService.savePerson(person);
        return person;
    }
}
