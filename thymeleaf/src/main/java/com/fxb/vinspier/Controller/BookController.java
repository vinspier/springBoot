package com.fxb.vinspier.Controller;

import com.fxb.vinspier.PersonConfiguration.ExampleService;
import com.fxb.vinspier.domain.Book;
import com.fxb.vinspier.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private Book book;
    @Autowired
    private ExampleService exampleService;

    @RequestMapping("/book")
    @ResponseBody
    public String getBook(){
        return book.getId() + "----" + book.getName();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "1234";
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public String index(){
        return exampleService.printMsg();
    }

    @RequestMapping(value = "/book_test")
    @ResponseBody
    public String book_test() throws Exception{
        return book.getId() + "----" + book.getName() + "---------" + book.getAuthor();
    }

    @RequestMapping(value = "/index")
    public String indexPerson(Model model){
        Person singlePerson = new Person("fxb",23);
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("fxb1",23));
        personList.add(new Person("fxb2",23));
        personList.add(new Person("fxb3",23));
        model.addAttribute("singlePerson",singlePerson);
        model.addAttribute("personList",personList);
        return "index";
    }
}
