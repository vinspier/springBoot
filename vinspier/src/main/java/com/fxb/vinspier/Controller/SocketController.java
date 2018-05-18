package com.fxb.vinspier.Controller;

import com.fxb.vinspier.domain.SocketMessage;
import com.fxb.vinspier.domain.SocketResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @MessageMapping(value = "/welcome")
    @SendTo(value = "/topic/getResponse")
    public SocketResponse say(SocketMessage message) throws Exception{
        Thread.sleep(3000);
        return new SocketResponse("Welcome" + message.getName() + "!");
    }
}
