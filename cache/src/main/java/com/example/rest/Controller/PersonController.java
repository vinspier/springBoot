package com.example.rest.Controller;

import com.example.rest.domain.Person;
import com.example.rest.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;


    @RequestMapping(value = "/put")
    @ResponseBody
    public Person put(Person person){
        return personService.save(person);
    }

    @RequestMapping(value = "/cacheable")
    @ResponseBody
    public Person cacheable(Person person){
        return personService.findOne(person);
    }

    @RequestMapping(value = "/evict")
    @ResponseBody
    public String evict(Long id){
         personService.remove(id);
         return "delete OK";
    }

}
