package com.example.jms.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MsgReceiver {
    @JmsListener(destination = "my-destination")
    public void receiveMessage(String message) {
        System.out.println("接收到： <" + message + ">");
    }
}
