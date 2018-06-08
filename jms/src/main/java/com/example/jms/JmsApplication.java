package com.example.jms;

import com.example.jms.message.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class JmsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(JmsApplication.class, args);
	}

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public void run(String... strings) throws Exception {
		jmsTemplate.send("my-destination",new Msg());
	}
}
