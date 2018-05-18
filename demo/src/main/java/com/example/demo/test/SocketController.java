package com.example.demo.test;

import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;// 通过它想浏览器传递信息

    @MessageMapping("/chat")
    public void handleChat(Principal principal,String msg){
        if(principal.getNickname().equals("fxb")){
            simpMessagingTemplate.convertAndSendToUser("ty","/queue/notifications",principal.getNickname()+"-send:" + msg);
        }else {
            simpMessagingTemplate.convertAndSendToUser("fxb","/queue/notifications",principal.getNickname()+"-send:" + msg);
        }
    }

    @MessageMapping(value = "/welcome")
    @SendTo(value = "/topic/getResponse")
    public SocketResponse say(SocketMessage message) throws Exception{
        Thread.sleep(3000);
        return new SocketResponse("Welcome    " + message.getName() + "!");
    }

    @RequestMapping(value = "/webSocket")
    public String toWebSocket(){
        return "socket";
    }

    @RequestMapping(value = "/login")
    public String toLogin(){return "login";}

    @RequestMapping(value = "/chat")
    public String toChat(){return "chat";}
}
