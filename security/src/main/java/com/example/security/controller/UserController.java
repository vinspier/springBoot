package com.example.security.controller;

import com.example.security.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping(value = "/message")
    public String index(Model model){
        Message message = new Message("标题","公共显示内容","管理员显示内容区域");
        model.addAttribute("msg",message);
        return "home";
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public String test(){
        return "test";
    }
}
