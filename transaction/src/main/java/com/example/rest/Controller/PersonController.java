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


    @RequestMapping(value = "/rollBack")
    @ResponseBody
    public Person rollBack(Person person){
        return personService.savePersonWithRollBack(person);
    }

    @RequestMapping(value = "/noRollBack")
    @ResponseBody
    public Person noRollBack(Person person){
        return personService.savePersonWithoutRollBack(person);
    }
}
