package com.example.myjpa.Controller;

import com.example.myjpa.dao.PersonRepository;
import com.example.myjpa.dao.PersonUseCustomizeRepository;
import com.example.myjpa.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PersonController {

    @Autowired //JPA自动将bean注入到容器中
    private PersonRepository personRepository;
    @Autowired
    private PersonUseCustomizeRepository personUseCustomizeRepository;

    @RequestMapping(value = "/test_findByName")
    @ResponseBody
    public List<Person> findByName(String name){
        return personRepository.findByName(name);
    }

    @RequestMapping(value = "/test_findAll")
    @ResponseBody
    public List<Person> findAll(){
        return personRepository.findAll();
    }

    @RequestMapping(value = "/test_findByNameAndAddress")
    @ResponseBody
    public Person findByNameAndAddress(String name,String address){
        return personRepository.findByNameAndAddress(name,address);
    }

    @RequestMapping("/test_withNameAndAddressNamedQuery")
    @ResponseBody
    public Person withNameAndAddressNamedQuery(String name,String address){
     return personRepository.withNameAndAddressNamedQuery(name,address);
    }

    @RequestMapping("/test_withNameAndAddress")
    @ResponseBody
    public Person withNameAndAddress(String name,String address){
        return personRepository.withNameAndAddress(name,address);
    }

    @RequestMapping(value = "/test_save")
    @ResponseBody
    public List<Person> save(String name,String address,Integer age){
        personRepository.save(new Person(null,name,age,address));
        List<Person> person = personRepository.findByName(name);
        return person;
    }

    @RequestMapping(value = "/test_sort")
    @ResponseBody
    public List<Person> sort(){
        return personRepository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }

    @RequestMapping(value = "/test_page")
    @ResponseBody
    public Page<Person> page(){
        return personRepository.findAll(new PageRequest(1,3));
    }

    @RequestMapping(value = "/test_customize")
    @ResponseBody
    public Page<Person> customize(Person person){
        // 控制器中接收一个Person对象，当person中属性有值时，会自动对属性进行like查询 当所有属性为空时 会查出全部
        // 注意点：实体类中的类型应该用包装类，在spring MVC中，使用原始类型会自动赋值成0 而不是空 导致查询失败
        return personUseCustomizeRepository.findByAuto(person,new PageRequest(0,3));
    }
}
