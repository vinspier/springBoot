package com.fxb.vinspier.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/webSocket")
    public String toWebSocket(){
        return "socket";
    }
}
