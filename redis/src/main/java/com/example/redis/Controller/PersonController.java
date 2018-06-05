package com.example.redis.Controller;

import com.example.redis.dao.PersonDao;
import com.example.redis.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping(value = "/set")
    @ResponseBody
    public void set(){
        Person person = new Person("1","aa",23);
        personDao.save(person);
        personDao.stringRedisTemplateDemo();
    }

    @RequestMapping(value = "/getS")
    @ResponseBody
    public String getString(){
        return personDao.getString();
    }

    @RequestMapping(value = "/getP")
    @ResponseBody
    public Person getPerson(@Param("id") String id){
        return personDao.getPerson(id);
    }

    @RequestMapping(value = "/saveP")
    @ResponseBody
    public Person savePerson(Person person){
         personDao.save(person);
         return person;
    }
}
